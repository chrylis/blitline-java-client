package com.blitline.image;

/**
 * Value object representing an Amazon S3 location.
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
