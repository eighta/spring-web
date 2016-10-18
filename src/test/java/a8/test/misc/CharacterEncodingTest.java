package a8.test.misc;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.tika.detect.AutoDetectReader;
import org.apache.tika.exception.TikaException;
import org.junit.Ignore;
import org.junit.Test;
import org.mozilla.universalchardet.UniversalDetector;

public class CharacterEncodingTest {

	private String simpleString = "miltonOchoa";
	private String tildeString = "áéíóúñÁÉÍÓÚÑ";
	private String rareString = "Pinzï¿½n";
	
	@Test
	public void usingjUniversalChardet(){
		
		byte [] byteArray = rareString.getBytes();
		
		UniversalDetector detector = new UniversalDetector(null);
		detector.handleData(byteArray, 0, byteArray.length);
		detector.dataEnd();
		
		String detectedCharset = detector.getDetectedCharset();
		System.out.println(detectedCharset);
	}
	
	@Test
	@Ignore
	public void usingTIKA() throws IOException, TikaException{
		
		InputStream stream = new ByteArrayInputStream(simpleString.getBytes(StandardCharsets.UTF_8));
		
		try(AutoDetectReader autoDetectReader = new AutoDetectReader(stream);){
			//System.out.println(autoDetectReader.getCharset());
		}
		
	}
}
