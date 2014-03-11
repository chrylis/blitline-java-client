package com.blitline.image.functions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.blitline.image.BlitlinePostResults;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestDeserializer {

	private static ObjectMapper mapper;

	@BeforeClass
	public static void buildMapper() {
		mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
	}

	static final String SUCCESS_MESSAGE = "{\"results\":{\"images\":[{\"image_identifier\":\"SUCCESS\",\"s3_url\":\"http://s3.amazonaws.com/blitline/2014031113/3155/9RZWLYNiOpfGPnHpdr6RKNQ.jpg\"},{\"image_identifier\":\"cropsquare\",\"s3_url\":\"http://s3.amazonaws.com/blitline/2014031113/3155/0UQcMs9Ko41_aimxglMYPlQ.jpg\"}],\"job_id\":\"7x52QvS1lyhU7VqPK7pPoRg\"}}";
	static final String ERROR_MESSAGE = "{\"results\":{\"error\":\"Your application ID is required to process any job. Check your Blitline account page for your application ID\"}}";

	@Test
	public void testSuccess() throws JsonParseException, JsonMappingException, IOException {
		BlitlinePostResults results = mapper.readValue(SUCCESS_MESSAGE, BlitlinePostResults.class);
		assertTrue(results.isSuccessful());
		assertEquals(2, results.getImages().size());
		assertEquals("http://s3.amazonaws.com/blitline/2014031113/3155/0UQcMs9Ko41_aimxglMYPlQ.jpg", results.getImageDestinations().get("cropsquare"));
		assertEquals("7x52QvS1lyhU7VqPK7pPoRg", results.getJobId());
	}
	
	@Test
	public void testFailure() throws JsonParseException, JsonMappingException, IOException {
		BlitlinePostResults results = mapper.readValue(ERROR_MESSAGE, BlitlinePostResults.class);
		assertFalse(results.isSuccessful());
		assertEquals("Your application ID is required to process any job. Check your Blitline account page for your application ID", results.getError());
		assertNull(results.getImageDestinations());
	}
}
