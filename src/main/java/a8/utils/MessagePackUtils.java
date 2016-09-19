package a8.utils;

import java.io.IOException;

import org.msgpack.MessagePack;
import org.msgpack.type.Value;
import org.msgpack.unpacker.Converter;

public class MessagePackUtils {

	public static byte [] convertFromBean(Object bean, Class<?> clazz){
		try {
			MessagePack msgpack = new MessagePack();
			msgpack.register(clazz);
			return msgpack.write(bean);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T> T convertToBean(byte [] raw, Class<T> clazz) {

		try {
			MessagePack msgpack = new MessagePack();
			Value dynamic;
			dynamic = msgpack.read(raw);
			return new Converter(dynamic).read(clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
