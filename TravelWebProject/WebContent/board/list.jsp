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
		font-family: ���� ���;
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
					<img alt="�۾���" src="../image/bt_write.jpg" border="0"></a>
				</td>
			</tr>
		</table>
		
		<table border="0" width=600>
			<tr bgcolor="#ccccff" height="27">
				<th width="10%">��ȣ</th>
				<th width="45%">����</th>
				<th width="15%">�̸�</th>
				<th width="20%">�ۼ���</th>
				<th width="10%">��ȸ��</th>
			</tr>
			<tr bgcolor="ivory" height="27">
				<td width="10%" align="center">3</td>
				<td width="45%" align="left">
					<a href="board_content.html">HTML ������ �Խ��� �����</a>
				</td>
				<td width="15%" align="center">������</td>
				<td width="20%" align="center">2017-02-28</td>
				<td width="10%" align="center">5</td>
			</tr>
			<tr bgcolor="white" height="27">
				<td width="10%" align="center">2</td>
				<td width="45%" align="left">
					&nbsp;&nbsp;<img alt="���" src="../image/icon_reply.gif"><a href="board_content.html">HTML ������ �Խ��� �����</a>
				</td>
				<td width="15%" align="center">������</td>
				<td width="20%" align="center">2017-02-28</td>
				<td width="10%" align="center">5</td>
			</tr>	
			<tr bgcolor="white" height="27">
				<td width="10%" align="center">2</td>
				<td width="45%" align="left">
					&nbsp;&nbsp;&nbsp;&nbsp;<img alt="���" src="../image/icon_reply.gif"><a href="board_content.html">HTML ������ �Խ��� �����</a>
				</td>
				<td width="15%" align="center">������</td>
				<td width="20%" align="center">2017-02-28</td>
				<td width="10%" align="center">5</td>
			</tr>					
		</table>
		<hr width=600>
		
		<table border="0" width=600>
			<tr>
				<td align="left">
				Search:<select>
							<option>�̸�</option>
							<option>����</option>
							<option>����</option>
						</select>
						<input type="text" size="10">
						<input type="button" value="ã��">
				</td>
		    </tr>
		</table>
	</center>
</body>
</html>