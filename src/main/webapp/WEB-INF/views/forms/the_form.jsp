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

	<div class="rainbow">The Form</div>
	<div class="box">views/forms/the_form.jsp</div>
	
	<div id="form">
		
		<!-- SE GENERA LA URL CON LOS PARAMETROS ENVIADOS DESDE EL CONTROLLER -->
		<spring:url value="persons/{id}" var="editUrl">
			<spring:param name="id" value="${person.id}"/>
		</spring:url>
	
		<sf:form modelAttribute="person" action="${editUrl}" method="POST">
		
			<label for="firstName">
				<spring:message code="label.Person.firstName" />
			</label>
			<sf:input path="firstName"/>
			<sf:errors cssClass="error" path="firstName"/>
			
			<label for="secondName">
				<spring:message code="label.Person.secondName" />
			</label>
			<sf:input path="secondName"/>
			<sf:errors cssClass="error" path="secondName"/>
			
			<label for="lastName">
				<spring:message code="label.Person.lastName" />
			</label>
			<sf:input path="lastName"/>
			<sf:errors cssClass="error" path="lastName"/>
			
			<label for="dateOfBirth">
				<spring:message code="label.Person.dateOfBirth" />
			</label>
			<sf:input path="dateOfBirth"/>
			<sf:errors cssClass="error" path="dateOfBirth"/>
			
			<br/><br/>
			<label for="genre">
				<spring:message code="label.Person.genre" />
			</label>
			<sf:radiobutton path="gender" value="MALE"/>
			<spring:message code="label.Person.male"/>
			<sf:radiobutton path="gender" value="FEMALE"/>
			<spring:message code="label.Person.female"/>
			
			<br/><br/>
			<label for="hospital">
				<spring:message code="label.Hospitals" />
			</label>
			<sf:select path="hospital">
				<sf:options items="${hospitalList}" 
					itemLabel="name" itemValue="id"/>
			</sf:select>
			
			<input type="submit" value="Submit">
			
		</sf:form>
	</div>
	
</body>
</html>