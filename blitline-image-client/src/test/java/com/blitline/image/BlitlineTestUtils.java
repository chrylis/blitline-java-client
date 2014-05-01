package com.blitline.image;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class BlitlineTestUtils {

	public static ObjectMapper buildMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		return mapper;
	}
}
