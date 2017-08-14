<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.jat.persistence.entity.Press"%>
<%@ page import="com.jat.persistence.entity.Comment"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
					if (lComment == null || lComment.size()==0){
						continue;
					}
					int pos = 0, neg = 0, posW = 0, negW = 0, numCmt = lComment.size();
					
						
					for (Comment cmt : lComment) {
						double senti = cmt.getSentiSVM();
						double sentiW = cmt.getSentiVNWord();
						if (senti >= 0)
							pos++;
						else
							neg++;
						if (sentiW >= 0)
							posW++;
						else
							negW++;

					}
					
					pageContext.setAttribute("posSentiWord", posW);
					pageContext.setAttribute("negSentiWord", negW);
					pageContext.setAttribute("posSVM", pos);
					pageContext.setAttribute("negSVM", neg);
					pageContext.setAttribute("numCmt", numCmt);
					if (press.getKeyWords() != null)
						pageContext.setAttribute("tag", press.getKeyWords());
			%>



			<div class="question-summary" id="question-summary-45265884">
				<div class="statscontainer">
					<div class="statsarrow"></div>
					<div class="stats">
						<div class="vote">
							<div class="viewcount">SVM</div>
							<div class="votes">
								<span class="vote-count-post "><strong>${posSVM}</strong></span>
								<div class="viewcount">positive</div>
							</div>
						</div>
						<div class="status unanswered">
							<strong>${negSVM}</strong>
							<div class="viewcount">negative</div>
						</div>
						<div class="status unanswered">
							<strong>${numCmt}</strong>
							<div class="viewcount">Comments</div>
						</div>
					</div>
				</div>
				<div class="statscontainer">
					<div class="statsarrow"></div>
					<div class="stats">
						<div class="vote">
							<div class="viewcount">SentiWord</div>
							<div class="votes">
								<span class="vote-count-post "><strong>${posSentiWord}</strong></span>
								<div class="viewcount">positive</div>
							</div>
						</div>
						<div class="status unanswered">
							<strong>${negSentiWord}</strong>
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
					<div class="started">
						<div class="user-info ">
							<div class="user-action-time">
								<span class="relativetime"> ${time} </span>
							</div>
							
							<div class="user-details">
								<a href="">${publisher}</a>
								<div class="-flair"></div>
							</div>
						</div>
					</div>
					<div class="excerpt">${shortIntro}</div>
					<div class="tags t-ios t-swift">
						<a href="/questions/tagged/ios" class="post-tag"
							title="show questions tagged 'ios'" rel="tag">${tag}</a>
					</div>
					
				</div>
			</div>

			<%
				}
			%>