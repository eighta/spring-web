<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<title><tiles:getAsString name="title" /></title>
</head>
<body>
	<table>
		<!-- HEADER -->
		<tr>
			<td colspan="2"><tiles:insertAttribute name="header" /></td>
		</tr>
		
		<!-- body -->
		<tr>
		<%-- 	<td><tiles:insertAttribute name="menu" /></td> --%>
			<td><tiles:insertAttribute name="body" /></td>
		</tr>
		
	</table>
</body>
</html>