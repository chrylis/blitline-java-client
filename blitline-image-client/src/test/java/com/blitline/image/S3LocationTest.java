package com.blitline.image;

import static org.junit.Assert.*;

import org.junit.Test;

public class S3LocationTest {

	static final String BUCKET_NAME = "my-bucket-name", KEY_NAME = "my-key-name";

	static final S3Location EXPECTED = new S3Location(BUCKET_NAME, KEY_NAME);

	@Test
	public void testEquals() {
		assertEquals(EXPECTED, S3Location.of(BUCKET_NAME, KEY_NAME));
		assertNotEquals(EXPECTED, S3Location.of(BUCKET_NAME, KEY_NAME + "-"));
		assertNotEquals(EXPECTED, S3Location.of(BUCKET_NAME + "-", KEY_NAME));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBadBucket() {
		S3Location.of("asdf?", KEY_NAME);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUrlTooShort() {
		S3Location.of("s3://asdf/");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUrlWrongSchema() {
		S3Location.of("http://asdf/foo");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUrlBucketTooShort() {
		S3Location.of("s3://as/foo");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUrlBucketTooLong() {
		S3Location.of("s3://asdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdf/foo");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUrlBucketBadChar() {
		S3Location.of("s3://asdf+asdf/foo");
	}

	@Test
	public void testUrl() {
		assertEquals(EXPECTED, S3Location.of("s3://" + BUCKET_NAME + "/" + KEY_NAME));
	}

	@Test
	public void testHeaders() {
	    S3Location withHeaders = S3Location.of(BUCKET_NAME, KEY_NAME).withCacheControlHeader();
	    assertEquals("public, max-age=31536000", withHeaders.getHeaders().get("Cache-Control"));
	}
}
