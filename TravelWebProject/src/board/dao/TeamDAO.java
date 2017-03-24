package com.sist.dao;

import java.util.*;
import java.sql.*;

public class TeamDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracl:thin:@211.238.142.213:1521:ORCL";
	private static TeamDAO dao;

	public TeamDAO(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public static TeamDAO newInstance(){
		if(dao==null)
			dao=new TeamDAO();
		return dao;
	}
	
	public void disConnection(){
		try{
			if(ps!=null)ps.close();
			if(conn!=null)conn.close();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public void getConnection(){
		try{
			conn=DriverManager.getConnection(URL, "scott", "tiger");
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public List<TeamVO> boardListData(int page){
		ArrayList<TeamVO> list=new ArrayList<>();
		
		try{
			getConnection();
			String sql="SELECT no,subject,name,regdate,hit "
					+"FROM teamProjects "
					+"ORDER BY group_id DESC,ASC";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			int i=0;
			int j=0;
			int pagecnt=(page*10)-10;
			
			
		}catch(Exception ex){
			System.out.println("boardListData"+ex.getMessage());
		}finally{
			disConnection();
		}
		return list;
	}
	public int boardTotalPage(){
		int total=0;
		
		try{
			getConnection();
		}catch(Exception ex){
			System.out.println("boardTotalPage()"+ex.getMessage());
		}finally{
			disConnection();
		}
		
		return total;
	}
	
	public TeamVO boardContent(int no,int type){
		TeamVO vo=new TeamVO();
		
		try{
			getConnection();
			String sql="";
			if(type==1){
				sql="UPDATE TeamProjects1 SET "
						+"hit=hit+1 "
						+"WHERE no=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, no);
				ps.executeQuery();
				ps.close();
			}
			
			sql="SELECT no,name,email,subject,content,regdate,hit "
					+"FROM TeamProjects1 "
					+"WHERE no=?";
		}catch(Exception ex){
			
			
			System.out.println("boardContent()"+ex.getMessage());
		}finally{
			disConnection();
		}
		return vo;
		
	}
	
	public int boardRowCount(){
		int total=0;
		try{
			getConnection();
			String sql="SELECT COUNT(*) FROM teamProjects1";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();	
			
		}catch(Exception ex){
			System.out.println("boardRowCount()"+ex.getMessage());
		}finally{
			disConnection();
		}
		return total;
	}
	public int boardFindCount(String fs,String ss){
		int count=0;
		try{
			getConnection();
			String sql="SELECT COUNT(*) "
					+"FROM teamProjects1 "
					+"WHERE "+fs+" LIKE '%'||?||'%'";
			ps=conn.prepareStatement(sql);
			ps.setString(1, ss);
			ResultSet rs=ps.executeQuery();
			rs.next();
			count=rs.getInt(1);
			rs.close();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally{
			disConnection();
		}
		return count;
	}
	public List<TeamVO> boardFindData(String fs, String ss){
		List<TeamVO> list=new ArrayList<>();
		
		try{
			getConnection();
			String sql="SELECT no,subject,name,regdate,hit,group_tab "
					+"FROM replyBoard "
					+"WHERE "+fs+" LIKE '%'||?||'%'";
			ps=conn.prepareStatement(sql);
			ps.setString(1, ss);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				TeamVO vo=new TeamVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setRegdate(rs.getDate(4));
				vo.setHit(rs.getInt(5));
				list.add(vo);
			}
			rs.close();
			
		}catch(Exception ex){
			System.out.println("boardFindData"+ex.getMessage());
		}finally{
			disConnection();
		}
		return list;
	}
	
	/*
	 * 	
	 */
	//내용보기
	//SEQUENCE INSERT,UPDATE,SELECT
	public void boardReply(int pno,TeamVO vo){
		try{
			getConnection();
			String sql="SELECT group_id,group_step,group_tab "
					+"FROM replyboard "
					+"WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, pno);
			ResultSet rs=ps.executeQuery();
			rs.next();
			int gi=rs.getInt(1);
			int gs=rs.getInt(2);
			int gt=rs.getInt(3);
			rs.close();
			ps.close();
			//답변의 핵심쿼리
			sql="UPDATE replyboard SET "
					+"group_step=group_step+1 "
					+"WHERE group_id=? AND group_step>?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, gi);
			ps.setInt(2, gs);
			ps.executeUpdate();
			ps.close();
			
			//추가
			sql="INSERT INTO replyBoard(no,name,email,subject,content,pwd,group_id,group_step,group_tab,root) "
					+"VALUES(rb_no_seq.nextval,?,?,?,?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getEmail());
			ps.setString(3, vo.getSubject());
			ps.setString(4, vo.getContent());
			ps.setString(5, vo.getPwd());
			ps.setInt(6, gi);
			ps.setInt(7, gs+1);
			ps.setInt(8, gt+1);
			ps.setInt(9, pno);
			ps.executeUpdate();
			ps.close();
			//depth=depth+1
			sql="UPDATE replyBoard SET "
					+"depth=depth+1 "
					+"WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, pno);
			ps.executeUpdate();
			
		}catch(Exception ex){
			System.out.println("boardReply()"+ex.getMessage());
		}finally{
			disConnection();
		}
	}
	
	public void boardInsert(TeamVO vo){
		try{
			getConnection();
			String sql="INSERT INTO replyBoard(no,name,email,subject,content,pwd,group_id) "
					+"VALUES(rb_no_seq.nextval,?,?,?,?,?,"
					+"(SELECT NVL(MAX(group_id)+1,1) FROM replyBoard))";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getEmail());
			ps.setString(3, vo.getSubject());
			ps.setString(4, vo.getContent());
			ps.setString(5, vo.getPwd());
			
			ps.executeUpdate();
			
		}catch(Exception ex){
			System.out.println("boardInsert()"+ex.getMessage());
		}finally{
			disConnection();
		}
	}







}
