<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1>LET'S BEGIN WITH THE ADVANCED!</h1>

<form:form modelAttribute="thePerson" method="POST" class="form" action="${flowExecutionUrl}">
	<form:input path="id" placeholder="The ID" class="textbox"/>
	
	<form:button name="_eventId" value="mySubflow">Go to Subflow</form:button>
</form:form>

<br/>
<br/>

<table class="simple-table">
  <tr>
  
    <td>
    	<div>
	    	<H3>Subflow</H3>
	    	<a href="${flowExecutionUrl}&_eventId=mySubflow"><i class="fa fa-spinner fa-spin fa-3x fa-fw"></i></a>
		</div>
    </td>
    <td>
    	<div>
	    	<H3>Animated Icons</H3>
			<i class="fa fa-spinner fa-pulse fa-3x fa-fw"></i>
		</div>
    </td>
    <td>
    	<div>
	    	<H3>Animated Icons</H3>
			<i class="fa fa-circle fa-3x fa-fw"></i>
		</div>
    </td>
  </tr>
</table>

<br/><br/><br/>




<!-- 
<H3>list icons</H3>
<ul class="fa-li">
  <li> <i class="fa-li fa fa-check-square"></i>List icon</li>
  
</ul>

<H3>fixed-width</H3>
<div class="list-group">
	<a class="list-group-item" href="#">
		<i class="fa fa-home fa-fw" aria-hidden="true"></i>
		&nbsp; Home
	</a>
	
	<a class="list-group-item" href="#">
		<i class="fa fa-book fa-fw" aria-hidden="true"></i>
		&nbsp; Library
	</a>
</div>


<H3>normal</H3>
<i class="fa fa-camera-retro"></i> fa-camera-retro 
<br/>
<i class="fa fa-lg fa-camera-retro"></i> fa-camera-retro fa-lg
<br/>
<i class="fa fa-5x fa-camera-retro"></i> fa-camera-retro fa-5x
-->
