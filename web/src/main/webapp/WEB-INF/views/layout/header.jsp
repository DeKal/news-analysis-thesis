<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url value="/assets/css/header.css" var="mainCss" />
<spring:url value="/assets/css/main.css" var="headerCss" />
<spring:url value="/assets/css/footer.css" var="footerCss" />


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link href="${headerCss}" rel="stylesheet" />
	<link href="${mainCss}" rel="stylesheet" />
	

</head>


<div id="header_web" class="width_common sliding-u-l-r">
	<div id="header_main">
		<div class="header_logo width_common">
			<a href="/">
				<img src="<c:url value = "/assets/image/jat_logo.png"/>" alt="JAT logo" class="left logo_web">
			</a>
			<div class="banner_728x90 banner_top right"></div>
		</div>
		<div class="team-signature">
		</div>

	</div>
</div>