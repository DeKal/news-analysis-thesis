<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url value="/assets/css/header.css" var="headerCss" />
<spring:url value="/assets/css/display.css" var="displayCss" />
<spring:url value="/assets/css/main.css" var="mainCss" />
<spring:url value="/assets/css/footer.css" var="footerCss" />
<spring:url value="/assets/lib/bootstrap/css/bootstrap.min.css" var="bootstrapCss" />
<spring:url value="/assets/lib/bootstrap/js/bootstrap.min.js" var="bootstrapMinJs" />
<spring:url value="/assets/lib/bootstrap/js/bootstrap.js" var="bootstrapJs" />
<spring:url value="/assets/lib/bootstrap/vendor/tether.min.js" var="tether" />
<spring:url value="/assets/lib/jquery/jquery-3.2.1.min.js" var="jquery" />
<spring:url value="/assets/lib/chart/Chart.bundle.js" var="chartJs" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<link href="${headerCss}" rel="stylesheet" />
	<link href="${mainCss}" rel="stylesheet" />
	<link href="${displayCss}" rel="stylesheet" />
	<link href="${bootstrapCss}" rel="stylesheet" />
	<script src="${jquery}"></script>
	<script src="${tether}"></script>
	<script src="${bootstrapJs}"></script>
	<script src="${bootstrapMinJs}"></script>
	<script src="${chartJs}"></script>

</head>


<div id="header_web" class="width_common sliding-u-l-r">
	<div id="header_main">
		<div class="header_logo width_common">
			<a href="/"> <img
				src="<c:url value = "/assets/image/jat_logo.png"/>" alt="JAT logo"
				class="left logo_web">
			</a>
			<div class="banner_728x90 banner_top right"></div>
		</div>
		<div class="team-signature"></div>

	</div>
</div>