<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="http://localhost:8080/spring-web/css/site.css">

<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Josefin+Slab" />
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Ubuntu" />
<link rel="stylesheet" href="http://localhost:8080/spring-web/libs/pure-release-0.6.0/pure-min.css">

<title>Forms</title>
</head>
<body>
	<c:set var="global" value="${applicationScope.GLOBAL}" />

	<div class="rainbow">Forms</div>
	<div class="box">views/forms/index.jsp</div>
	
	<h1><%=response.getStatus() %></h1>
	
	<ul class="rounded-list">
		<li><a href="/spring-web/s/tasks/forms/a">/spring-web/s/tasks/forms/a</a> <span class="resalt">Go to Form</span></li>
		<li><a href="/spring-web/s/tasks/forms/b">/spring-web/s/tasks/forms/b</a> <span class="resalt">Go to Form Validations</span></li>
	</ul>
	
</body>
</html>