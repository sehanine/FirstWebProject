package web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class QueryDAO {
	
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@211.238.142.230:1521:ORCL";
	//private static DataDAO dao;
	/**
	 * 쿼리문 템플릿
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
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	 */
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
	public ArrayList<String> getMainVO(int fesno){ // test use only ****
		ArrayList<String> list = new ArrayList<>();
		try{
			getConnection();
			String sql = "SELECT  maintitle, maincontent, mainloc, fesdate "
					+ "FROM vk_main WHERE fesno = ?";
			
			// maintitle, maincontent, mainLoc, fesdate
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
	
	public int getTotalPage(){  // test use only ************
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
	public ArrayList<MainVO> boardListData(int page){
		ArrayList<MainVO> list = new ArrayList<>();
		
		try{
			getConnection();
			int rowSize = 6;
			int start = (rowSize * page)-(rowSize - 1);
			int end = rowSize * page;
			/*
			 *  1-10 11-20 			1-5 6-10
			 */

			String sql_ = "SELECT fesno, maintitle, maincontent, mainloc, fesdate "
					+"FROM (SELECT fesno, maintitle, maincontent, mainloc, fesdate FROM vk_main ORDER BY  fesno ASC) "
					+"WHERE fesno BETWEEN " + start + " AND " + end;
			ps=conn.prepareStatement(sql_);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				// maintitle, maincontent, mainloc, fesdate
				MainVO vo = new MainVO();
				vo.setFesno(rs.getInt(1));
				vo.setMaintitle(rs.getString(2));
				vo.setMaincontent(rs.getString(3));
				vo.setMainloc(rs.getString(4));
				vo.setFesdate(rs.getString(5));
				list.add(vo);
			}
			rs.close();
		
//			for(MainVO vo: list){
//				System.out.println(vo.getMaintitle());
//			}
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally{
			disConnection();
		}
		
		return list;
	}
	
	public int getDivPage(){
		int  result = 0;
		
		try{
			getConnection();
			String sql = "SELECT CEIL(COUNT(*)/6) FROM vk_main";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			result = rs.getInt(1);
			rs.close();
			
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
	
		return list;
	}
	public ArrayList<ListVO> getFirst_list(int fesno){
		ArrayList<ListVO> list = new ArrayList<>();
		try{
			getConnection();
			String sql = "SELECT first_list_title, first_list "
					+ "FROM vk_first_list WHERE fesno= ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, fesno);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				ListVO vo = new ListVO();
				vo.setTitle(rs.getString(1));
				vo.setContent(rs.getString(2));
				list.add(vo);
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
	public ArrayList<ListVO> getSecond_list(int fesno){
		ArrayList<ListVO> list = new ArrayList<>();
		try{
			getConnection();
			String sql = "SELECT second_list_title, second_list "
					+ "FROM vk_second_list WHERE fesno= ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, fesno);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				ListVO vo = new ListVO();
				vo.setTitle(rs.getString(1));
				vo.setContent(rs.getString(2));
				list.add(vo);
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
	public ArrayList<ListVO> getThird_list(int fesno){
		ArrayList<ListVO> list = new ArrayList<>();
		try{
			getConnection();
			String sql = "SELECT third_list_title, third_list "
					+ "FROM vk_third_list WHERE fesno= ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, fesno);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				ListVO vo = new ListVO();
				vo.setTitle(rs.getString(1));
				vo.setContent(rs.getString(2));
				list.add(vo);
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
	public String getFirstImage(int fesno){
		String url = null;
		try{
			getConnection();
			String sql = "SELECT image FROM vk_image_list WHERE fesno = ? AND ROWNUM = 1";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, fesno);
			ResultSet rs = ps.executeQuery();
			rs.next();
		
			url = rs.getString(1);
			
			ps.close();
	
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			url = "img/1.png";
		}finally{
			disConnection();
		}
		return url;
	}
	
	

}
