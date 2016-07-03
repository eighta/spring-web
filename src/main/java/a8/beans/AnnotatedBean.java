package a8.beans;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AnnotatedBean {

	private static final Logger logger = LoggerFactory.getLogger(AnnotatedBean.class);
	
	private final String innerBeanName;
	private SimpleBean simpleBean;

	@Autowired
	public AnnotatedBean(
			@Qualifier("verySimpleBean")
			SimpleBean simpleBean) {
		this.innerBeanName="AnnotatedBean";
		this.simpleBean=simpleBean;
	}
	
	@PostConstruct
	public void init(){
		logger.info("@PostConstruct...");
	}
	
	public String getInnerBeanName() {
		return innerBeanName;
	}
	
	public SimpleBean getSimpleBean() {
		return simpleBean;
	}
}
