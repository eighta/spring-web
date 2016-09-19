package a8.test.utils;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean2;
import org.junit.Test;

public class BeansUtilsTest {

	@Test
	public void convertUtilsBean2Test(){
		
		BeanUtilsBean.setInstance(new BeanUtilsBean2());
		
	}
	
}
