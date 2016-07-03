package a8.test.xml;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:conf/transaction-config.xml")
public class TransactionTest {

	@Autowired 
	private ApplicationContext applicationContext;
	
	@Test
	@Transactional
	public void getTxByType(){
		PlatformTransactionManager tx = applicationContext.getBean(PlatformTransactionManager.class);
		assertNotNull(tx);
	}
	
}
