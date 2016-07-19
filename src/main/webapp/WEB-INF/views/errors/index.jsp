<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="http://localhost:8080/spring-web/css/site.css">

<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Josefin+Slab" />
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Ubuntu" />
<link rel="stylesheet" href="http://localhost:8080/spring-web/libs/pure-release-0.6.0/pure-min.css">

<title>Exceptions</title>
</head>
<body>
	<c:set var="global" value="${applicationScope.GLOBAL}" />

	<div class="rainbow">Exceptions</div>
	<div class="box">views/errors/index.jsp</div>
	
	<h1><%=response.getStatus() %></h1>
	
	<ul class="rounded-list">
		<li><a href="/spring-web/s/tasks/errors/g">/spring-web/s/tasks/errors/g</a> <span class="resalt">Model-data in @ControllerAdvice</span><span class="yellow">(desde Controller)</span></li>
		<li><a href="/spring-web/s/tasks/errors/f">/spring-web/s/tasks/errors/f</a> <span class="resalt">Lanzar ResponseStatusException</span><span class="yellow">(desde Controller)</span></li>
		<li><a href="/spring-web/s/tasks/errors/none">/spring-web/s/tasks/errors/none</a> <span class="resalt">Ir a una pagina que no tiene request mapping</span></li>
		<li><a href="/spring-web/s/tasks/errors/c">/spring-web/s/tasks/errors/c</a> <span class="resalt">Lanzar Error 404</span><span class="yellow">(desde Controller)</span></li>
		<li><a href="/spring-web/s/tasks/errors/b">/spring-web/s/tasks/errors/b</a> <span class="resalt">Lanzar Error 407</span><span class="yellow">(desde JSP)</span></li>
		<li><a href="/spring-web/s/tasks/errors/d">/spring-web/s/tasks/errors/d</a> <span class="resalt">Lanzar DataAccessException (no mapeada)</span><span class="yellow">(desde Controller)</span></li>
		<li><a href="/spring-web/s/tasks/errors/a">/spring-web/s/tasks/errors/a</a> <span class="resalt">Lanzar EightaException</span><span class="yellow">(desde Controller)</span></li>
		<li><a href="/spring-web/s/tasks/errors/e">/spring-web/s/tasks/errors/e</a> <span class="resalt">Lanzar SophieException</span><span class="yellow">(desde Controller)</span></li>
	</ul>
	
</body>
</html>