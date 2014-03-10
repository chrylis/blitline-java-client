package com.blitline.image;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.Validate;

public class BlitlineImageJob {
	
	private final String applicationId;
	private final Object src;
	private final String postbackUrl;
	private final List<Function> functions = new LinkedList<Function>();
	
	public BlitlineImageJob(String applicationId, Object src, String postbackUrl) {
		Validate.notNull(applicationId, "application ID must not be null");
		this.applicationId = applicationId;
		
		Validate.notNull(src,"image source must not be null");
		this.src = src;
		
		this.postbackUrl = postbackUrl;
	}
	
	public String getApplicationId() {
		return applicationId;
	}
	
	public Object getSrc() {
		return src;
	}
	
	public String getPostbackUrl() {
		return postbackUrl;
	}
	
	public List<Function> getFunctions() {
		return Collections.unmodifiableList(functions);
	}
	
	public void apply(Function... functions) {
		this.functions.addAll(Arrays.asList(functions));
	}
	
	public static Builder forApplication(String applicationId) {
		return new Builder(applicationId);
	}

	/**
	 * Fluent builder class for a {@link BlitlineImageJob} instance.
	 * 
	 * @author Christopher Smith
	 * 
	 */
	public static class Builder {
		private final String applicationId;

		private Object src;

		private String postbackUrl;

		/**
		 * Constructs a {@code Builder} instance for a single {@code Job}.
		 * 
		 * @param applicationId
		 *            your Blitline application ID key
		 */
		public Builder(String applicationId) {
			this.applicationId = applicationId;
		}

		/**
		 * Convenience method that simply calls the standard constructor.
		 * 
		 * @param applicationId
		 *            your Blitline application ID key
		 * @return a new {@code Builder} instance that will build a {@code Job} for the provided key
		 */
		public static Builder forApplication(String applicationId) {
			return new Builder(applicationId);
		}

		private void setSrc(Object src) {
			if (this.src != null)
				throw new IllegalStateException("src is already set");
			
			this.src = src;
		}

		/**
		 * Loads the image to be processed from the specified URL. The contents of the string are not encoded and are passed to the
		 * API as-is.
		 * 
		 * @param src
		 *            the URL for the image to be processed
		 * @return this {@code Builder} object
		 */
		public Builder fromUrl(String src) {
			setSrc(src);
			return this;
		}

		/**
		 * Loads the image to be processed from the specified URL. The contents of the {@code URL} object are not encoded and are
		 * passed to the API as-is.
		 * 
		 * @param src
		 *            the URL for the image to be processed
		 * @return this {@code Builder} object
		 */
		public Builder fromUrl(URL src) {
			return fromUrl(src.toString());
		}

		/**
		 * Loads the image to be processed from S3.
		 * 
		 * @param src
		 *            the S3 location to load the image from
		 * @return this {@code Builder} object
		 */
		public Builder fromS3(S3Location src) {
			setSrc(src);
			return this;
		}

		/**
		 * Loads the image to be processed from S3.
		 * 
		 * @param bucket
		 *            the ID of the S3 bucket where the image is stored
		 * @param key
		 *            the image's object key within the S3 bucket
		 * @return this {@code Builder} object
		 */
		public Builder fromS3(String bucket, String key) {
			return fromS3(new S3Location(bucket, key));
		}

		/**
		 * Specifies an optional callback URL that will receive the notification of job completion or failure.
		 * 
		 * @param postbackUrl
		 *            the callback URL
		 * @return this {@code Builder} object
		 */
		public Builder withPostback(String postbackUrl) {
			this.postbackUrl = postbackUrl;
			return this;
		}

		/**
		 * Specifies an optional callback URL that will receive the notification of job completion or failure.
		 * 
		 * @param postbackUrl
		 *            the callback URL
		 * @return this {@code Builder} object
		 */
		public Builder withPostback(URL postbackUrl) {
			return withPostback(postbackUrl.toString());
		}
		
		public BlitlineImageJob apply(Function... functions) {
			BlitlineImageJob job = new BlitlineImageJob(applicationId, src, postbackUrl);
			job.apply(functions);
			return job;
		}
	}
}
