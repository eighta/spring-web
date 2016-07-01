package a8.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class InitAndDestroyBean implements InitializingBean, DisposableBean {

	private static final Logger logger = LoggerFactory.getLogger(InitAndDestroyBean.class);
	
	private String innerBeanName; 
	
	public InitAndDestroyBean(){
		logger.info("constructor");
	}
	
	@Override
	public void destroy() throws Exception {
		logger.info("destroy(...) method [DisposableBean]"); 
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("afterPropertiesSet(...) method [InitializingBean]");
	}
	@PostConstruct
	public void init(){
		logger.info("@PostConstruct...");
	}
	@PreDestroy
	public void cleanup(){
		logger.info("@PreDestroy...");
	}
	public void plainInitMethod(){
		logger.info("plainInitMethod(...)");
	}
	public void plainCleanMethod(){
		logger.info("plainCleanMethod(...)");
	}

	public String getInnerBeanName() {
		return innerBeanName;
	}

	public void setInnerBeanName(String innerBeanName) {
		logger.info("setInnerBeanName(...)");
		this.innerBeanName = innerBeanName;
	}
}
