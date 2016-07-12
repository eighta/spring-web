<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/site.css">

<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Josefin+Slab" />
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Ubuntu" />
<link rel="stylesheet" href="../libs/pure-release-0.6.0/pure-min.css">

<title>ViewResolver's</title>
</head>
<body>
	<c:set var="global" value="${applicationScope.GLOBAL}" />

	<div class="bigtitle">ViewResolver's</div>
	<div class="box">views/tasks/view_resolver.jsp</div>
	
	<div class="test">
		<c:out value="${global.giveMeDauthersName().getClass().getCanonicalName()}"/>
	</div>
	
	<ul class="rounded-list">
	    <li><a href="/spring-web/s/view">/spring-web/s/view</a> <span class="resalt">String return<span class="yellow">("simple/viewFromAnnotatedController")</span></span></li>
	    <li><a href="/spring-web/s/view/a">/spring-web/s/view/a</a> <span class="resalt">View return</span><span class="yellow">(InternalResourceView)</span></li>    
	    <li><a href="/spring-web/s/view/b">/spring-web/s/view/b</a> <span class="resalt">View return</span><span class="yellow">(MyInternalResourceView)</span></li>
	    <li><a href="/spring-web/s/view/c">/spring-web/s/view/c</a> <span class="resalt">ModelAndView return</span><span class="yellow">(ModelAndView)</span></li>
	</ul>
	
</body>
</html>