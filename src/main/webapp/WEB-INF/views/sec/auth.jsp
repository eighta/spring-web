<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="http://localhost:8080/spring-web/css/site.css">

<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Josefin+Slab" />
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Ubuntu" />
<link rel="stylesheet" href="http://localhost:8080/spring-web/libs/pure-release-0.6.0/pure-min.css">

<title>Authentication</title>
</head>
<body>
	<c:set var="global" value="${applicationScope.GLOBAL}" />

	<div class="rainbow">Authentication</div>
	<div class="box">views/sec/auth.jsp</div>
	
	<h1><%=response.getStatus() %></h1>
	
	<div id="form">
		<form action="<c:url value='/s/login'/>" method="post">
			Username
			<input type='text' id='username' name='username'/>
		
			Password
			<input type='password' id='password' name='password'/>
			
			<input type="submit" value="Submit">
		</form>
		
	</div>
	
</body>
</html>