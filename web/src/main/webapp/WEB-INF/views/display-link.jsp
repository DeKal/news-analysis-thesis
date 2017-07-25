<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.jat.persistence.entity.Press"%>
<%@ page import="com.jat.persistence.entity.Comment"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<body>


	<%
		Press press = (Press) request.getAttribute("press");
	%>
	<%
		pageContext.setAttribute("title", press.getTitle());
	
		pageContext.setAttribute("link", press.getLink());
		pageContext.setAttribute("publisher", press.getPublisher());
		pageContext.setAttribute("content", press.getContent());
		pageContext.setAttribute("time", press.getTime());
		pageContext.setAttribute("shortIntro", press.getShortIntro());



	%>

	<div id="question-header">
		<h1 itemprop="name">
			<a
				href="${link}"
				class="question-hyperlink"> ${title}</a>
		</h1>
	</div>
	


	<div>Content: ${content}</div>
	</br>
	<%
		List<Comment> lComment = press.getComment();
		if (lComment != null) {
			int temp = 0;
			for (Comment comment : lComment) {
				temp++;
				pageContext.setAttribute("cmtCount", temp);
				pageContext.setAttribute("cmt", comment.getMessage());
				pageContext.setAttribute("senti", comment.getSenti());
	%>
	<div>Comment ${cmtCount} : ${cmt} with senti   ${senti}</div>
	<br />
	<%
		}
		} else {
	%>
	<div>No Comment</div>
	<%
		}
	%>

</body>
</html>