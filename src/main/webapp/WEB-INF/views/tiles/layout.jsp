<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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