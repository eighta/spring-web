<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="http://localhost:8080/spring-web/css/site.css">

<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Josefin+Slab" />
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Ubuntu" />
<link rel="stylesheet" href="http://localhost:8080/spring-web/libs/pure-release-0.6.0/pure-min.css">

<title>Web Flow - Estallada</title>
</head>
<body>
	<c:set var="global" value="${applicationScope.GLOBAL}" />

	<div class="rainbow">Web Flow - Estallada</div>
	<div class="box">views/webflow/estallada.jsp</div>
	
	<h1><%=response.getStatus() %></h1>
	
	<h2>Exception is: <%= exception %> </h2>
	
</body>
</html>