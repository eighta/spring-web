<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="http://localhost:8080/spring-web/css/site.css">

<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Josefin+Slab" />
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Ubuntu" />
<link rel="stylesheet" href="http://localhost:8080/spring-web/libs/pure-release-0.6.0/pure-min.css">

<title>Themes</title>
</head>
<body>
	<c:set var="global" value="${applicationScope.GLOBAL}" />

	<!-- banner dinamico con respecto al thema -->
	<spring:theme var="bannerImage" code="banner.image"/>
	<c:out value="${bannerImage}"/>
	<br/>
	
	<!-- la idea tambien ara cambiar los CSS del sitio atravez
	de la url defina en el properties del theme asi:
	
	<spring:theme var="styleCss" code="style.css"/>
	<c:url var="styleCssUrl" value="${styleCss}"/>
	<link type="text/css" type="stylesheet" href="${styleCssUrl}" />
	
	 -->
	
	<img id="banner" src="http://localhost:8080/spring-web${bannerImage}" 
	 width="600" height="60%" alt="Banner Image"/>
	
	<br/>	
	<div class="box">views/themes/change.jsp</div>
	
	<div><span class="t3">Choose theme:</span> 
		<a href="?tematica=base">base</a> 
		|<a href="?tematica=red">red</a>
		|<a href="?tematica=blue">blue</a>
	</div>
	
	<br/><br/><span class="neon2">Current Theme: </span> <span class="over_black">${requestContext.theme.name}</span>
	<br/><br/><span class="neon2">Current Theme (Spring way):  </span> <span class="over_black">${requestContext.theme.name}</span>
	
	<spring:theme var="currentCSS" code="style.css"/>
	<br/><br/><span class="neon2">Current CSS: </span> <c:out value="${currentCSS}"/>
	
	<hr/>
	<div class="over_black">${requestContext.theme}</div>
	<div class="over_black">${requestContext.theme.messageSource}</div>
	
</body>
</html>