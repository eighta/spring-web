<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

	<div class="bigtitle">Internationalization</div>
	<div class="box">views/tasks/i18n.jsp</div>
	
	<ul class="rounded-list">
		<li><a href="/spring-web/s/tasks/i18n/g">/spring-web/s/tasks/i18n/g</a> <span class="resalt">(AcceptHeaderLocaleResolver)</span></li>
		<li><a href="/spring-web/s/tasks/i18n/f">/spring-web/s/tasks/i18n/f</a> <span class="resalt">(SessionLocaleResolver)</span></li>
		<li><a href="/spring-web/s/tasks/i18n/e">/spring-web/s/tasks/i18n/e</a> <span class="resalt">(CookieLocaleResolver)</span></li>
		<li><a href="/spring-web/s/tasks/i18n/d">/spring-web/s/tasks/i18n/d</a> <span class="resalt">(JSTL)<span class="yellow">&lt;fmt:setLocale value="de_DE"/&gt;</span></span></li>	
		<li><a href="/spring-web/s/tasks/i18n/c">/spring-web/s/tasks/i18n/c</a> <span class="resalt">(JSTL)<span class="yellow">DEFAULT</span></span></li>
		<li><a href="/spring-web/s/tasks/i18n/b">/spring-web/s/tasks/i18n/b</a> <span class="resalt">messageSource.getMessage(..)<span class="yellow">Locale.GERMAN</span></span></li>
	    <li><a href="/spring-web/s/tasks/i18n/a">/spring-web/s/tasks/i18n/a</a> <span class="resalt">messageSource.getMessage(..)<span class="yellow">Locale.getDefault()</span></span></li>
	</ul>
	
</body>
</html>