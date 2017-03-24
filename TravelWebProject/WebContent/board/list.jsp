<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import= "java.util.*,com.sist.dao.*,java.text.*"%>
<% 
	String strPage=request.getParameter("page");
	if(strPage==null)
		strPage="1";
	int curpage=Integer.parseInt(strPage);
	
	TeamDAO dao=new TeamDAO();
	List<TeamVO> list=dao.boardListData(curpage);
	int totalpage=dao.boardTotalPage();
	int count=dao.boardRowCount();
	
	count=count-((curpage*10)-10);
	
	int total=dao.boardTotalPage();

%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style>
	td,th{
		font-family: 맑은 고딕;
		font-size: 9pt;
	}
	a{
		text-decoration: none;
		color: black;
	}
	a:HOVER{
		text-decoration: underline;
		color: green;
	}
</style>

</head>
<body>
<center>
		<img src="../image/title.gif">
		<p>
		<table border="0" width=600>
			<tr>
				<td align="left">
					<a href="board_write.html">
					<img alt="글쓰기" src="../image/bt_write.jpg" border="0"></a>
				</td>
			</tr>
		</table>
		
		<table border="0" width=600>
			<tr bgcolor="#ccccff" height="27">
				<th width="10%">번호</th>
				<th width="45%">제목</th>
				<th width="15%">이름</th>
				<th width="20%">작성일</th>
				<th width="10%">조회수</th>
			</tr>
			<tr bgcolor="ivory" height="27">
				<td width="10%" align="center">3</td>
				<td width="45%" align="left">
					<a href="board_content.html">HTML 연습용 게시판 만들기</a>
				</td>
				<td width="15%" align="center">정도전</td>
				<td width="20%" align="center">2017-02-28</td>
				<td width="10%" align="center">5</td>
			</tr>
			<tr bgcolor="white" height="27">
				<td width="10%" align="center">2</td>
				<td width="45%" align="left">
					&nbsp;&nbsp;<img alt="댓글" src="../image/icon_reply.gif"><a href="board_content.html">HTML 연습용 게시판 만들기</a>
				</td>
				<td width="15%" align="center">정도전</td>
				<td width="20%" align="center">2017-02-28</td>
				<td width="10%" align="center">5</td>
			</tr>	
			<tr bgcolor="white" height="27">
				<td width="10%" align="center">2</td>
				<td width="45%" align="left">
					&nbsp;&nbsp;&nbsp;&nbsp;<img alt="댓글" src="../image/icon_reply.gif"><a href="board_content.html">HTML 연습용 게시판 만들기</a>
				</td>
				<td width="15%" align="center">정도전</td>
				<td width="20%" align="center">2017-02-28</td>
				<td width="10%" align="center">5</td>
			</tr>					
		</table>
		<hr width=600>
		
		<table border="0" width=600>
			<tr>
				<td align="left">
				Search:<select>
							<option>이름</option>
							<option>제목</option>
							<option>내용</option>
						</select>
						<input type="text" size="10">
						<input type="button" value="찾기">
				</td>
		    </tr>
		</table>
	</center>
</body>
</html>