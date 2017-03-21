package vk.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DataDAO {
	
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@211.238.142.230:1521:ORCL";
	private static DataDAO dao;
	private int numOfImages;
	private int numOfsqlColumn = 0;
	//드라이버 등록 
	public DataDAO(){
		numOfImages = 0;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	//싱글턴
	public static DataDAO newInstance(){
		if(dao == null){
			dao = new DataDAO();
		}
		return dao;
	}
	
	//오라클 해제
	public void disConnection(){
		try{
			if(ps != null){
				ps.close();
			}
			if(conn != null){
				conn.close();
			}
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	//오라클 연결
	public void getConnection(){
		try{
			conn=DriverManager.getConnection(URL, "scott", "tiger");
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	// 메인 데이터 insert
	public void setMain(VisitKoreaVO vo){
		
		try{
			getConnection();
			String sql = "INSERT INTO vk_main("
					+ "fesno, maintitle, maincontent, mainloc, fesdate) "
					+ "VALUES (?, ?, ?, ?, ?)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getFesNo());
			
			ps.setString(2, vo.getTitle());
			ps.setString(3, vo.getContentShort());
			ps.setString(4, vo.getMainLoc());
			ps.setString(5, vo.getDate());
			ps.executeUpdate();
			ps.close();
			
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}finally{
			disConnection();
		}
	}
	public void dropAndCreateImageTable(){
		String sqldrop = "DROP TABLE vk_image_list CASCADE CONSTRAINTS";
		
		String sqlCreateTable = "CREATE TABLE vk_main( "
				  + "fesno          NUMBER, "
				  + "maintitle     VARCHAR2(100) CONSTRAINT vk_main_maintitle_nn NOT NULL, "
				  + "maincontent   CLOB, "
				  + "mainloc        VARCHAR2(100), "
				  + "fesdate        VARCHAR2(50), "
				  + "CONSTRAINT vk_main_fesno_pk PRIMARY KEY(fesno) )";
		try{
			getConnection();
			
			ps=conn.prepareStatement(sqldrop);
			ps.executeUpdate();
			
			sqldrop = "DROP TABLE vk_main CASCADE CONSTRAINTS";
			ps=conn.prepareStatement(sqldrop);
			ps.executeUpdate();
			
			ps = conn.prepareStatement(sqlCreateTable);
			ps.executeUpdate();
			 
			sqlCreateTable = "CREATE TABLE vk_image_list("
					  +"fesno     NUMBER, "
					  +"image     VARCHAR2(100), "
					  + "CONSTRAINT vk_image_list_fk FOREIGN KEY(fesno) REFERENCES vk_main(fesno))";
			
			ps = conn.prepareStatement(sqlCreateTable);
			ps.executeUpdate();
			
			
			
		
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}finally{
			disConnection();
		}
				
	}
	public void setImageList(VisitKoreaVO vo){
		ArrayList<String> list = vo.getImage_list();
		numOfImages = list.size();
		try{
			getConnection();
//			while(numOfsqlColumn < numOfImages){
//				String sqlAdd = "ALTER TABLE vk_image_list "
//						+ "ADD image" + numOfsqlColumn + " VARCHAR2(100)";
//				ps=conn.prepareStatement(sqlAdd);
//				ps.executeUpdate();
//				numOfsqlColumn++;
//			}
			for(int i = 0; i < numOfImages; i++){
				int fesno = vo.getFesNo();
			//	String sqlInsert = null;
				
			String	sqlInsert = "INSERT INTO vk_image_list(fesno, image)"
			    + "VALUES(" + fesno + ", ?)";
				ps = conn.prepareStatement(sqlInsert);
				ps.setString(1, list.get(i));
				ps.executeUpdate();
			}
		
			ps.close();
	
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}finally{
			disConnection();
		}
	}
	//
}
