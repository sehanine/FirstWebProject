<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="vk.dao.QueryDAO, java.util.ArrayList, vk.dao.ListVO"%>
<%
	String pageNum = request.getParameter("page");
	if(pageNum == null)
		pageNum = "PAGE_NOT_FOUND";
	int curpage = Integer.parseInt(pageNum);
	QueryDAO dao =new QueryDAO();
	ArrayList<String> image_list = dao.getImage_list(curpage);
	ArrayList<String> main_list = dao.getMainVO(curpage);
	ArrayList<ListVO> second_list;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<center>
	<h3><%=main_list.get(0) %></h3>
		<table>
			
			<tr>
				<%
					for(int i = 0; i < image_list.size(); i++){
				%>
					<td>
						<img alt="image<%=i %>" src="<%=image_list.get(i) %>">
					</td>		
				<%
					}
				%>
			</tr>
		
		</table>
	</center>
	
</body>
</html>