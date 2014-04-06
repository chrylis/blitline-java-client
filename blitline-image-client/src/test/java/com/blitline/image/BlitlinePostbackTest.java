package com.blitline.image;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.blitline.image.BlitlinePostback.Image;
import com.blitline.image.BlitlinePostback.Image.Dimensions;
import com.blitline.image.BlitlinePostback.OriginalMetadata;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class BlitlinePostbackTest {

	private static ObjectMapper mapper;
	
	@BeforeClass
	public static void createMapper() {
		mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
		mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
		mapper.setDateFormat(BlitlinePostback.BLITLINE_DATE_FORMAT);
	}
	
	public static final String SUCCESS_POSTBACK_CONTENT =
		"{\"results\":" +
			"{\"original_meta\":{\"width\":3740,\"height\":5573,\"date_created\":\"2011:06:07 22:09:30\"}," +
			"\"images\":["
			+ "{\"image_identifier\":\"6odPpPC9Ayy8Z2ETs9i1Bk-ts\",\"s3_url\":\"http://s3.amazonaws.example.com/bucket-name/6odPpPC9Ayy8Z2ETs9i1Bk-ts.webp\","
			+ "\"meta\":{\"width\":43,\"height\":64}}],\"job_id\":\"8ReWKB20IZaSpN5sjbnz3xA\"}"
			+ "}";
	
	public static final OriginalMetadata SUCCESS_ORIGINAL_META = new OriginalMetadata(3740, 5573, new Date(1307502570000L));
	
	public static final String IMAGE_IDENTIFIER="6odPpPC9Ayy8Z2ETs9i1Bk-ts";
	
	public static final Dimensions IMAGE_SIZE = new Dimensions(43, 64);
	
	@Test
	public void test() throws IOException {
		BlitlinePostback postback = mapper.readValue(SUCCESS_POSTBACK_CONTENT, BlitlinePostback.class);
		assertEquals(SUCCESS_ORIGINAL_META, postback.getOriginalMeta());
		assertTrue(postback.isSuccessful());
		assertEquals(1, postback.getImages().size());
		
		Image image = postback.getImages().iterator().next();
		assertEquals(IMAGE_IDENTIFIER, image.getImageIdentifier());
		assertEquals(IMAGE_SIZE, image.getMeta());
	}

//	public static final String FAILURE_POSTBACK_CONTENT = "{\"results\":{\"original_meta\":null,\"images\":[{\"error\":\"Image processing failed. Failed to download from s3 (404: Not Found (https://asq-orig-dev.s3.amazonaws.com:443/mURrYXWEHbPAUCkhYnUAi))","failed_image_identifiers":["mURrYXWEHbPAUCkhYnUAi-ts","mURrYXWEHbPAUCkhYnUAi-tm","mURrYXWEHbPAUCkhYnUAi-tl","mURrYXWEHbPAUCkhYnUAi-tx","mURrYXWEHbPAUCkhYnUAi-ds","mURrYXWEHbPAUCkhYnUAi-dm","mURrYXWEHbPAUCkhYnUAi-dl"]}],"job_id":"7Gm0m-3WM8mdFnylrqHZj-A"}}"
}
