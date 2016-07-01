package a8.beans;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

public class RequiredBean {

	//private static final Logger logger = LoggerFactory.getLogger(RequiredBean.class);
	
	private final String innerBeanName;
	private SimpleBean verySimpleBean;
	private SingletonBean singletonBean; 
	
	public RequiredBean() {
		this.innerBeanName="RequiredBean";
	}
	
	@PostConstruct
	public void init(){
		this.setVerySimpleBean(new SimpleBean("0") );
	}

	public String getInnerBeanName() {
		return innerBeanName;
	}

	public SimpleBean getVerySimpleBean() {
		return verySimpleBean;
	}
	public SingletonBean getSingletonBean() {
		return singletonBean;
	}

	@Autowired
	//If such a bean is not found, a BeanCreationException is thrown
	public void setSingletonBean(SingletonBean singletonBean) {
		this.singletonBean = singletonBean;
	}
	
	@Required
	//@Required methods are processed by RequiredAnnotationBeanPostProcessor
	//need to register an RequiredAnnotationBeanPostProcessor to aware of the @Required annotation
	//If the property is not set, a BeanInitializationException is thrown.
	public void setVerySimpleBean(SimpleBean verySimpleBean) {
		this.verySimpleBean = verySimpleBean;
	}
}
