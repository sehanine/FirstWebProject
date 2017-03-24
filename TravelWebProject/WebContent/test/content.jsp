<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="web.dao.*, java.util.ArrayList"%>
<%
	
	String pageNum = request.getParameter("page");
	int TITLE_NAME_INDEX = 0;
	if(pageNum == null)
		pageNum = "PAGE_NOT_FOUND";
	int fesno = Integer.parseInt(pageNum);
	QueryDAO dao =new QueryDAO();
	ArrayList<String> image_list = dao.getImage_list(fesno);
	ArrayList<String> main_list = dao.getMainVO(fesno);
	ArrayList<ListVO> first_list = dao.getFirst_list(fesno);
	ArrayList<ListVO> second_list = dao.getSecond_list(fesno);
	ArrayList<ListVO> third_list = dao.getSecond_list(fesno);
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<center>
	<h3><%=main_list.get(TITLE_NAME_INDEX) %></h3>
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
		<%
			for(int i = 0; i < first_list.size(); i++){
		%>
			<ul>
	
				<li>
					<b><%=first_list.get(i).getTitle() %></b>
					<span><%=first_list.get(i).getContent() %></span>
				</li>
	
			</ul>
		<%
			}
		%>
<!-- 
	행사기간 
	위치 
	행사장소
	연 락 처 
	홈페이지
 -->
		<%
			for(int i = 0; i < second_list.size(); i++){
		%>
			<ul>
	
				<li>
					<b><%=second_list.get(i).getTitle() %></b>
					<span><%=second_list.get(i).getContent() %></span>
				</li>
	
			</ul>
		<%
			}
		%>
		
		<%
			for(int i = 0; i < third_list.size(); i++){
		%>
			<ul>
	
				<li>
					<b><%=third_list.get(i).getTitle() %></b>
					<span><%=third_list.get(i).getContent() %></span>
				</li>
	
			</ul>
		<%
			}
		%>
</body>
</html>