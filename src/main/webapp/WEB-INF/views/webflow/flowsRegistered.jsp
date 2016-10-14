<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="http://localhost:8080/spring-web/css/site.css">

<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Josefin+Slab" />
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Ubuntu" />
<link rel="stylesheet" href="http://localhost:8080/spring-web/libs/pure-release-0.6.0/pure-min.css">

<title>Web Flow</title>
</head>
<body>
	<c:set var="global" value="${applicationScope.GLOBAL}" />

	<div class="rainbow">Flows Registered</div>
	<div class="box">views/webflow/flowsRegistered.jsp</div>
	
	<h1><%=response.getStatus() %></h1>
	
	<table class="pure-table">
		<thead>
	        <tr>
	            <th>Flow id</th>
	            <th>EMPTY</th>
	            <th>EMPTY</th>
        	</tr>
    	</thead>
		<tbody>
			<c:forEach items="${stringList}" var="flowId">
				<tr>
					<td>${flowId}</td>
					<td></td>
					<td></td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>
	
</body>
</html>