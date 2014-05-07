package com.blitline.image;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.blitline.image.BlitlinePostback.Image;
import com.blitline.image.BlitlinePostback.Image.ImageMeta;
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

	public static final ImageMeta IMAGE_SIZE = new ImageMeta(43, 64);

	@Test
	public void testSuccess() throws IOException {
		BlitlinePostback postback = mapper.readValue(SUCCESS_POSTBACK_CONTENT, BlitlinePostback.class);
		OriginalMetadata original = postback.getOriginalMeta();
		assertEquals(Integer.valueOf(3740), original.getWidth());
		assertEquals(Integer.valueOf(5573), original.getHeight());
		assertEquals(new Date(1307502570000L), original.getDateCreated());
		assertTrue(postback.isSuccessful());
		assertEquals(1, postback.getImages().size());

		Image image = postback.getImages().iterator().next();
		assertEquals(IMAGE_IDENTIFIER, image.getImageIdentifier());
		ImageMeta meta = image.getMeta();
		assertEquals(Integer.valueOf(43), meta.getWidth());
		assertEquals(Integer.valueOf(64), meta.getHeight());
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

	public static final String BIG_EXIF_SUCCESS_POSTBACK = "{\"results\":"
		+ "{\"original_meta\":{\"width\":3504,\"height\":2336,"
		+ "\"all_exif\":[[\"ApertureValue\",\"393216/65536\"],[\"ColorSpace\",\"1\"],[\"ComponentsConfiguration\",\"1, 2, 3, 0\"],[\"Compression\",\"6\"],[\"CustomRendered\",\"0\"],[\"DateTime\",\"2012:12:10 15:50:58\"],[\"DateTimeDigitized\",\"2012:12:10 15:50:58\"],[\"DateTimeOriginal\",\"2012:12:10 15:50:58\"],[\"ExifImageLength\",\"2336\"],[\"ExifImageWidth\",\"3504\"],[\"ExifOffset\",\"196\"],[\"ExifVersion\",\"48, 50, 50, 49\"],[\"ExposureBiasValue\",\"0/2\"],[\"ExposureMode\",\"0\"],[\"ExposureProgram\",\"2\"],[\"ExposureTime\",\"1/200\"],[\"Flash\",\"16\"],[\"FlashPixVersion\",\"48, 49, 48, 48\"],[\"FNumber\",\"80/10\"],[\"FocalLength\",\"56/1\"],[\"FocalPlaneResolutionUnit\",\"2\"],[\"FocalPlaneXResolution\",\"3504000/885\"],[\"FocalPlaneYResolution\",\"2336000/590\"],[\"InteroperabilityIndex\",\"R98\"],[\"InteroperabilityOffset\",\"9258\"],[\"InteroperabilityVersion\",\"48, 49, 48, 48\"],[\"ISOSpeedRatings\",\"200\"],[\"JPEGInterchangeFormat\",\"9716\"],[\"JPEGInterchangeFormatLength\",\"13192\"],[\"Make\",\"Canon\"],[\"MakerNote\",\"24, 0, 1, 0, 3, 0, 46, 0, 0, 0, 154, 3, 0, 0, 2, 0, 3, 0, 4, 0, 0, 0, 246, 3, 0, 0, 3, 0, 3, 0, 4, 0, 0, 0, 254, 3, 0, 0, 4, 0, 3, 0, 34, 0, 0, 0, 6, 4, 0, 0, 6, 0, 2, 0, 32, 0, 0, 0, 74, 4, 0, 0, 7, 0, 2, 0, 32, 0, 0, 0, 106, 4, 0, 0, 9, 0, 2, 0, 32, 0, 0, 0, 138, 4, 0, 0, 12, 0, 4, 0, 1, 0, 0, 0, 160, 113, 148, 102, 13, 0, 7, 0, 0, 4, 0, 0, 170, 4, 0, 0, 15, 0, 3, 0, 19, 0, 0, 0, 226, 8, 0, 0, 16, 0, 4, 0, 1, 0, 0, 0, 117, 1, 0, 128, 18, 0, 3, 0, 28, 0, 0, 0, 170, 8, 0, 0, 19, 0, 3, 0, 4, 0, 0, 0, 8, 9, 0, 0, 21, 0, 4, 0, 1, 0, 0, 0, 0, 0, 0, 160, 25, 0, 3, 0, 1, 0, 0, 0, 1, 0, 205, 204, 131, 0, 4, 0, 1, 0, 0, 0, 144, 44, 68, 0, 147, 0, 3, 0, 16, 0, 0, 0, 16, 9, 0, 0, 160, 0, 3, 0, 14, 0, 0, 0, 48, 9, 0, 0, 170, 0, 3, 0, 5, 0, 0, 0, 76, 9, 0, 0, 208, 0, 4, 0, 1, 0, 0, 0, 0, 0, 0, 0, 224, 0, 3, 0, 17, 0, 0, 0, 86, 9, 0, 0, 1, 64, 3, 0, 70, 2, 0, 0, 120, 9, 0, 0, 2, 64, 3, 0, 116, 10, 0, 0, 4, 14, 0, 0, 3, 64, 3, 0, 22, 0, 0, 0, 236, 34, 0, 0, 51, 51, 0, 0, 0, 0, 92, 0, 2, 0, 0, 0, 3, 0, 0, 0, 1, 0, 0, 0, 2, 0, 0, 0, 7, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 255, 127, 3, 0, 2, 0, 0, 0, 1, 0, 255, 255, 255, 255, 80, 0, 28, 0, 1, 0, 139, 0, 64, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 255, 255, 255, 255, 0, 0, 0, 0, 0, 0, 0, 0, 255, 255, 0, 0, 255, 127, 0, 0, 255, 127, 255, 255, 255, 255, 2, 0, 56, 0, 139, 3, 93, 2, 100, 0, 0, 0, 0, 0, 0, 0, 68, 0, 0, 0, 192, 0, 240, 0, 192, 0, 245, 0, 0, 0, 1, 0, 3, 0, 0, 0, 8, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 192, 0, 144, 1, 148, 0, 0, 0, 0, 0, 252, 0, 0, 0, 255, 255, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 67, 97, 110, 111, 110, 32, 69, 79, 83, 32, 50, 48, 68, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 70, 105, 114, 109, 119, 97, 114, 101, 32, 50, 46, 48, 46, 51, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 117, 110, 107, 110, 111, 119, 110, 0, 25, 0, 0, 0, 1, 0, 0, 0, 30, 0, 0, 0, 1, 0, 0, 0, 40, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0\"],[\"MeteringMode\",\"5\"],[\"Model\",\"Canon EOS 20D\"],[\"Orientation\",\"1\"],[\"ResolutionUnit\",\"2\"],[\"SceneCaptureType\",\"0\"],[\"ShutterSpeedValue\",\"500948/65536\"],[\"UserComment\",\"0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0\"],[\"WhiteBalance\",\"1\"],[\"XResolution\",\"72/1\"],[\"YCbCrPositioning\",\"2\"],[\"YResolution\",\"72/1\"]],\"filesize\":4468016,\"date_created\":\"2012:12:10 15:50:58\"},"
		+ "\"images\":["
		+ "{\"image_identifier\":\"kiwi.jpg-ts\",\"s3_url\":\"http://s3.amazonaws.com/out-bucket/kiwi.jpg-ts.webp\","
		+ "\"meta\":{\"width\":64,\"height\":43,\"filesize\":1252,\"density\":\"72x72\",\"depth\":16,\"quality\":75}}],"
		+ "\"job_id\":\"1MRsizP36V3H7ccTXbJowMQ\"}}";

	@Test
	public void testBigExifDump() throws IOException {
		BlitlinePostback postback = mapper.readValue(BIG_EXIF_SUCCESS_POSTBACK, BlitlinePostback.class);
		OriginalMetadata meta = postback.getOriginalMeta();
		assertNotNull(meta);
		assertTrue(postback.isSuccessful());

		assertEquals(4468016, meta.getFilesize().intValue());
		assertEquals(new Date(1355176258000L), meta.getDateCreated());
		assertEquals("Canon EOS 20D", meta.getAllExif().get("Model"));
	}
}
