<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>

<%@ page import="com.jat.persistence.entity.Press"%>
<%@ page import="com.jat.persistence.entity.Comment"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	Press press = (Press) request.getAttribute("press");
	pageContext.setAttribute("title", press.getTitle());

	pageContext.setAttribute("link", press.getLink());
	pageContext.setAttribute("publisher", press.getPublisher());
	pageContext.setAttribute("content", press.getContent());
	pageContext.setAttribute("time", press.getTime());
	pageContext.setAttribute("shortIntro", press.getShortIntro());

	List<Comment> lComment = press.getComment();
	int pos = 0, neg = 0, neu = 0, numCmt = lComment.size();

	for (Comment cmt : lComment) {
		double senti = cmt.getSenti();
		if (senti > 0.4)
			pos++;
		else if (senti < - 0.4)
			neg++;

	}
	neu = numCmt - pos - neg;
	
	pageContext.setAttribute("pos", pos);
	pageContext.setAttribute("neg", neg);
	pageContext.setAttribute("neu", neu);
	pageContext.setAttribute("numCmt", numCmt);
%>
<div id="main-body">

	<div class="head_detail full_width">
		<h1 id="btn_exp_edit" class="title">
			<a href="${link}" class="question-hyperlink"> ${title}</a>
		</h1>
		<div class="font11 blue margin8_tb">
			${time}
			<div style="clear: both;"></div>
		</div>
		<h2 class="intro font14 rendering" style="line-height: 20px;">${shortIntro}</h2>

		<div style="text-align: center;">
			<div style="display: inline-block;"></div>
		</div>
	</div>


	<!-- <div class="post-text" itemprop="text">${content}</div> -->
	<div id="comments" class="full_width">
		<div role="tabpanel">
			<ul class="nav nav-pills" id="myTab" role="tablist">
				<li class="nav-item"><a class="nav-link active" id="home-tab"
					data-toggle="tab" href="#home" role="tab" aria-controls="home"
					aria-expanded="true">Comments ${numCmt}</a></li>
				<li class="nav-item"><a class="nav-link" id="profile-tab"
					data-toggle="tab" href="#profile" role="tab"
					aria-controls="profile">Chart</a></li>
			</ul>
			<div class="tab-content" id="myTabContent">
				<div role="tabpanel" class="tab-pane fade show active" id="home"
					aria-labelledby="home-tab">
					<div id="answers">


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
										<td class="answercell">
											<div class="post-text" itemprop="text">${cmt}</div>

										</td>

										<td class="votecell">
											<div class="vote">

												<span itemprop="upvoteCount"
													class="vote-count-post high-scored-post"> <fmt:formatNumber
														type="number" maxIntegerDigits="2" value="${senti}" />
												</span>
											</div>
										</td>

									</tr>

								</tbody>
							</table>
						</div>
						<%
							}
						%>
					</div>

					<%
						} else {
					%>
					<div>No Comment</div>
					<%
						}
					%>
				</div>
				<div class="tab-pane fade" id="profile" role="tabpanel"
					aria-labelledby="profile-tab">
					<canvas id="myChart" style ="max-width: 600px !important; max-height : 600px !important;margin-top: 50px;"></canvas>
					<script>
						var ctx = document.getElementById("myChart")
								.getContext('2d');
						var data = {
					        labels: ['Negative', 'Positive', 'Neutral'],
					        datasets: [{
					            label: '# of Votes',
					            data: [${neg}, ${pos}, ${neu}],
					            backgroundColor: [
					                'rgba(255, 99, 132, 0.2)',
					                'rgba(54, 162, 235, 0.2)',
					                'rgba(255, 206, 86, 0.2)'
					            ],
					            borderColor: [
					                'rgba(255,99,132,1)',
					                'rgba(54, 162, 235, 1)',
					                'rgba(255, 206, 86, 1)'
					            ],
					            borderWidth: 1
					        }]
						};

						var options = {
	
						}
						var myPieChart = new Chart(ctx, {
							type : 'pie',
							data : data,
							options : options
						});
					</script>
				</div>

			</div>
		</div>

	</div>
</div>