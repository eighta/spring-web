<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="http://localhost:8080/spring-web/css/site.css">

<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Josefin+Slab" />
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Ubuntu" />
<link rel="stylesheet" href="http://localhost:8080/spring-web/libs/pure-release-0.6.0/pure-min.css">

<title>The Form</title>
</head>
<body>
	<c:set var="global" value="${applicationScope.GLOBAL}" />

	<div class="rainbow">The Form Validations</div>
	<div class="box">views/forms/form_validation.jsp</div>
	
	<div id="form">
		
		<!-- SE GENERA LA URL CON LOS PARAMETROS ENVIADOS DESDE EL CONTROLLER -->
		<spring:url value="persons/{id}" var="editUrl">
			<spring:param name="id" value="${person.id}"/>
		</spring:url>
	
		<sf:form modelAttribute="animal" method="POST">
		
			<label for="name">
				<spring:message code="label.Animal.name" />
			</label>
			<sf:input path="name"/>
			<sf:errors cssClass="error" path="name"/>
			
			
			<br/>
			<label for="age">
				<spring:message code="label.Animal.age" />
			</label>
			<sf:input path="age"/>
			<sf:errors cssClass="error" path="age"/>
			
			<input type="submit" value="Submit">
			
		</sf:form>
	</div>
	
</body>
</html>