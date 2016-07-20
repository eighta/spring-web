<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="http://localhost:8080/spring-web/css/site.css">

<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Josefin+Slab" />
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Ubuntu" />
<link rel="stylesheet" href="http://localhost:8080/spring-web/libs/pure-release-0.6.0/pure-min.css">

<title>Controllers</title>
</head>
<body>
	<c:set var="global" value="${applicationScope.GLOBAL}" />

	<div class="rainbow">Controllers</div>
	<div class="box">views/controllers/index.jsp</div>
	
	<h1><%=response.getStatus() %></h1>
	
	<ul class="rounded-list">
		
		<li><a href="/spring-web/s/tasks/ctrls/r">/spring-web/s/tasks/ctrls/r</a> <span class="resalt">FlashAttributes</span><span class="yellow">RedirectAttributes (interface)</span></li>
		<li><a href="/spring-web/s/tasks/ctrls/p">/spring-web/s/tasks/ctrls/p</a> <span class="resalt">@SessionAttributes("person")</span></li>
		<li><a href="/spring-web/s/tasks/ctrls/n">/spring-web/s/tasks/ctrls/n</a> <span class="resalt">Redirecting</span><span class="yellow">@ModelAttribute</span></li>
		
		<li><a href="/spring-web/s/tasks/ctrls/m">/spring-web/s/tasks/ctrls/m</a> <span class="resalt">Returning NULL</span></li>
		<li><a href="/spring-web/s/tasks/ctrls/l">/spring-web/s/tasks/ctrls/l</a> <span class="resalt">Only by URL using @ModelAttribute</span><span class="yellow">@ModelAttribute</span></li>
		<li><a href="/spring-web/s/tasks/ctrls/k">/spring-web/s/tasks/ctrls/k</a> <span class="resalt">Only by URL</span><span class="yellow">Returning a Object</span></li>
		
		<li>
			<spring:url var="showUrl" value="j/{id}">
				<spring:param name="id" value="${modelId}"/>
			</spring:url>
			<a href="${showUrl}">${showUrl}</a>
			<span class="resalt">Using &lt;spring/&gt; TAGLIB</span><span class="yellow">&lt;spring:url/&gt; and &lt;spring:param/&gt; </span>
		</li>
		
		<li><a href="/spring-web/s/tasks/ctrls/i?amount=$5123.54">/spring-web/s/tasks/ctrls/i?amount=$5123.54</a> <span class="resalt">Using NumberFormat</span></li>
		<li><a href="/spring-web/s/tasks/ctrls/h/159?foo=bar">/spring-web/s/tasks/ctrls/h/159?foo=bar</a> <span class="resalt">Param's without name</span></li>
		<li><a href="/spring-web/s/tasks/ctrls/g">/spring-web/s/tasks/ctrls/g</a> <span class="resalt">Flexible Signature</span><span class="yellow">Locale, Principal, etc...</span></li>
		<li><a href="/spring-web/s/tasks/ctrls/f/69?dateOfBirth=1983-08-18">/spring-web/s/tasks/ctrls/f/69/?dateOfBirth=1983-08-18</a> <span class="resalt">By Path Variable and Specific Request Param</span><span class="yellow">@RequestMapping(value="/f/{id}/?dateOfBirth=1983-08-18")</span></li>
		<li><a href="/spring-web/s/tasks/ctrls/e/444">/spring-web/s/tasks/ctrls/e/444</a> <span class="resalt">By Path Variable-RegExp</span><span class="yellow">@RequestMapping(value = "/e/{id:[\\d]*}")</span></li>
		<li><a href="/spring-web/s/tasks/ctrls/d/102030">/spring-web/s/tasks/ctrls/d/102030</a> <span class="resalt">By Path Variable</span><span class="yellow">@RequestMapping(value="/d/{id}") - @PathVariable("id")</span></li>
		<li><a href="/spring-web/s/tasks/ctrls/c?id=1123">/spring-web/s/tasks/ctrls/c?id=1123</a> <span class="resalt">By Specific Param-Value</span><span class="yellow">@RequestMapping(value="/c", params={"id=1123"})</span></li>
		<li><a href="/spring-web/s/tasks/ctrls/b?id=456">/spring-web/s/tasks/ctrls/b?id=456</a> <span class="resalt">With Request Param</span><span class="yellow">@RequestParam("id")</span></li>
		<li><a href="/spring-web/s/tasks/ctrls/a">/spring-web/s/tasks/ctrls/a</a> <span class="resalt">Only by URL</span><span class="yellow">@RequestMapping("/a")</span></li>
	</ul>
	
</body>
</html>