package exp.gibin.app.configuration;

import java.io.IOException;

import org.atmosphere.config.managed.Decoder;
import org.atmosphere.config.managed.Encoder;
import org.atmosphere.config.service.Message;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonEncoderDecoder implements Encoder<Message, String>,
Decoder<String, Message>{
	
	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public Message decode(String s) {
		try {
			return this.mapper.readValue(s, Message.class);
		}
		catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
	}

	@Override
	public String encode(Message s) {
		try {
			return this.mapper.writeValueAsString(s);
		}
		catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
		
	}

}
