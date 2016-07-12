package web.templates.velocity;

import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class VelocityTest {

	public static void main(String[] args) {
		//http://velocity.apache.org/engine/1.7/developer-guide.html
		
		Properties properties = new Properties();
		
		
		 /*  first, get and initialize an engine  */
        VelocityEngine velocityEngine = new VelocityEngine(properties);
        velocityEngine.init();
        /*  next, get the Template  */
        Template template = velocityEngine.getTemplate( "helloworld.vm" );
        /*  create a context and add data */
        VelocityContext context = new VelocityContext();
        context.put("name", "World");
        /* now render the template into a StringWriter */
        StringWriter writer = new StringWriter();
        template.merge( context, writer );
        /* show the World */
        System.out.println( writer.toString() ); 

	}

}
