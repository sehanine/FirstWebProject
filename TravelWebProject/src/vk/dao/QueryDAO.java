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
