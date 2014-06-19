package com.blitline.image;

import java.io.Serializable;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.Validate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonRootName("json")
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
@JsonInclude(Include.NON_EMPTY)
public class BlitlineImageJob implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * The postback class considers any image identifier ending in this string to be an "identify-only" job.
	 */
	public static final String IDENTIFY_ONLY_SUFFIX = "-identify";

	private final String applicationId;
	private final Object src;
	private final Boolean extendedMetadata;
	private final String postbackUrl;
	private final List<Function> functions = new LinkedList<Function>();

	public BlitlineImageJob(String applicationId, Object src, Boolean extendedMetadata, String postbackUrl) {
		Validate.notNull(applicationId, "application ID must not be null");
		this.applicationId = applicationId;

		Validate.notNull(src, "image source must not be null");
		this.src = src;

		this.extendedMetadata = extendedMetadata;

		this.postbackUrl = postbackUrl;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public Object getSrc() {
		return src;
	}

	public Boolean getExtendedMetadata() {
		return extendedMetadata;
	}

	public String getPostbackUrl() {
		return postbackUrl;
	}

	public String getV() {
		return Blitline.BLITLINE_API_VERSION;
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

		private Boolean extendedMetadata;

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
		public Builder fromUrl(URI src) {
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
		public Builder withPostback(URI postbackUrl) {
			return withPostback(postbackUrl.toString());
		}

		/**
		 * Specifies that this job should return extended metadata such as file size.
		 *
		 * @return this {@code Builder} object
		 */
		public Builder withExtendedMetadata() {
			extendedMetadata = Boolean.TRUE;
			return this;
		}

		/**
		 * Build the job and apply one or more functions.
		 *
		 * @param functions
		 *            the functions to apply to the job
		 * @return a job specification object
		 */
		public BlitlineImageJob apply(Function... functions) {
			BlitlineImageJob job = new BlitlineImageJob(applicationId, src, extendedMetadata, postbackUrl);
			job.apply(functions);
			return job;
		}

		/**
		 * Build the job from the specified source, but only attempt to read image metadata about the original.
		 * This method uses the fixed image identifier "only-identify".
		 *
		 * @return a job specification that will only read and return metadata
		 */
		public BlitlineImageJob identifyMetadataOnly() {
			return identifyMetadataOnly("only");
		}

		/**
		 * Build the job from the specified source, but only attempt to read image metadata about the original.
		 * This method appends "-identify" to the {@code identifierPart} to form the image identifier.
		 *
		 * @param identifierPart the prefix to use for the image identifier
		 *
		 * @return a job specification that will only read and return metadata
		 */
		public BlitlineImageJob identifyMetadataOnly(String identifierPart) {
			BlitlineImageJob job = new BlitlineImageJob(applicationId, src, true, postbackUrl);
			job.apply(Blitline.noOp().andSkipSave(identifierPart + IDENTIFY_ONLY_SUFFIX));
			return job;
		}

	}
}
