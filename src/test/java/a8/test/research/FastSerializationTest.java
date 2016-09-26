package a8.test.research;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import java.io.ByteArrayInputStream;

import org.junit.Ignore;
import org.junit.Test;
import org.nustaq.serialization.FSTConfiguration;
import org.nustaq.serialization.FSTObjectInput;

import a8.data.Person;

//https://github.com/RuedigerMoeller/fast-serialization/wiki/Serialization
public class FastSerializationTest {

	@Test
	public void workingWithObjects() throws Exception{
		
		Person person = new Person();
		person.setFirstName("Sophie");
		
		FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
		// write
		byte barray[] = conf.asByteArray(person);
		
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(barray); 
		
		
//		InputStream stream = new ByteArrayInputStream(exampleString.getBytes(StandardCharsets.UTF_8));
//		
		 FSTObjectInput in = new FSTObjectInput(byteArrayInputStream);
		 Person result = (Person) in.readObject();
		 in.close();
		 assertThat(person, is(not(equalTo(result))));
		 assertThat(result.getFirstName(), is(equalTo("Sophie")));
		
	}
	
	
	@Test
	@Ignore
	public void prueba(){
		
		Person toBinary = new Person();
		toBinary.setFirstName("Sophie");
		
		FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
		
		// write
		byte barray[] = conf.asByteArray(toBinary);
		
		// read
		Person fromBinary = (Person)conf.asObject(barray);
		
		assertThat(toBinary, is(not(equalTo(fromBinary))));
		
		
	}
	
	
}
