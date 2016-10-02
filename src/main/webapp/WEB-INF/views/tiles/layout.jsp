<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="../css/site.css">

	<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Josefin+Slab" />
	<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Ubuntu" />
	<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Open+Sans" />
	<link rel="stylesheet" href="../libs/pure-release-0.6.0/pure-min.css">
	
	<link rel="stylesheet" type="text/css" href="../css/themify/themify-icons.css">
	<link rel="stylesheet" type="text/css" href="../css/webflow-styles.css">
	
	<script src="http://localhost:8080/spring-web/js/jquery-3.1.0.js"></script>

	<title>
		<%-- <spring:message></spring:message> --%>
		<tiles:insertAttribute name="pageTitle"/>
	</title>
</head>

<body>
	<div class="bigtitle">
		<tiles:insertAttribute name="pageTitle"/>
	</div>
	<div class="box">
		<tiles:insertAttribute name="pageLocation"/>
	</div>
	<br/>
	<div class="box serious_box">
		<!-- http://forum.spring.io/forum/spring-projects/web/web-flow/45365-flowexecutionkey -->
		<%-- <h2>${flowExecutionKey}</h2> --%>
		<h2>${flowExecutionUrl}</h2>
	</div>
	
	<!-- Dynamic data - body of the page -->
	<div class="content">
		<tiles:insertAttribute name="content"/>
	</div>
	
	<%-- <div class="footer">
		<p><spring:message code="footer.text"/></p>
	</div> --%>
	
	<!-- XXX TODO no termine el ejemplo del tiles del libro, continuarlo: page 126 -->
	
</body>
</html>