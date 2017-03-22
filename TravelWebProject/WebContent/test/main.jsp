<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="web.dao.*, java.util.ArrayList" %>
<%
	QueryDAO dao = new QueryDAO();
	ArrayList<String> list = new ArrayList<>();
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
				int totalPage = dao.getTotalPage();
				for(int i = 1; i <= totalPage; i++){
					list = dao.getMainVO(i);
			%>
			<tr>
				<%
					for(int j = 0; j < list.size(); j++){
				%>
					<td>
						<a href="content.jsp?page=<%=i %>"><%=list.get(j) %></a>
					</td>		
				<%
					}
				%>
			</tr>
			<%
				}
			%>	
		</table>
	</center>
</body>
</html>