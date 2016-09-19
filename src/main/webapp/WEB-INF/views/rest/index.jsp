<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="http://localhost:8080/spring-web/css/site.css">

<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Josefin+Slab" />
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Ubuntu" />
<link rel="stylesheet" href="http://localhost:8080/spring-web/libs/pure-release-0.6.0/pure-min.css">

<script src="http://localhost:8080/spring-web/js/jquery-3.1.0.js"></script>

<script>


$( document ).ready(function() {
	$('#jquery_delete').click(function(){
		
		$.ajax({
		    url: '/spring-web/s/rest/2',
		    type: 'DELETE',
		    success: function(result) {
		        // Do something with the result
		    	 alert('good');
		    }
		});
		
	});
});

$( document ).ready(function() {
	$('#jquery_put').click(function(){
		
		var person = {
				id:98764321,
				firstName:"Milton",
				lastName:"Ochoa"
		};
		
		$.ajax({
		    url: '/spring-web/s/rest',
		    type: 'PUT',
		    //(default: 'application/x-www-form-urlencoded; charset=UTF-8')
		    //contentType: 'application/x-www-form-urlencoded',
		    data:person,
		    success: function(result) {
		        alert('good');
		    },
		    error: function(){
		    	alert('bad');
		    }
		});
		
	});
});

</script>


<title>RESTful services</title>
</head>
<body>
	<c:set var="global" value="${applicationScope.GLOBAL}" />

	<div class="rainbow">RESTful services</div>
	<div class="box">views/rest/index.jsp</div>
	
	<h1><%=response.getStatus() %></h1>
	
	<ul class="rounded-list">
	
		<li>
			<div id="jquery_delete" class="like-a" >/spring-web/s/rest/1</div>
			<span class="resalt">DELETE</span><span class="yellow">method (jQuery)</span>
		</li>
	
		<li>
			<div>
				<sf:form action="/spring-web/s/rest/1" method="delete">
					<input type="submit" value="/spring-web/s/rest/1"/>
				</sf:form>
			</div>
			<span class="resalt">DELETE</span><span class="yellow">method (&lt;sf:form method='DELETE'&gt;)</span>
		</li>
		
		<li>
			<div id="jquery_put" class="like-a" >/spring-web/s/rest</div>
			<span class="resalt">PUT</span><span class="yellow">method (jQuery)</span>
		</li>
	
		<li>
			<div>
				<sf:form action="/spring-web/s/rest" method="put" modelAttribute="person">
					<sf:hidden path="firstName"/>
					<input type="submit" value="/spring-web/s/rest"/>
				</sf:form>
			</div>
			<span class="resalt">PUT</span><span class="yellow">method (&lt;sf:form  method='put'&gt;)</span>
			<h3>(NOT WORKING by HiddenHttpMethodFilter - a BUG?)</h3>
		</li>
	
		<li>
			<div>
				<form action="/spring-web/s/rest" method="post">
					<input type="hidden" name="id" value="89" />
					<input type="submit" value="/spring-web/s/rest"/>
					<input type="hidden" name="_method" value="put" />
				</form>
			</div>
			<span class="resalt">PUT</span><span class="yellow">method (&lt;form  method='post'&gt;)</span>
			<h3>(NOT WORKING by HiddenHttpMethodFilter - a BUG?)</h3>
		</li>
		
		<li>
			<div>
				<form action="/spring-web/s/rest" method="post">
					<input type="hidden" name="firstName" value="FORM-POST-FIXED"/>
					<input type="submit" value="/spring-web/s/rest"/>
				</form>
			</div>
			<span class="resalt">POST</span><span class="yellow">method (&lt;form&gt;)</span>
		</li>
		 
		<li><a href="/spring-web/s/rest/me">/spring-web/s/rest/me</a> <span class="resalt">GET</span><span class="yellow">method</span></li>
		 
	</ul>
	
</body>
</html>