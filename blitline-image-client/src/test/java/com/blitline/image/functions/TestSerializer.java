package com.blitline.image.functions;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.blitline.image.functions.params.FontStyle;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class TestSerializer {

	@Test
	public void testJackson() throws JsonProcessingException {
		List<AbstractFunction> functions;
		
		Annotate an = new Annotate("sample text").fontFamily("Comic Sans").xOffset(120).fontStyle(FontStyle.ITALIC);
		AutoLevel al = new AutoLevel();
		
		functions = Arrays.asList(an, al);
		
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, SerializationFeature.INDENT_OUTPUT);
		mapper.disable(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS);
		String json = mapper.writeValueAsString(functions);
		System.out.println(json);
	}
}
