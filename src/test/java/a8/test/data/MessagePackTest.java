package a8.test.data;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import java.io.IOException;

import org.junit.Test;
import org.msgpack.MessagePack;
import org.msgpack.template.TemplateRegistry;
import org.msgpack.type.Value;
import org.msgpack.unpacker.Converter;

import a8.data.Person;
import a8.utils.MessagePackUtils;

public class MessagePackTest {

	@Test
	public void usingUtils(){
		
		Person mySelf = new Person(1,"Milton","Ochoa","2016-01-01");
		byte[] raw = MessagePackUtils.convertFromBean( mySelf, Person.class);
		Person mySelfThen = MessagePackUtils.convertToBean(raw, Person.class);
		
		assertThat(mySelf, is(not(equalTo(mySelfThen))));
		assertThat(mySelf.getLastName(), is(equalTo(mySelfThen.getLastName())));
	}
	
	@Test
	public void using() throws IOException{
		
		Person mySelf = new Person(1,"Milton","Ochoa","2016-01-01");
		
		//TemplateRegistry templateRegistry = TemplateRegistry.
		
		MessagePack msgpack = new MessagePack();
		msgpack.register(Person.class);
		
		// Serialize
		byte[] raw = msgpack.write(mySelf);
		
		//Deserialze to Value then convert type. 
		Value dynamic =msgpack.read(raw);
		
		Person mySelfThen =  new Converter(dynamic)
				.read(Person.class);
		
		assertThat(mySelf, is(not(equalTo(mySelfThen))));
		assertThat(mySelf.getLastName(), is(equalTo(mySelfThen.getLastName())));
		
	} 
	
}
