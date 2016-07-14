<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="http://localhost:8080/spring-web/s/css/site.css">
<link rel="stylesheet" href="http://localhost:8080/spring-web/libs/pure-release-0.6.0/pure-min.css">

<title>contentNegotiationView.jsp</title>
</head>
<body>
	<div class="t3">contentNegotiationView.jsp</div>
	<div class="box">views/simple/contentNegotiationView.jsp</div>
	
	<table class="pure-table">
		<thead>
	        <tr>
	            <th>Tittle Book</th>
        	</tr>
    	</thead>
		<tbody>
	
			<c:forEach items="${stringList}" var="book">
				<tr>
					<td>${book}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	
</body>
</html>