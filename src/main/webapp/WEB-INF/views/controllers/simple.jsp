<%@ page isErrorPage="true" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="http://localhost:8080/spring-web/css/site.css">

<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Josefin+Slab" />
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Ubuntu" />
<link rel="stylesheet" href="http://localhost:8080/spring-web/libs/pure-release-0.6.0/pure-min.css">
<title>Controllers-Simple</title>
</head>
<body>

	<div class="bigtitle-medium">Controllers-Simple</div>
	<div class="box">views/controllers/simple.jsp</div>

	 <br><span class="text-green">method: </span><span class="text-yellow">${method}</span>
	 <br><span class="text-green">someValue: </span><span class="text-yellow">${someValue}</span>
	 <br><span class="text-green">nacimiento: </span><span class="text-yellow">${nacimiento}</span>
	 
	 <br><br>
	 <a href="/spring-web/s/tasks/ctrls/q">get SessionAttribute(person) </a>

</body>
</html>