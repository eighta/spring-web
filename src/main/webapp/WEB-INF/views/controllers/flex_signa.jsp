<%@ page isErrorPage="true" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="http://localhost:8080/spring-web/css/site.css">

<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Josefin+Slab" />
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Ubuntu" />
<link rel="stylesheet" href="http://localhost:8080/spring-web/libs/pure-release-0.6.0/pure-min.css">
<title>Flexible Signature</title>
</head>
<body>

	<div class="bigtitle-medium">Flexible Signature</div>
	<div class="box">views/controllers/simple.jsp</div>
	 
	 <br><span class="text-green">method: </span><span class="text-yellow">${method}</span>
	 <br><span class="text-green">Request: </span><span class="text-yellow">${requestFacade}</span>
	 <br><span class="text-green">Session: </span><span class="text-yellow">${standardSessionFacade}</span>
	 <br><span class="text-green">Principal: </span><span class="text-yellow">IS NULL (TILL NOW)</span>
	 <br><span class="text-green">Locale: </span><span class="text-yellow">${locale}</span>
	 <br><span class="text-green">Response: </span><span class="text-yellow">${responseFacade}</span>
	 <br><span class="text-green">Model: </span>	 
	 
	 <table class="pure-table pure-table-horizontal"">
		<thead>
	        <tr>
	            <th>Model name</th>
	            <th>Model value</th>
        	</tr>
    	</thead>
		<tbody>
			<c:forEach items="${bindingAwareModelMap}" var="model" varStatus="loopCounter">
				<!-- <tr class='${loopCounter.count%2==0?"pure-table-odd":""}'> -->
				<tr>
					<td>${model.key}</td>
					<td>${model.value}</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>

</body>
</html>