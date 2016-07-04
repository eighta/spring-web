
<!-- XXX SALE ERROR taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" isELIgnored="false" %>

<!-- XXX SALE ERROR
<portlet:resourceURL var="testResourceUrl" id="testResource" escapeXml="false"/>
 -->

<c:out value="${helloWorldMessage}"/>
<br/>
<a href="${testResourceUrl}"> Test Resource Request </a>