package vk.dao;
/*
 * 
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * 
 * @author mdShin
 *  테이블 생성순서
 * 1) vk_main
 * 	a) vk_image_list
 * 	b) vk_second_list
 * 	c)..
 * 
 * vk_main이 다른 리스트에서 사용하는 primary key를 보유하고 있기 때문에,
 * list 테이블 생성전에 꼭 생성 해야함
 *
 *
 */
public class DataDAO {
	
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@211.238.142.230:1521:ORCL";
	private static DataDAO dao;
	private int numOfImages;
	//private final static int NUMBER_OF_TABLES = 4;
	//private int numOfsqlColumn = 0;
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
		
		
		String[] tableName = {"vk_main", "vk_image_list", "vk_first_list",
				"vk_second_list", "vk_third_list"};
		try{
			getConnection();
			for(int i = 0; i < tableName.length; i++){
				String sqldrop = "DROP TABLE " + tableName[i] + " CASCADE CONSTRAINTS";
				ps=conn.prepareStatement(sqldrop);
				ps.executeUpdate();
			}
			
//			sqldrop = "DROP TABLE vk_main CASCADE CONSTRAINTS";
//			ps=conn.prepareStatement(sqldrop);
//			ps.executeUpdate();
			String[] tableDesc = {
					  "CREATE TABLE vk_main( "
					  + "fesno          NUMBER, "
					  + "maintitle     VARCHAR2(100) CONSTRAINT vk_main_maintitle_nn NOT NULL, "
					  + "maincontent   CLOB, "
					  + "mainloc        VARCHAR2(100), "
					  + "fesdate        VARCHAR2(50), "
					  + "CONSTRAINT vk_main_fesno_pk PRIMARY KEY(fesno) )",
					  
					  "CREATE TABLE vk_image_list("
					  +"fesno     NUMBER, "
					  +"image     VARCHAR2(100), "
					  +"CONSTRAINT vk_image_list_fk FOREIGN KEY(fesno) "
					  +"REFERENCES vk_main(fesno))",
					  
					  "CREATE TABLE vk_first_list("
					  +"fesno NUMBER, "
					  +"first_list_title VARCHAR(100), "
					  +"first_list VARCHAR(100), "
					  +"CONSTRAINT first FOREIGN KEY(fesno) "
					  +"REFERENCES vk_main(fesno))",
						
					  "CREATE TABLE vk_second_list("
					  +"fesno NUMBER, "
					  +"second_list_title VARCHAR(20), "
					  +"second_list CLOB, "
					  +"CONSTRAINT vk_second_list_fk FOREIGN KEY(fesno) "
					  +"REFERENCES vk_main(fesno))",
					  
					  "CREATE TABLE vk_third_list("
					  +"fesno NUMBER, "
					  +"third_list_title VARCHAR(20), "
					  +"third_list CLOB, "
					  +"CONSTRAINT vk_third_list_fk FOREIGN KEY(fesno) "
					  +"REFERENCES vk_main(fesno))"
			};
			for(int i = 0; i < tableDesc.length; i++){
				ps = conn.prepareStatement(tableDesc[i]);
				ps.executeUpdate();
			}
		
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
				
			String	insert = "INSERT INTO vk_image_list(fesno, image)"
			    + "VALUES(" + fesno + ", ?)";
				ps = conn.prepareStatement(insert);
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
	public void setFirstList(VisitKoreaVO vo){
/*		
		행사기간 2016.12.02 ~ 2017.03.26
		위치 경기도 가평군 상면 수목원로 432 (상면)
		행사장소 경기 가평군 아침고요수목원 내 주요정원
		연 락 처 1544-6703
		홈페이지 http://morningcalm.co.kr
		*/
		try{
			getConnection();
			String insert = "INSERT INTO vk_first_list("
					+ "fesno, first_list_title, first_list)"
					+ "VALUES(" + vo.getFesNo() + ",?,?)";
			
			ps=conn.prepareStatement(insert);
			
			ps.setString(1, "행사기간");
			ps.setString(2, vo.getDate());
			ps.executeUpdate();
			ps.setString(1, "위치");
			ps.setString(2, vo.getLocAddr());
			ps.executeUpdate();
			ps.setString(1, "행사장소");
			ps.setString(2, vo.getLoc());
			ps.executeUpdate();
			ps.setString(1, "연락처");
			ps.setString(2, vo.getTel());
			ps.executeUpdate();
			ps.setString(1, "홈페이지");
			ps.setString(2, vo.getHomepage());
			ps.executeUpdate();
		
			ps.close();
	
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}finally{
			disConnection();
		}
	}
	public void setSecondList(VisitKoreaVO vo){
		ArrayList<String> list = vo.getSummary();
		int numOfContents = list.size();
		try{
			getConnection();

			for(int i = 0; i < numOfContents; i++){
				int fesno = vo.getFesNo();
			//	String sqlInsert = null;
				
//			String	sqlInsert = "INSERT INTO vk_image_list(fesno, image)"
//			    + "VALUES(" + fesno + ", ?)";
				String insert = "INSERT INTO vk_second_list("
						+ "fesno, second_list_title, second_list)"
						+ "VALUES(" + fesno + ",?,?)";
				ps = conn.prepareStatement(insert);
				//text.substring(0, text.indexOf(' '));
				String temp = list.get(i);
				if(temp.indexOf(' ') != -1){
					if(temp.contains("출 연")){
						ps.setString(1, "출연");
						ps.setString(2, temp.replace("출 연 ", ""));
					} else if(temp.contains("줄 거 리")){
						ps.setString(1, "줄거리");
						ps.setString(2, temp.replace("줄 거 리 ", ""));
					} else{
						ps.setString(1, temp.substring(0, temp.indexOf(' ')));
						ps.setString(2, temp.substring(temp.indexOf(' ')+1));
					}
				} else{
					ps.setString(1, temp);
					ps.setString(2, " ");
				}
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
	public void setThirdList(VisitKoreaVO vo){
		ArrayList<String> list = vo.getInstruction();
		int numOfContents = list.size();
		try{
			getConnection();

			for(int i = 0; i < numOfContents; i++){
				int fesno = vo.getFesNo();
			//	String sqlInsert = null;
				
//			String	sqlInsert = "INSERT INTO vk_image_list(fesno, image)"
//			    + "VALUES(" + fesno + ", ?)";
				String insert = "INSERT INTO vk_third_list("
						+ "fesno, third_list_title, third_list)"
						+ "VALUES(" + fesno + ",?,?)";
				ps = conn.prepareStatement(insert);
				//text.substring(0, text.indexOf(' '));
				String temp = list.get(i);
				if(temp.indexOf(' ') != -1){	
					ps.setString(1, temp.substring(0, temp.indexOf(' ')));
					ps.setString(2, temp.substring(temp.indexOf(' ')+1));
				} else{
					ps.setString(1, temp);
					ps.setString(2, " ");
				}
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

}
