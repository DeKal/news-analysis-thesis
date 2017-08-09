<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url value="/assets/lib/js/display-link.js" var="displayLinkJs" />

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<body id="display-page">
	<jsp:include page="/WEB-INF/views/layout/display-main-content.jsp" />
	<script src="${displayLinkJs}"></script>
</body>

</html>