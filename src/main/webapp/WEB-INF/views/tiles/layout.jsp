<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
	<title>
		<%-- <spring:message></spring:message> --%>
		<tiles:insertAttribute name="pageTitle"/>
	</title>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="../css/site.css">

	<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Josefin+Slab" />
	<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Ubuntu" />
	<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Open+Sans" />
	<link rel="stylesheet" href="../libs/pure-release-0.6.0/pure-min.css">
	
	<link rel="stylesheet" type="text/css" href="../css/themify/themify-icons.css">
	<link rel="stylesheet" type="text/css" href="../css/webflow-styles.css">
	
	<style type="text/css">
		/*BETTER WITH JS*/
		/*body { display: none; }*/
	</style>
	
	<script src="http://localhost:8080/spring-web/js/jquery-3.1.0.js"></script>
	
	<script type="text/javascript">
	
	$(document).ready(function(){
		
		$("body").css("display", "none");	
		
		//IN
		$("body").fadeIn(2000);
		//$("body").hide(0).delay(500).fadeIn(1000);
		
		//OUT
		//links
		
		/* $("a").click(function(event){
			event.preventDefault();
			linkLocation = this.href;
			$("body").fadeOut(1000, redirectPage);		
		});
			
		function redirectPage() {
			window.location = linkLocation;
		} */
		
		
		/*
		$(":submit").click(function(event) {
		    event.preventDefault();
		    $(this).fadeOut("slow", function(){
		        //Once button has faded, invoke the form submission
		        $("form").submit();
		    });
		});
		*/
		
		
		/*
		//SUBMIT BUTTONS
		$(":submit").on("click", function(e){
			//alert("click");
			e.preventDefault();
			var elForm = $("form"); 
			$("body").fadeOut("slow",function(){
				
				//var eventData = "GO";
				
				//alert(elForm);
				
				elForm.submit();
			});
		});
		*/
		
		//OUT
//		$("form").on( "__submit", function(e) {
//			//alert("submit");
//			e.preventDefault();
//			/*  
//			  $("body").fadeOut("fast",function(){
//				 // $( "form" ).submit();
//			  }); */
//		});
		
	});
	</script>
</head>
 
<body>
	<c:set var="global" value="${applicationScope.GLOBAL}" />

	<!-- TOP-RIGHT -->
	  <div class="topright">
	  	<table class="information">
	  		
	  		<!-- header -->
	  		<tr>
	  			<th class="column-INVOCATION">Invocation</th>
	  			<th class="column-SCOPE">Scope</th>
	  			<th class="column-VAR-NAME">Variable</th>
	  			<th class="column-VAR-VALUE">Value</th>
	  		</tr>
	  		
	  		<!-- flow Scope -->
	  		<tr>
	  			<td>Flow at Beginning</td>
	  			<td>Flow</td>
	  			<td>&lt;var name='grammy'&gt;</td>
	  			<td>${grammy}</td>
	  		</tr>
	  		
	  		<!-- view-state Scope -->
	  		<tr>
	  			<td>View at Beginning</td>
	  			<td>View</td>
	  			<td>&lt;var  name='concretePerson'&gt;</td>
	  			<td>${concretePerson}</td>
	  		</tr>
	  		
	  		<!-- conversation Scope -->
	  		<tr>
	  			<td>Flow &lt;on-start&gt;</td>
	  			<td>Conversation</td>
	  			<td>(conversationScope.conversationVar1)</td>
	  			<td>${conversationVar1}</td>
	  		</tr>
	  		
	  		<!-- Flash Scope -->
	  		<tr>
	  			<td>Flow &lt;on-start&gt;</td>
	  			<td>Flash</td>
	  			<td>(flashScope.flashVar1)</td>
	  			<td>${flashVar1}</td>
	  		</tr>
	  		
	  		<!-- Request Scope -->
	  		<tr>
	  			<td>Flow &lt;on-start&gt;</td>
	  			<td>Request</td>
	  			<td>(requestScope.requestVar1)</td>
	  			<td>${requestVar1} (NO ENTENDIDO)</td>
	  		</tr>
	  		
	  		<!-- OTHERS examples -->
	  		<tr>
	  			<td>Flow &lt;transition&gt;</td>
	  			<td>Flash</td>
	  			<td>from 'requestParameters.firstName'</td>
	  			<td>${flashVar2}</td>
	  		</tr>
	  		
	  	</table>
	  
	  </div>

	<div class="bigtitle">
		<tiles:insertAttribute name="pageTitle"/>
	</div>
	<div class="box">
		<tiles:insertAttribute name="pageLocation"/>
	</div>
	<br/>
	<div class="box serious_box">
		<!-- http://forum.spring.io/forum/spring-projects/web/web-flow/45365-flowexecutionkey -->
		<%-- <h2>${flowExecutionKey}</h2> --%>
		<h2>${flowExecutionUrl}</h2>
	</div>
	
	<!-- Dynamic data - body of the page -->
	<div class="content">
		<tiles:insertAttribute name="content"/>
	</div>
	
	<%-- <div class="footer">
		<p><spring:message code="footer.text"/></p>
	</div> --%>
	
	<!-- XXX TODO no termine el ejemplo del tiles del libro, continuarlo: page 126 -->
	
	<%--ThemifyIcons: ALL ICONS
	<c:forEach var="icon" items="${global.getThemifyIcons()}">
		<span class="${icon}"></span>	
	</c:forEach>
	--%>
</body>
</html>