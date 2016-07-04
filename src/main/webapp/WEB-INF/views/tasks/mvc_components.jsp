<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/site.css">

<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Josefin+Slab" />
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Ubuntu" />
<link rel="stylesheet" href="../libs/pure-release-0.6.0/pure-min.css">

<title>MVC Components</title>
</head>
<body>
	<h2>MVC Components</h2>
	<h4>views/tasks/mvc_components.jsp</h4>
	
	<div class="test">
		<c:set var="global" value="${applicationScope.GLOBAL}" />
		<c:out value="${global.giveMeDauthersName()}"/>
	</div>
	
	<div class="title">HandlerMapping's</div>
	<table class="pure-table">
    <thead>
        <tr>
            <th>#</th>
            <th>Make</th>
            <th>Model</th>
            <th>Year</th>
        </tr>
    </thead>

    <tbody>
        <tr>
            <td>1</td>
            <td>Honda</td>
            <td>Accord</td>
            <td>2009</td>
        </tr>

        <tr>
            <td>2</td>
            <td>Toyota</td>
            <td>Camry</td>
            <td>2012</td>
        </tr>

        <tr>
            <td>3</td>
            <td>Hyundai</td>
            <td>Elantra</td>
            <td>2010</td>
        </tr>
    </tbody>
</table>
	
	
	<br/><br/><br/>
	<div class="title">HandlerAdapter's</div>
	<div class="title">ViewResolver's</div>
	<div class="title">HandlerExceptionsResolver's</div>
	<div class="title">LocaleResolver's</div>
	<div class="title">ThemeResolver's</div>
	<div class="title">RequestToViewNameTraslator's</div>
	<div class="title">FlashManager's</div>
	
	
	
</body>
</html>