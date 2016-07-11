<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/site.css">

<title>Welcome Page (spring-web)</title>
</head>
<body>
	<div class="bigTitle">Welcome Page (spring-web)</div>
	<div class="box">views/bienvenido.jsp</div>
	
	<br/>
	<a href="tasks">
		<img src="<c:url value='/images/photo.jpg'/>"/>
	</a>
</body>
</html>