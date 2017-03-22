package vk.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class QueryDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@211.238.142.230:1521:ORCL";
	private static DataDAO dao;
	
	public QueryDAO(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
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
	public ArrayList<String> getMainVO(int fesno){
		ArrayList<String> list = new ArrayList<>();
		try{
			getConnection();
			String sql = "SELECT  maintitle, maincontent, mainloc, fesdate "
					+ "FROM vk_main WHERE fesno = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, fesno);
			ResultSet rs = ps.executeQuery();
			rs.next();
			for(int i = 1; i <= 4; i++){
				
				list.add(rs.getString(i));
		
			}
			ps.close();
	
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}finally{
			disConnection();
		}
		return list; 
	}
	public int getTotalPage(){
		int result = 0;
		try{
			getConnection();
			String sql = "SELECT COUNT(*) FROM vk_main";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			result = rs.getInt(1);
			ps.close();
	
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}finally{
			disConnection();
		}
		return result;
	}
	public ArrayList<String> getImage_list(int fesno){
		ArrayList<String> list = new ArrayList<>();
		
		try{
			getConnection();
			String sql = "SELECT image FROM vk_image_list where fesno = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, fesno);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				list.add(rs.getString(1)); // has onlyone element
			}
			ps.close();
	
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}finally{
			disConnection();
		}
		for(String str: list){
			System.out.println(str);
		}
		return list;
	}

	public ArrayList<String> getSecond_list(int fesno){
		ArrayList<String> list = new ArrayList<>();
		try{
			getConnection();
			String sql = "SELECT second_list_title, second_list "
					+ "FROM vk_second_list WHERE fesno= ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, fesno);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				
			}
			ps.close();
	
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}finally{
			disConnection();
		}
		return list;
	}
/*
  	try{
			getConnection();
			String sql = "INSERT QUERY";
			ps = conn.prepareStatement(sql);
			ps.setString(parameterIndex, x);
			ResultSet rs = ps.executeQuery();
			rs.next();
			//TODO
			ps.close();
	
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}finally{
			disConnection();
		}
 */
}
