<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div id="bg">
	 <div class="module">
	 
	 	<form:form modelAttribute="newAnimal" method="POST" class="form" action="${flowExecutionUrl}">
	 	
	 		<form:input path="name" placeholder="Name" class="textbox"/>
			<form:errors cssClass="fieldError" path="name"/>
			
			<!-- Como user es un objeto, necesita ser convertido -->
			<!-- Se especifica en el binder -->
			<form:input path="user" placeholder="User (Converter)" class="textbox"/>
			<form:errors cssClass="fieldError" path="user"/>
			
			<form:select path="breed">
				<form:option value="pure">Pure breeds</form:option>
				<form:option value="cross">Cross-breeds</form:option>
				<form:option value="mixed">Mixed-breeds</form:option>
				<form:option value="natural">Natural breeds</form:option>
			</form:select>
	 	
	 		<div class="footerButtons" style="text-align:center;">
				<form:button class="button back_b" disabled="true">Back</form:button>
				<form:button class="button"  name="_eventId" value="next">Next</form:button>
				<form:button class="button finish_b"  name="_eventId" value="decision">Eval</form:button>
			</div>
	 	
	 	</form:form>
	 
	 </div>
</div>