<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
	
<%@ page import="java.util.List"%>
<%@ page import="com.jat.persistence.entity.Press"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<%
		Press press = (Press) request.getAttribute("press");
	%>
	<%
		pageContext.setAttribute("link", press.getLink());
		pageContext.setAttribute("content", press.getContent());
	%>
	<div>
		Link: ${link} 
	</div>
	</br>
	<div>Content: ${content}</div>
	</br>
	<%
		List<String> lComment = press.getComment();
		if (lComment != null) {
			int temp = 0;
			for (String comment : lComment) {
				temp++;
				pageContext.setAttribute("cmtCount", temp);
				pageContext.setAttribute("cmt", comment);
	%>
	<div>Comment ${cmtCount} : ${cmt}</div>
	<br />
	<%
		}
		} else {
	%>
		<div> No Comment</div>
	<%

		}
	%>

</body>
</html>