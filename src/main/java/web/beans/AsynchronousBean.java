package web.beans;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.concurrent.Future;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import a8.data.User;

@Component
public class AsynchronousBean {

	@Async("otherExecutor")
    public Future<User> findUser(String user) throws InterruptedException {
		
	    System.out.println("Thread [Async]: " + Thread.currentThread().getName());
		
System.out.println("METHOD: findUser(..)");
        String url = String.format("https://api.github.com/users/%s", user);
        
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		Proxy proxy= new Proxy(Type.HTTP, new InetSocketAddress("10.1.0.194", 3128));
	    requestFactory.setProxy(proxy);
		
		RestTemplate restTemplate = new RestTemplate(requestFactory);
        User results = restTemplate.getForObject(url, User.class);
System.out.println("delay(1000L)");        
        // Artificial delay of 1s for demonstration purposes
        Thread.sleep(1000L);
System.out.println("METHOD: findUser(..) END");        
        return new AsyncResult<>(results);
    }
	
}
