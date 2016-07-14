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
	
		<li><a href="/spring-web/s/view/q">/spring-web/s/view/q</a> <span class="resalt">Tiles<span class="yellow">(Tiles)</span></span></li>
	
		<li><a href="/spring-web/s/view/p.xls">/spring-web/s/view/p.xls</a> <span class="resalt">ContentNegotiating<span class="yellow">(XLS)</span></span></li>
		<li><a href="/spring-web/s/view/p.json">/spring-web/s/view/p.json</a> <span class="resalt">ContentNegotiating<span class="yellow">(JSON)</span></span></li>
		<li><a href="/spring-web/s/view/p.pdf">/spring-web/s/view/p.pdf</a> <span class="resalt">ContentNegotiating<span class="yellow">(PDF)</span></span></li>
		<li><a href="/spring-web/s/view/p">/spring-web/s/view/p</a> <span class="resalt">ContentNegotiating<span class="yellow">(HTML)</span></span></li>
	
		<li><a href="/spring-web/s/view/o">/spring-web/s/view/o</a> <span class="resalt">String return<span class="yellow">("redirect:")</span></span></li>
		<li><a href="/spring-web/s/view/n">/spring-web/s/view/n</a> <span class="resalt">String return<span class="yellow">("fordward:")</span></span></li>
	    <li><a href="/spring-web/s/view">/spring-web/s/view</a> <span class="resalt">String return<span class="yellow">("simple/viewFromAnnotatedController")</span></span></li>
	    <li><a href="/spring-web/s/view/a">/spring-web/s/view/a</a> <span class="resalt">View return</span><span class="yellow">(InternalResourceView)</span></li>    
	    <li><a href="/spring-web/s/view/b">/spring-web/s/view/b</a> <span class="resalt">View return</span><span class="yellow">(MyInternalResourceView)</span></li>
	    <li><a href="/spring-web/s/view/c">/spring-web/s/view/c</a> <span class="resalt">ModelAndView return</span><span class="yellow">(ModelAndView)</span></li>
	    <li><a href="/spring-web/s/view/d">/spring-web/s/view/d</a> <span class="resalt">String return</span><span class="yellow">(FreeMarker)</span></li>
	    <li><a href="/spring-web/s/view/e">/spring-web/s/view/e</a> <span class="resalt">String return</span><span class="yellow">(Velocity)</span></li>
	    <li><a href="/spring-web/s/view/f">/spring-web/s/view/f</a> <span class="resalt">String return</span><span class="yellow">(BeanName-XLS-poi)</span></li>
	    <li><a href="/spring-web/s/view/g">/spring-web/s/view/g</a> <span class="resalt">String return</span><span class="yellow">(BeanName-XLS-JExcelApi)</span></li>
	    <li><a href="/spring-web/s/view/h">/spring-web/s/view/h</a> <span class="resalt">String return</span><span class="yellow">(BeanName-PDF-iText)</span></li>
	    <li><a href="/spring-web/s/view/i">/spring-web/s/view/i</a> <span class="resalt">String return</span><span class="yellow">(BeanName-JSON-Jackson2)</span></li>
	    <li><a href="/spring-web/s/view/j">/spring-web/s/view/j</a> <span class="resalt">String return</span><span class="yellow">(BeanName-XmlViewResolver::JstlView)</span></li>
	    <li><a href="/spring-web/s/view/k">/spring-web/s/view/k</a> <span class="resalt">String return</span><span class="yellow">(BeanName-XmlViewResolver::RedirectView)</span></li>
	    <li><a href="/spring-web/s/view/l">/spring-web/s/view/l</a> <span class="resalt">String return</span><span class="yellow">(BeanName-RSS)</span></li>
	    <li><a href="/spring-web/s/view/m">/spring-web/s/view/m</a> <span class="resalt">String return</span><span class="yellow">(BeanName-XML-Jackson2)</span></li>
	</ul>
	
</body>
</html>