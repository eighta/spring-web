<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/site.css">

<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Josefin+Slab" />
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Ubuntu" />
<link rel="stylesheet" href="../libs/pure-release-0.6.0/pure-min.css">

<title>HandlerMapping's</title>
</head>
<body>
	<c:set var="global" value="${applicationScope.GLOBAL}" />

	<div class="bigtitle">HandlerMapping's</div>
	<div class="box">views/tasks/handler_mapping.jsp</div>
	
	<div class="test">
		<c:out value="${global.giveMeDauthersName().getClass().getCanonicalName()}"/>
	</div>
	
	<ul class="rounded-list">
	    <li><a href="/spring-web/s/slash_plain_controller">/spring-web/s/slash_plain_controller</a></li>
	    <li><a href="/spring-web/s/slash_abstract_controller">/spring-web/s/slash_abstract_controller</a></li>
	    <li><a href="/spring-web/s/annotated_controller">/spring-web/s/annotated_controller</a></li>    
	    <li><a href="/spring-web/s/class_name_controller">/spring-web/s/className/methodName</a></li>
	    <li><a href="">List item 4</a></li>                       
	</ul>
	
	<select>
  		<option value=" ">24&#x1D57;&#x02B0;</option>
  		<option value=" ">24&#x1D57;&#x02B0;</option>
  		<option value=" ">24&#x1D57;&#x02B0;</option>
  		<option value=" ">24&#x1D57;&#x02B0;</option>
  		<option value=" ">24&#x1D57;&#x02B0;</option>
	</select>
	
</body>
</html>