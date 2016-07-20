<%@ page isErrorPage="true" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="http://localhost:8080/spring-web/css/site.css">

<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Josefin+Slab" />
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Ubuntu" />
<link rel="stylesheet" href="http://localhost:8080/spring-web/libs/pure-release-0.6.0/pure-min.css">
<title>q-Simple</title>
</head>
<body>

	<div class="bigtitle-medium">q-Simple</div>
	<div class="box">views/controllers/q.jsp</div>

	 <br><span class="text-green">getSessionAttribute(person): </span><span class="text-yellow">${person}</span>
	 <br><span class="text-green">getFlashAttribute(anotherPerson): </span><span class="text-yellow">${anotherPerson}</span>
	 <br><span class="text-green">getFlashAttribute(theAnotherPerson): </span><span class="text-yellow">${theAnotherPerson}</span>

</body>
</html>