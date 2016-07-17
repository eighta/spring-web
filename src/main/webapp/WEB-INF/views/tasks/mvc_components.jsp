<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/site.css">

<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Josefin+Slab" />
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Ubuntu" />
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Bangers" />
<link rel="stylesheet" href="../libs/pure-release-0.6.0/pure-min.css">

<title>MVC Components</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<c:set var="global" value="${applicationScope.GLOBAL}" />
	
	<div class="bigTitle">MVC Components</div>
	<div class="box">views/tasks/mvc_components.jsp</div>
	
	<!-- sample de import
	http://www.java2s.com/Tutorial/Java/0380__JSTL/ImportPagewithParameterPassing.htm
	 	c:import var="data" url="URL FUENTE" />
		c:out value="${data}"/>
	 -->
	
	<div class="test">
		<c:out value="${global.giveMeDauthersName().getClass().getCanonicalName()}"/>
	</div>
	
	<div>
		<a href="handler_mapping" class="title">HandlerMapping's</a>
		<span class="readable">org.springframework.web.servlet.<span class="neon">HandlerMapping</span></span>
	</div>
	
	<table class="pure-table">
		<thead>
	        <tr>
	            <th>Bean name</th>
	            <th>Class</th>
	            <th>Order</th>
        	</tr>
    	</thead>
		<tbody>
			<c:forEach items="${global.getHandlerMappings()}" var="handlerMapping">
				<tr>
					<td>${handlerMapping.key}</td>
					<td>${handlerMapping.value.getClass().getCanonicalName()}</td>
					<td>${handlerMapping.value.getOrder()}</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>
	
	<div>
		<span class="title">HandlerAdapter's</span>
		<span>org.springframework.web.servlet.<span class="neon">HandlerAdapter</span></span>
	</div>
	<table class="pure-table">
		<thead>
	        <tr>
	            <th>Bean name</th>
	            <th>Class</th>
	            <th>Order</th>
        	</tr>
    	</thead>
		<tbody>
			<c:forEach items="${global.getHandlerAdapters()}" var="handlerAdapter">
				<tr>
					<td>${handlerAdapter.key}</td>
					<td>${handlerAdapter.value.getClass().getCanonicalName()}</td>
					<td>NO ORDER</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>
	
	<div>
		<a href="view_resolver" class="title">ViewResolver's</a>
		<span>org.springframework.web.servlet.<span class="neon">ViewResolver</span></span>
	</div>
	<table class="pure-table">
		<thead>
	        <tr>
	            <th>Bean name</th>
	            <th>Class</th>
	            <th>Order</th>
        	</tr>
    	</thead>
		<tbody>
			<c:forEach items="${global.getViewResolvers()}" var="viewResolver">
				<tr>
					<td>${viewResolver.key}</td>
					<td>${viewResolver.value.getClass().getCanonicalName()}</td>
					<td>${viewResolver.value.getOrder()}</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>
	
	<div>
		<a href="errors" class="title">HandlerExceptionResolver's</a>
		<span>org.springframework.web.servlet.<span class="neon">HandlerExceptionResolver</span></span>
	</div>
	<table class="pure-table">
		<thead>
	        <tr>
	            <th>Bean name</th>
	            <th>Class</th>
	            <th>Order</th>
        	</tr>
    	</thead>
		<tbody>
			<c:forEach items="${global.getHandlerExceptionResolvers()}" var="handlerExceptionResolver">
				<tr>
					<td>${handlerExceptionResolver.key}</td>
					<td>${handlerExceptionResolver.value.getClass().getCanonicalName()}</td>
					<td>${handlerExceptionResolver.value.getOrder()}</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>
	
	<div>
		<span class="title">LocaleResolver's</span>
		<span>org.springframework.web.servlet.<span class="neon">LocaleResolver</span></span>
	</div>
	<table class="pure-table">
		<thead>
	        <tr>
	            <th>Bean name</th>
	            <th>Class</th>
	            <th>Order</th>
        	</tr>
    	</thead>
		<tbody>
			<c:forEach items="${global.getLocaleResolvers()}" var="localeResolver">
				<tr>
					<td>${localeResolver.key}</td>
					<td>${localeResolver.value.getClass().getCanonicalName()}</td>
					<td>NO ORDER</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>
	
	<div>
		<span class="title">ThemeResolver's</span>
		<span>org.springframework.web.servlet.<span class="neon">ThemeResolver</span></span>
	</div>
	<table class="pure-table">
		<thead>
	        <tr>
	            <th>Bean name</th>
	            <th>Class</th>
	            <th>Order</th>
        	</tr>
    	</thead>
		<tbody>
			<c:forEach items="${global.getThemeResolvers()}" var="themeResolver">
				<tr>
					<td>${themeResolver.key}</td>
					<td>${themeResolver.value.getClass().getCanonicalName()}</td>
					<td>NOT ORDER</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>
	
	<div>
		<span class="title">RequestToViewNameTranslator's</span>
		<span>org.springframework.web.servlet.RequestToViewNameTranslator</span>
	</div>
	<table class="pure-table">
		<thead>
	        <tr>
	            <th>Bean name</th>
	            <th>Class</th>
	            <th>Order</th>
        	</tr>
    	</thead>
		<tbody>
			<c:forEach items="${global.getRequestToViewNameTranslators()}" var="requestToViewNameTranslator">
				<tr>
					<td>${requestToViewNameTranslator.key}</td>
					<td>${requestToViewNameTranslator.value.getClass().getCanonicalName()}</td>
					<td>${requestToViewNameTranslator.value.getOrder()}</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>
	
	<div>
		<span class="title">FlashManager's</span>
		<span>org.springframework.web.servlet.FlashMapManager</span>
	</div>
	
	<table class="pure-table">
		<thead>
	        <tr>
	            <th>Bean name</th>
	            <th>Class</th>
	            <th>Order</th>
        	</tr>
    	</thead>
		<tbody>
			<c:forEach items="${global.getFlashMapManagers()}" var="flashMapManager">
				<tr>
					<td>${flashMapManager.key}</td>
					<td>${flashMapManager.value.getClass().getCanonicalName()}</td>
					<td>${flashMapManager.value.getOrder()}</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>
	
</body>
</html>