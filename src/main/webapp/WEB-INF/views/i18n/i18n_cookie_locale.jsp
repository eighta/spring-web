<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="http://localhost:8080/spring-web/css/site.css">

<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Josefin+Slab" />
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Ubuntu" />
<link rel="stylesheet" href="http://localhost:8080/spring-web/libs/pure-release-0.6.0/pure-min.css">

<title>Internationalization</title>
</head>
<body>
	<c:set var="global" value="${applicationScope.GLOBAL}" />

	<div class="bigtitle">CookieLocaleResolver</div>
	<div class="box">views/i18n/i18n_cookie_locale.jsp</div>
	
	<div><span class="t3">Choose language:</span> 
		<a href="?lang=es">es</a> 
		| 
		<a href="?lang=de">de</a>
	</div>
	
	<h1>Spring(&lt;spring:message/&gt;): <spring:message code="welcome" />!</h1>
	
	<a class="neon-text" href="simple">Let me See</a>
	
</body>
</html>