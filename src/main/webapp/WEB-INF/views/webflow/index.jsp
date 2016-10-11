<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="http://localhost:8080/spring-web/css/site.css">

<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Josefin+Slab" />
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Ubuntu" />
<link rel="stylesheet" href="http://localhost:8080/spring-web/libs/pure-release-0.6.0/pure-min.css">

<title>Web Flow</title>
</head>
<body>
	<c:set var="global" value="${applicationScope.GLOBAL}" />

	<div class="rainbow">Web Flow</div>
	<div class="box">views/webflow/index.jsp</div>
	
	<h1><%=response.getStatus() %></h1>
	
	<ul class="rounded-list">
		<li><a href="/spring-web/s/tasks/webflow/ids">/spring-web/s/tasks/webflow/ids</a> <span class="resalt">Flows Ids</span><span class="yellow">(FlowHandlerMapping)</span></li>
		<li><a href="/spring-web/s/dogs/newDog">/spring-web/s/dogs/newDog</a> <span class="resalt">Dog Flow</span><span class="yellow">(FlowHandlerMapping)</span></li>
		<li><a href="/spring-web/s/persons/newPerson">/spring-web/s/persons/newPerson</a> <span class="resalt">Iniciar Flow</span><span class="yellow">(FlowHandlerMapping)</span></li>
	</ul>
	
</body>
</html>