package com.blitline.image;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
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

	public static final String SUCCESS_POSTBACK_CONTENT = "{\"results\":"
		+ "{\"original_meta\":{\"width\":3740,\"height\":5573,\"date_created\":\"2011:06:07 22:09:30\"},"
		+ "\"images\":[{\"image_identifier\":\"6odPpPC9Ayy8Z2ETs9i1Bk-ts\",\"s3_url\":\"http://s3.amazonaws.com/some-bucket/6odPpPC9Ayy8Z2ETs9i1Bk-ts.webp\",\"meta\":{\"width\":43,\"height\":64}}],"
		+ "\"job_id\":\"3WkW98qII4EMriH4jV3eAkQ\"}}";

	public static final OriginalMetadata SUCCESS_ORIGINAL_META = new OriginalMetadata(3740, 5573, new Date(1307502570000L));

	public static final String IMAGE_IDENTIFIER = "6odPpPC9Ayy8Z2ETs9i1Bk-ts";

	public static final Dimensions IMAGE_SIZE = new Dimensions(43, 64);

	@Test
	public void testSuccess() throws IOException {
		BlitlinePostback postback = mapper.readValue(SUCCESS_POSTBACK_CONTENT, BlitlinePostback.class);
		assertEquals(SUCCESS_ORIGINAL_META, postback.getOriginalMeta());
		assertTrue(postback.isSuccessful());
		assertEquals(1, postback.getImages().size());

		Image image = postback.getImages().iterator().next();
		assertEquals(IMAGE_IDENTIFIER, image.getImageIdentifier());
		assertEquals(IMAGE_SIZE, image.getMeta());
	}

	public static final String FAILURE_POSTBACK_CONTENT = "{\"results\":{"
		+ "\"original_meta\":null,"
		+ "\"images\":[],"
		+ "\"job_id\":\"0QCEapK7xMIaoQvvkPyVk-w\","
		+ "\"errors\":[\"Image processing failed. Failed to download from s3 (404: Not Found (https://some-bucket.s3.amazonaws.com:443/wCg2ArHhjrr7DxNnsctWGM))\"],"
		+ "\"failed_image_identifiers\":[\"wCg2ArHhjrr7DxNnsctWGM-tl\",\"wCg2ArHhjrr7DxNnsctWGM-tx\",\"wCg2ArHhjrr7DxNnsctWGM-ds\",\"wCg2ArHhjrr7DxNnsctWGM-dm\"]"
		+ "}}";
	
	public static final String FAILURE_JOB_ID = "0QCEapK7xMIaoQvvkPyVk-w"; 

	@Test
	public void testFailure() throws IOException {
		BlitlinePostback postback = mapper.readValue(FAILURE_POSTBACK_CONTENT, BlitlinePostback.class);
		assertNull(postback.getOriginalMeta());
		assertFalse(postback.isSuccessful());
		
		assertEquals(FAILURE_JOB_ID, postback.getJobId());
		assertEquals(4, postback.getFailedImageIdentifiers().size());
	}
}
