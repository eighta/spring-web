<%@ page isErrorPage="true" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="http://localhost:8080/spring-web/css/site.css">

<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Josefin+Slab" />
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Ubuntu" />
<link rel="stylesheet" href="http://localhost:8080/spring-web/libs/pure-release-0.6.0/pure-min.css">
<title>Un error? Que raro!</title>
</head>
<body>

	<div class="bigtitle-medium">ENTIRE-WEBAPP-ERROR</div>
	<div class="box">views/errors/entire_webapp_error.jsp</div>

	<div class="clearfix float-my-children">
		<img id="banner" src="http://localhost:8080/spring-web/images/error_icon.png" 
		 	width="150" />
		 
		 <div>
			 <span class="text-green">Response Status-Code: </span><span class="text-yellow-big"><%=response.getStatus() %></span>
			 <br><span class="text-green">Exception: </span><span class="text-yellow"><%=exception.getClass().getCanonicalName() %></span>
			 <br><span class="text-green">Message: </span><span class="text-yellow"><%=exception.getMessage() %></span>
			 <br><span class="text-green">url: </span><span class="text-yellow">${url}</span>
		 </div>
	 </div>
</body>
</html>