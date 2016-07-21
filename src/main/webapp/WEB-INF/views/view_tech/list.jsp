<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="http://localhost:8080/spring-web/css/site.css">

<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Josefin+Slab" />
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Ubuntu" />
<link rel="stylesheet" href="http://localhost:8080/spring-web/libs/pure-release-0.6.0/pure-min.css">

<title>View Technologies</title>
</head>
<body>
	<c:set var="global" value="${applicationScope.GLOBAL}" />

	<div class="bigtitle-medium">List</div>
	<div class="box">views/view_tech/list.jsp</div>
	
	<h1><%=response.getStatus() %></h1>
	
	<table class="pure-table pure-table-horizontal"">
		<thead>
	        <tr>
	            <th>Count</th>
	            <th>First Name</th>
	            <th>Last Name</th>
	            <th>Date of Birth</th>
        	</tr>
    	</thead>
		<tbody>
			<c:forEach items="${persons}" var="person" varStatus="loopCounter">
				<tr>
					<td>${loopCounter.index +1}</td>
					<td>${person.firstName}</td>
					<td>${person.lastName}</td>
					<td><fmt:formatDate value="${person.dateOfBirth}" pattern="y MMMM d"/></td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>
	
	
</body>
</html>