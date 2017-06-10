<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.jat.persistence.entity.Press"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
<%
List<Press> lPress = (List<Press>) request.getAttribute( "listPress" );
%>
<%
for (Press press :lPress) {
	pageContext.setAttribute("link", press.getLink() );
	String hrefString =  "getLinkInfo?url=" +  press.getLink();
	pageContext.setAttribute("hrefString", hrefString );
%>
<div>
<a href = "${hrefString}" > ${link} </a>
</div>
<%
}
%>
</body>
</html>