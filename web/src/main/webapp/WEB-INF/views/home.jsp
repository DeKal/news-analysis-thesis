<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<jsp:include page="/WEB-INF/views/layout/header.jsp" />


<body>
	<div id="mainbar">
		<!--pagination *   -->
		<jsp:include page="/WEB-INF/views/layout/pagination.jsp" />
		<div id="question">

			<jsp:include page="/WEB-INF/views/layout/home-main-content.jsp" />
			<!--pagination *   -->
			<jsp:include page="/WEB-INF/views/layout/pagination.jsp" />
		</div>
	</div>

</body>
<jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</html>