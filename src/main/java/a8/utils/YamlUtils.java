package a8.utils;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;

import a8.data.Person;

public class YamlUtils {

	public static byte [] convertFromBean(Object bean){
		try {
			StringWriter sw = new StringWriter();
			YamlWriter yamlWriter = new YamlWriter(sw);
			yamlWriter.write(bean);
			yamlWriter.close();
			return sw.toString().getBytes();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T> T convertToBean(byte [] raw, Class<T> clazz) {

		try {
			
			Reader targetReader = new StringReader(new String(raw));
			
			YamlReader reader = new YamlReader(targetReader);
			return reader.read(clazz);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	
	
	public static void main(String[] args) throws YamlException {
		x();
	}
	
	public static void x() throws YamlException{
		System.out.println("YAML");
		
		
		StringWriter sw = new StringWriter();
		YamlWriter yamlWriter = new YamlWriter(sw);
		yamlWriter.write(new Person(1,"Javier","Larios","2016-01-01"));
		yamlWriter.close();
		
		
		System.out.println(sw.toString());
		
		StringReader sr = new StringReader(sw.toString());
		YamlReader reader = new YamlReader(sr);
		Person personRead = reader.read(Person.class);
		System.out.println(personRead.getLastName());
		
		
	}
}
