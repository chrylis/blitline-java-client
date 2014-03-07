package com.blitline.image;

/**
 * Value object representing an Amazon S3 location. An instance of this class may be used for the initial source image, any of the
 * {@code src} parameters of the various functions, or a location to save to.
 * 
 * @author Christopher Smith
 * 
 */
public class S3Location {

	public final String bucket, key;

	public S3Location(String bucket, String key) {
		this.bucket = bucket;
		this.key = key;
	}

	public String getBucket() {
		return bucket;
	}

	public String getKey() {
		return key;
	}
}
