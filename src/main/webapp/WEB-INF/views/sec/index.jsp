<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="http://localhost:8080/spring-web/css/site.css">

<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Josefin+Slab" />
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Ubuntu" />
<link rel="stylesheet" href="http://localhost:8080/spring-web/libs/pure-release-0.6.0/pure-min.css">

<title>Spring Security</title>
</head>
<body>
	<c:set var="global" value="${applicationScope.GLOBAL}" />

	<div class="rainbow">Spring Security</div>
	<div class="box">views/sec/index.jsp</div>
	
	<h1><%=response.getStatus() %></h1>
	
	<sec:authorize access="isAuthenticated()">
		<h1>username: <sec:authentication property="principal.username"/></h1>
	</sec:authorize>
	
	<ul class="rounded-list">
		<sec:authorize access="isAuthenticated()">
			<li><a href="/spring-web/s/tasks/sec/logout">/spring-web/s/tasks/sec/logout</a> <span class="resalt">Logout</span><span class="yellow">(Spring Security)</span></li>
		</sec:authorize>
		<li><a href="/spring-web/s/tasks/sec/insured">/spring-web/s/tasks/sec/insured</a> <span class="resalt">Goto: Resource Insured</span><span class="yellow">(Spring Security)</span></li>
	</ul>
	
</body>
</html>