<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/site.css">

<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Josefin+Slab" />
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Ubuntu" />
<link rel="stylesheet" href="../libs/pure-release-0.6.0/pure-min.css">

<title>Tasks page</title>
</head>
<body>
	<c:set var="global" value="${applicationScope.GLOBAL}" />

	<h2>Tasks page</h2>
	<h4>views/tasks.jsp</h4>
	
	<div class="test">
		<c:out value="${global.giveMeDauthersName().getClass().getCanonicalName()}"/>
	</div>
	
	<ul>
	  <li><a href="tasks/mvc_components">Ver MVC Components</a></li>
	</ul>
	
</body>
</html>