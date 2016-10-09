<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div id="bg" style="background:url('../images/climb/006.jpg') center no-repeat;">
  <div class="module">
    <ul>
      <li class="tab"><a href="${flowExecutionUrl}&_eventId=mainInfo" class="ti-user"></a></li>
      <li class="tab"><a href="${flowExecutionUrl}&_eventId=locationInfo" class="ti-location-pin"></a></li>
      <li class="tab"><a href="${flowExecutionUrl}&_eventId=keyInfo" class="ti-key"></a></li>
      <li class="tab activeTab" ><span class="ti-check"></span></li>
    </ul>
    
    <form:form modelAttribute="newPerson" method="POST" class="form" action="${flowExecutionUrl}">
    
    	<h4>Name</h4>
    	<span class="justtext" style="font-family: Ewert; font-size:35px;">${newPerson.firstName}-${newPerson.password}</span>
    	<!-- 
      	<input type="text" class="textbox" disabled="disabled" value="" />
      	 -->
      	
      	<br/><br/>
      	<h4>T&eacute;rminos y Condiciones</h4>
      	<textarea class="textarea">
POR FAVOR LEA CON DETENIMIENTO LAS CONDICIONES DE USO ANTES DE UTILIZAR EL PRESENTE SITIO. PUEDEN PARECERLE MUY TÉCNICAS Y FORMALES DESDE EL PUNTO DE VISTA LEGAL PERO SON DE VITAL IMPORTANCIA. AL UTILIZAR ESTE SITIO, UD. ACEPTA ESTAS CONDICIONES DE USO. EN CASO DE QUE UD. NO ESTE DE ACUERDO CON ESTAS CONDICIONES DE USO, POR FAVOR NO UTILICE ESTE SITIO O LOS SERVICIOS QUE EL MISMO LE OFRECE. GRACIAS.
1. La aceptación a estos términos del servicio (“Términos de Servicio”) es un acuerdo legal vinculante entre usted y la EMPRESA respecto al uso del Sitio Web y todos los productos o servicios disponibles del Sitio Web. Por favor, lea estos Términos de Servicio cuidadosamente. Al acceder o utilizar el Sitio Web, usted expresa su acuerdo con (1) los Términos del Servicio, y (2) las Normas de la comunidad incorporadas y detalladas en las presentes condiciones generales. Si no está de acuerdo con cualquiera de estos términos o las Normas de la comunidad, por favor, no utilice este sitio o los servicios que el mismo ofrece.
2. Actualización de los Términos del Servicio. Aunque intentaremos notificar a los lectores cuando se realizan cambios importantes a las presentes Condiciones de Servicio, usted debe revisar periódicamente la versión vigente más actualizada de las Condiciones del servicio. La EMPRESA, a su discreción, puede modificar o revisar estas Condiciones de servicio y políticas en cualquier momento, y usted acepta que quedará vinculado por estas modificaciones o revisiones.
3. Las presentes Condiciones de Servicio se aplican a todos los usuarios del Sitio Web, incluidos los usuarios que participen aportando contenidos tales como imágenes, información y otros materiales o servicios en el Sitio Web.
4. La EMPRESA se reserva el derecho a modificar cualquier aspecto del Sitio Web en cualquier momento.</textarea>
     <br/> 
     <div  style="margin-top: 8px;">
     	<form:checkbox path="acceptConditions"/>
     	<span style="font-family: Akronim; font-size:20px;">Acepto las Condiciones del servicio</span>
     </div>
        
      <div style="text-align:center;">
      	<form:button class="button cancel_b" name="_eventId" value="cancel">Cancel</form:button>
		<form:button class="button back_b" name="_eventId" value="back">Back</form:button>
		<form:button class="button" disabled="true">Next</form:button>
		<form:button class="button finish_b" name="_eventId" value="confirm">Finish</form:button>
      </div>
    </form:form>
  </div>
</div>
