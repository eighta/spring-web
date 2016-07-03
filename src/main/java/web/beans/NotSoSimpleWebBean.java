package web.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import a8.beans.SimpleBean;

@Component
public class NotSoSimpleWebBean {
	
	private SimpleBean simpleBean;
	
	@Autowired
	public NotSoSimpleWebBean(@Qualifier("verySimpleBean") SimpleBean simpleBean){
		this.setSimpleBean(simpleBean);
	}

	public SimpleBean getSimpleBean() {
		return simpleBean;
	}

	public void setSimpleBean(SimpleBean simpleBean) {
		this.simpleBean = simpleBean;
	}

}
