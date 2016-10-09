<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div id="bg" style="background:url('../images/climb/004.jpg') center no-repeat;">
  <div class="module">
    <ul>
      <li class="tab"><a href="${flowExecutionUrl}&_eventId=mainInfo" class="ti-user"></a></li>
      <li class="tab"><a href="${flowExecutionUrl}&_eventId=locationInfo" class="ti-location-pin"></a></li>
      <li class="tab activeTab"><span class="ti-key"></span></li>
      <li class="tab"><a href="${flowExecutionUrl}&_eventId=reviewInfo" class="ti-check"></a></li>
    </ul>
    
<form:form modelAttribute="newPerson" method="POST" class="form" action="${flowExecutionUrl}"> 

	<form:password path="password" placeholder="Password" class="textbox"/>
	<form:password path="confirmPassword" placeholder="Confirm Password" class="textbox"/>
	<form:input path="securityQuestion" placeholder="Security Question: (Zero-Factorial is equal to?)" class="textbox"/>

      <div style="text-align:center;">
      	<form:button class="button cancel_b" name="_eventId" value="cancel">Cancel</form:button>
      	<form:button class="button back_b" name="_eventId" value="back">Back</form:button>
      	<form:button class="button"  name="_eventId" value="next">Next</form:button>
      	<form:button class="button finish_b" disabled="true">Finish</form:button>
      </div>
   </form:form>
  </div>
</div>
