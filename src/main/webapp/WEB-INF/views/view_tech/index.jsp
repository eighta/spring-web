<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="http://localhost:8080/spring-web/css/site.css">

<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Josefin+Slab" />
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Ubuntu" />
<link rel="stylesheet" href="http://localhost:8080/spring-web/libs/pure-release-0.6.0/pure-min.css">

<title>View Technologies</title>
</head>
<body>
	<c:set var="global" value="${applicationScope.GLOBAL}" />

	<div class="rainbow">View Technologies</div>
	<div class="box">views/view_tech/index.jsp</div>
	
	<h1><%=response.getStatus() %></h1>
	
	<ul class="rounded-list">
		
		<li><a href="/spring-web/s/tasks/view_tech/a.json">/spring-web/s/tasks/view_tech/a.json</a> <span class="resalt">JSON</span><span class="yellow">.json</span></li>
		<li><a href="/spring-web/s/tasks/view_tech/a.pdf">/spring-web/s/tasks/view_tech/a.pdf</a> <span class="resalt">PDF</span><span class="yellow">.pdf</span></li>
		<li><a href="/spring-web/s/tasks/view_tech/a.xls">/spring-web/s/tasks/view_tech/a.xls</a> <span class="resalt">XLS</span><span class="yellow">.xls</span></li>
		<li><a href="/spring-web/s/tasks/view_tech/a">/spring-web/s/tasks/view_tech/a</a> <span class="resalt">WEB</span><span class="yellow">NOT EXTENSION</span></li>
	</ul>
	
</body>
</html>