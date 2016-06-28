package a8;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import a8.conf.RepositorioConf;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RepositorioConf.class })
public class InfraestructuraTest {

	@Test
	public void dummyMethod(){
		System.out.println("HEY....");
	}
}
