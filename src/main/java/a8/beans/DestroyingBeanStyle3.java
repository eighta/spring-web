package a8.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

@Component
public class DestroyingBeanStyle3 implements DisposableBean{

	private static final Logger logger = LoggerFactory.getLogger(DestroyingBeanStyle3.class);

	@Override
	public void destroy() throws Exception {
		logger.info("PreDestroy...");
	}
}
