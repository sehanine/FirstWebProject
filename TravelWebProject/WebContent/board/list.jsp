<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import= "java.util.*,board.dao.*,java.text.*"%>
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
<link rel="stylesheet" type="text/css" href="table.css">
<style type="text/css">
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#findBtn').click(function(){
		var ss=$('#ss').val();
		$('#print').html("");
		if(ss==""){
			$('#print').html("<font color=red>�˻��� �Է�</font>");
			$('#ss').focus();
			return;
		}
		$('#ff').submit();
	});
});
</script>
</head>
<body>
<center>
	<h3>Festigo�Խ���</h3>
		<table border="0" width=600>
			<tr bgcolor="#ccccff" height="27">
				<th width="10%">��ȣ</th>
				<th width="45%">����</th>
				<th width="15%">�̸�</th>
				<th width="20%">�ۼ���</th>
				<th width="10%">��ȸ��</th>
			</tr>
			<tr bgcolor="ivory" height="27">
				<td width="10%" align="center">5</td>
				<td width="45%" align="left">
					<a href="board_content.html">HTML ������ �Խ��� �����</a>
				</td>
				<td width="15%" align="center">���Ƕ�</td>
				<td width="20%" align="center">2017-03-24</td>
				<td width="10%" align="center">5</td>
			</tr>
			<tr bgcolor="ivory" height="27">
				<td width="10%" align="center">4</td>
				<td width="45%" align="left">
					<a href="board_content.html">HTML ������ �Խ��� �����</a>
				</td>
				<td width="15%" align="center">���Ƕ�</td>
				<td width="20%" align="center">2017-03-24</td>
				<td width="10%" align="center">5</td>
			</tr>
			<tr bgcolor="ivory" height="27">
				<td width="10%" align="center">3</td>
				<td width="45%" align="left">
					<a href="board_content.html">HTML ������ �Խ��� �����</a>
				</td>
				<td width="15%" align="center">���Ƕ�</td>
				<td width="20%" align="center">2017-03-24</td>
				<td width="10%" align="center">5</td>
			</tr>
			<tr bgcolor="ivory" height="27">
				<td width="10%" align="center">2</td>
				<td width="45%" align="left">
					<a href="board_content.html">HTML ������ �Խ��� �����</a>
				</td>
				<td width="15%" align="center">���Ƕ�</td>
				<td width="20%" align="center">2017-03-24</td>
				<td width="10%" align="center">5</td>
			</tr>
			<tr bgcolor="ivory" height="27">
				<td width="10%" align="center">1</td>
				<td width="45%" align="left">
					<a href="board_content.html">HTML ������ �Խ��� �����</a>
				</td>
				<td width="15%" align="center">���Ƕ�</td>
				<td width="20%" align="center">2017-03-24</td>
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