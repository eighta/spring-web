pASSed

# spring-web
Proyecto en donde se plasmaran los conocimientos adquiridos en el spring-web
(y repaso del Spring Framework)

#install and setup Grep Console plugin 

#Console Colors
#393939	background
#C7C7C7	simple text

#logcColors
#56C1E9	DEBUG
#A9D40D	INFO
#FAC848	WARNING
#F55C5A	ERROR
#9878D5	TRACE (ASSERT)


#Font	MONACO

at HOME
C:\Yesta\dev\eclipse\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\spring-web\

at WORK

#TOMCAT CACHE PROBLEM
-En eclipse, en la pestaña de Servers
-Doble clic a la instancia de Tomcat
-En la ventana de configuracion, clic en "Open launch Configuration"
-En la nueva ventanada damos clic en la pestaña "Argumets"
-En la seccion de abajo llamada "VM Arguments", aparece el argumento:
 >Dcatalina.base
-El valor de este argumento, es el path en donde se encuentran los archivos
 De configuracion de la instancia del TOMCAT
-Accedemos a la ruta (directorio) indicada por este PATH
-Abrimos el archivo "context.xml"
-Antes del cierte del tag principal(</Context>)
 Añadimos la siguiente linea:
 <Resources cachingAllowed="true" cacheMaxSize="100000" />
 
NOTA: lo anterior, no soluciona el problema complementamente, ya que cada vez que se
reinicia el Server, otra vez el archivo "context.xml" es restaurado y se pierden los cambios
realizados alli directamente.

http://stackoverflow.com/questions/6759219/change-eclipses-built-in-tomcat-context-xml-file-using-wtp
Lo que en verdad se debe hacer es:

En el proyecto "Servers" (en eclipse)
Aparecen carpetas, por Server asociados al eclipse

En la carpeta de Tomcat, aparecen todos los archivos que se utilizaran cada vez que
el servidor se pone en funcionamiento.

Lo que quiere decir, que el cambio anteriormente mencionado, se debe hacer sobre estos archivos
directamente en el eclipse



  
