<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="web.dao.*, java.util.ArrayList" %>
<%
	String pageNum = request.getParameter("page");
	if(pageNum == null){
		pageNum = "1";
	}

	int curPage = Integer.parseInt(pageNum);
	QueryDAO dao = new QueryDAO();
	ArrayList<MainVO> list = dao.boardListData(curPage);
	int totalPage = dao.getDivPage();
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<center>
		<table>
	
			<%	
				for(MainVO vo: list){	
			%>
			<tr>
				
				<td>
				<!-- fesno, maintitle, maincontent, mainloc, fesdate -->
					<a href="content.jsp?page=<%=vo.getFesno() %>">
					<%=vo.getMaintitle() %></a>
				</td>
				<td>
				<!-- fesno, maintitle, maincontent, mainloc, fesdate -->
				
					<%=vo.getMaincontent() %>
				</td>
				<td>
				<!-- fesno, maintitle, maincontent, mainloc, fesdate -->
				
					<%=vo.getMainloc() %>
				</td>				
				<td>
				<!-- fesno, maintitle, maincontent, mainloc, fesdate -->
				
					<%=vo.getFesdate() %>
				</td>								
				
			</tr>
			<%
				}
			%>	
			
		</table>
			<%
				for(int i = 1; i <= totalPage; i++){
			%>
					[<a href="main.jsp?page=<%=i %>"><%=i %></a>]
			<%
				}
			%>
	</center>
</body>
</html>