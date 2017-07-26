<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>

<%@ page import="com.jat.persistence.entity.Press"%>
<%@ page import="com.jat.persistence.entity.Comment"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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

		List<Comment> lComment = press.getComment();
		int pos = 0, neg = 0, numCmt = lComment.size();

		for (Comment cmt : lComment) {
			double senti = cmt.getSenti() ;
			if (senti > 0)
				pos++;
			else
				neg++;

		}
		pageContext.setAttribute("pos", pos);
		pageContext.setAttribute("neg", neg);
		pageContext.setAttribute("numCmt", numCmt);
	%>

	<div id="question-header">
		<h1 itemprop="name">
			<a href="${link}" class="question-hyperlink"> ${title}</a>
		</h1>
	</div>
	<div class="post-text" itemprop="text">
		${content}
	</div>
					
	<div id="answers">



		<div id="answers-header">
			<div class="subheader answers-subheader">
				<h2>
					Comment <span itemprop="cmtCount">${numCmt}</span>
				</h2>
				<div></div>
			</div>
		</div>
		
		<%
			if (lComment != null) {
				int temp = 0;
				for (Comment comment : lComment) {
					temp++;
					pageContext.setAttribute("cmt", comment.getMessage());
					pageContext.setAttribute("senti", comment.getSenti());
		%>
		<div class="answer">
			<table>
				<tbody>
					<tr>
						<td class="votecell">
							<div class="vote">
							
								 <span itemprop="upvoteCount" class="vote-count-post high-scored-post">
								 	<fmt:formatNumber type = "number" maxIntegerDigits = "2" value = "${senti}" />
								 </span>
							</div>
						</td>

						<td class="answercell">
							<div class="post-text" itemprop="text">
								${cmt}
							</div>
					
						</td>
					</tr>

				</tbody>
			</table>
		</div>

	</div>

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