<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div id="bg">
	 <div class="module">
	 
	 	<form:form modelAttribute="newAnimal" method="POST" class="form" action="${flowExecutionUrl}">
	 	
	 		<form:input path="age" placeholder="Age" class="textbox"/>
			<form:errors cssClass="fieldError" path="age"/>
	 	
	 		<div class="footerButtons" style="text-align:center;">
				<form:button class="button back_b" name="_eventId" value="back">Back</form:button>
				<form:button class="button"  name="_eventId" value="next">Next</form:button>
			</div>
	 	
	 	</form:form>
	 
	 </div>
</div>