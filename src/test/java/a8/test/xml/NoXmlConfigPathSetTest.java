package a8.test.xml;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration 
//Look for: a8.test.xml.NoXmlConfigPathSetTest-context.xml
//that it: [package of current java test file].[name of current java test file]-context.xml


public class NoXmlConfigPathSetTest {
	
	@Test
	public void voidMethod(){
	}
}
