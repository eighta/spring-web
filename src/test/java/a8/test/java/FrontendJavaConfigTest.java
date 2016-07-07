package a8.test.java;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import a8.conf.BackendJavaConfig;
import web.conf.MvcJavaConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration //XXX COMO ESPECIFICAR QUIEN ES EL FRONT Y QUIEN ES EL ROOT?
@ContextConfiguration(classes={/*MvcJavaConfig.class,*/BackendJavaConfig.class})
public class FrontendJavaConfigTest {

	@Autowired
	public WebApplicationContext webApplicationContext;
	
	@Test
	public void xxx(){
		System.out.println("ok!");
		System.out.println(webApplicationContext);
	}
}
