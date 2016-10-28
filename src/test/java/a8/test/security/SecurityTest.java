package a8.test.security;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class SecurityTest {

	@Test
	public void testMd5PasswordEncoder(){
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		String encrypted = encoder.encodePassword("doe", "MySalt");
		assertEquals(encrypted, "a1c093d7a2742f0afef7720883a59016");
		encrypted = encoder.encodePassword("admin", "MySalt");
		assertEquals(encrypted, "5a693853b2958ecb256db46b808ac488");
	}
}
