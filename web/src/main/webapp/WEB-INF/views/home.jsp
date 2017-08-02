<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.jat.persistence.entity.Press"%>
<%@ page import="com.jat.persistence.entity.Comment"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<jsp:include page="/WEB-INF/views/layout/header.jsp" />


<body>
	<div id="mainbar">
		<!--pagination *   -->
		<jsp:include page="/WEB-INF/views/layout/pagination.jsp" />
		<div id="question">

			<%
				List<Press> lPress = (List<Press>) request.getAttribute("listPress");
			%>
			<%
				for (Press press : lPress) {
					pageContext.setAttribute("title", press.getTitle());
					if (press.getTitle() == null) {
						continue;
					}
					pageContext.setAttribute("link", press.getLink());
					pageContext.setAttribute("publisher", press.getPublisher());

					pageContext.setAttribute("time", press.getTime());
					pageContext.setAttribute("shortIntro", press.getShortIntro());

					String hrefString = "/web/getLinkInfo?url=" + press.getLink();
					pageContext.setAttribute("hrefString", hrefString);

					List<Comment> lComment = press.getComment();
					int pos = 0, neg = 0, numCmt = lComment.size();

					for (Comment cmt : lComment) {
						double senti = cmt.getSenti();
						if (senti > 0)
							pos++;
						else
							neg++;

					}
					pageContext.setAttribute("pos", pos);
					pageContext.setAttribute("neg", neg);
					pageContext.setAttribute("numCmt", numCmt);
			%>



			<div class="question-summary" id="question-summary-45265884">
				<div class="statscontainer">
					<div class="statsarrow"></div>
					<div class="stats">
						<div class="vote">
							<div class="votes">
								<span class="vote-count-post "><strong>${pos}</strong></span>
								<div class="viewcount">positive</div>
							</div>
						</div>
						<div class="status unanswered">
							<strong>${neg}</strong>
							<div class="viewcount">negative</div>
						</div>
						<div class="status unanswered">
							<strong>${numCmt}</strong>
							<div class="viewcount">Comments</div>
						</div>
					</div>


				</div>
				<div class="summary">
					<h3>
						<a href="${hrefString}" class="question-hyperlink">${title}</a>
					</h3>
					<div class="excerpt">${shortIntro}</div>
					<div class="tags t-ios t-swift">
						<a href="/questions/tagged/ios" class="post-tag"
							title="show questions tagged 'ios'" rel="tag">ios</a>
					</div>
					<div class="started fr">
						<div class="user-info ">
							<div class="user-action-time">
								<span class="relativetime"> ${time} </span>
							</div>
							<div class="user-gravatar32">
								<a href="/users/1143361/fullmoon"><div
										class="gravatar-wrapper-32">
										<img src="https://i.stack.imgur.com/UfWH4.jpg?s=32&amp;g=1"
											alt="" width="32" height="32">
									</div></a>
							</div>
							<div class="user-details">
								<a href="">${publisher}</a>
								<div class="-flair"></div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<%
				}
			%>

			<!--pagination *   -->
			<jsp:include page="/WEB-INF/views/layout/pagination.jsp" />
		</div>
	</div>

</body>
<jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</html>