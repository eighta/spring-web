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

	<title>
		<spring:message>
			<tiles:insertAttribute name="pageTitle"/>
		</spring:message>
	</title>
</head>

<body>
	<div class="bigtitle">Tiles3 (layout)</div>
	<div class="box">views/tiles/layout.jsp</div>
	
	<!-- Dynamic data - body of the page -->
	<div class="content">
		<tiles:insertAttribute name="content"/>
	</div>
	
	<div class="footer">
		<p><spring:message code="footer.text"/></p>
	</div>
	
	<!-- XXX TODO no termine el ejemplo del tiles del libro, continuarlo: page 126 -->
	
</body>
</html>