<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.jat.persistence.entity.Users"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
<%
List<Users> users = (List<Users>) request.getAttribute( "users" );
%>
<%
for (Users user : users) {
pageContext.setAttribute("firstname", user.getFirstName() );
pageContext.setAttribute("lastname", user.getLastName() );
%>
<div>User:</div>
<div>${firstname} ${lastname} </div>

<%
}
%>
</body>
</html>