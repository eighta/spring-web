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
	<c:set var="global" value="${applicationScope.GLOBAL}" />

	<h2>MVC Components</h2>
	<h4>views/tasks/mvc_components.jsp</h4>
	
	<!-- sample de import
	http://www.java2s.com/Tutorial/Java/0380__JSTL/ImportPagewithParameterPassing.htm
	 <c:import var="data" url="http://www.tutorialspoint.com"/>
		<c:out value="${data}"/>
	
	 -->
	
	<div class="test">
		<c:out value="${global.giveMeDauthersName().getClass().getCanonicalName()}"/>
	</div>
	
	<div class="title">HandlerMapping's</div>
	<table class="pure-table">
		<thead>
	        <tr>
	            <th>Bean name</th>
	            <th>Class</th>
        	</tr>
    	</thead>
		<tbody>
			<c:forEach items="${global.getHandlerMappings()}" var="handlerMapping">
				<tr>
					<td>${handlerMapping.key}</td>
					<td>${handlerMapping.value.getClass().getCanonicalName()}</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>
	
	<div class="title">HandlerAdapter's</div>
	<table class="pure-table">
		<thead>
	        <tr>
	            <th>Bean name</th>
	            <th>Class</th>
        	</tr>
    	</thead>
		<tbody>
			<c:forEach items="${global.getHandlerAdapters()}" var="handlerAdapter">
				<tr>
					<td>${handlerAdapter.key}</td>
					<td>${handlerAdapter.value.getClass().getCanonicalName()}</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>
	
	<div class="title">ViewResolver's</div>
	<table class="pure-table">
		<thead>
	        <tr>
	            <th>Bean name</th>
	            <th>Class</th>
        	</tr>
    	</thead>
		<tbody>
			<c:forEach items="${global.getViewResolvers()}" var="viewResolver">
				<tr>
					<td>${viewResolver.key}</td>
					<td>${viewResolver.value.getClass().getCanonicalName()}</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>
	
	<div class="title">HandlerExceptionResolver's</div>
	<table class="pure-table">
		<thead>
	        <tr>
	            <th>Bean name</th>
	            <th>Class</th>
        	</tr>
    	</thead>
		<tbody>
			<c:forEach items="${global.getHandlerExceptionResolvers()}" var="handlerExceptionResolver">
				<tr>
					<td>${handlerExceptionResolver.key}</td>
					<td>${handlerExceptionResolver.value.getClass().getCanonicalName()}</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>
	
	<div class="title">LocaleResolver's</div>
	<table class="pure-table">
		<thead>
	        <tr>
	            <th>Bean name</th>
	            <th>Class</th>
        	</tr>
    	</thead>
		<tbody>
			<c:forEach items="${global.getLocaleResolvers()}" var="localeResolver">
				<tr>
					<td>${localeResolver.key}</td>
					<td>${localeResolver.value.getClass().getCanonicalName()}</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>
	
	<div class="title">ThemeResolver's</div>
	<table class="pure-table">
		<thead>
	        <tr>
	            <th>Bean name</th>
	            <th>Class</th>
        	</tr>
    	</thead>
		<tbody>
			<c:forEach items="${global.getThemeResolvers()}" var="themeResolver">
				<tr>
					<td>${themeResolver.key}</td>
					<td>${themeResolver.value.getClass().getCanonicalName()}</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>
	
	<div class="title">RequestToViewNameTraslator's</div>
	<table class="pure-table">
		<thead>
	        <tr>
	            <th>Bean name</th>
	            <th>Class</th>
        	</tr>
    	</thead>
		<tbody>
			<c:forEach items="${global.getRequestToViewNameTranslators()}" var="requestToViewNameTranslator">
				<tr>
					<td>${requestToViewNameTranslator.key}</td>
					<td>${requestToViewNameTranslator.value.getClass().getCanonicalName()}</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>
	
	<div class="title">FlashManager's</div>
	<table class="pure-table">
		<thead>
	        <tr>
	            <th>Bean name</th>
	            <th>Class</th>
        	</tr>
    	</thead>
		<tbody>
			<c:forEach items="${global.getFlashMapManagers()}" var="flashMapManager">
				<tr>
					<td>${flashMapManager.key}</td>
					<td>${flashMapManager.value.getClass().getCanonicalName()}</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>
	
</body>
</html>