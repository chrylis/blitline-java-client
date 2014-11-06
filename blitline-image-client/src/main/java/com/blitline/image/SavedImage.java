package com.blitline.image;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * A base class representing a place where a processed image can be saved.
 *
 * @author Christopher Smith
 *
 */
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
@JsonInclude(Include.NON_NULL)
public class SavedImage implements Serializable {

	private static final long serialVersionUID = 1L;

	public final String imageIdentifier;
	public final Integer quality;
	public final Boolean saveMetadata;
	public final Boolean skip;
	public final Map<String, Object> setExif;

	public final S3Location s3Destination;
	public final AzureLocation azureDestination;

	public SavedImage(String imageIdentifier, Integer quality, boolean saveMetadata, boolean skip, S3Location s3Destination, AzureLocation azureDestination) {
	    this(imageIdentifier, quality, saveMetadata, skip, s3Destination, azureDestination, null);
	}

	public SavedImage(String imageIdentifier, Integer quality, boolean saveMetadata, boolean skip, S3Location s3Destination, AzureLocation azureDestination, Map<String, Object> setExif) {
		if(s3Destination != null && azureDestination != null)
			throw new IllegalArgumentException("only one destination location may be specified");

		this.imageIdentifier = imageIdentifier;
		this.quality = quality;

		// since the default value is false, only send the field if it's true
		this.saveMetadata = saveMetadata ? Boolean.TRUE : null;
		this.skip = skip ? Boolean.TRUE : null;

		this.s3Destination = s3Destination;
		this.azureDestination = azureDestination;

		this.setExif = setExif;
	}

	public static Builder withId(String imageIdentifier) {
		return new Builder(imageIdentifier);
	}

	public static class Builder {
		private final String imageIdentifier;
		private Integer quality;
		private boolean saveMetadata = false;
		private Map<String, Object> exif = new HashMap<String, Object>();

		public Builder(String imageIdentifier) {
			this.imageIdentifier = imageIdentifier;
		}

		public Builder withQuality(int quality) {
			this.quality = Integer.valueOf(quality);
			return this;
		}

		public Builder withMetadata() {
			saveMetadata = true;
			return this;
		}

		public Builder withExifHeader(String key, Object value) {
		    exif.put(key, value);
		    return this;
		}

		private Map<String, Object> exifOrNull() {
		    return exif.isEmpty() ? null : exif;
		}

		public SavedImage toS3(S3Location s3Destination) {
			return new SavedImage(imageIdentifier, quality, saveMetadata, false, s3Destination, null, exifOrNull());
		}

		public SavedImage toS3(String bucket, String key) {
			return toS3(S3Location.of(bucket, key));
		}

		public SavedImage toAzure(AzureLocation azureDestination) {
			return new SavedImage(imageIdentifier, quality, saveMetadata, false, null, azureDestination, exifOrNull());
		}

		public SavedImage toAzure(String accountName, String sharedAccessSignature) {
			return toAzure(AzureLocation.of(accountName, sharedAccessSignature));
		}

		public SavedImage toBlitlineContainer() {
			return new SavedImage(imageIdentifier, quality, saveMetadata, false, null, null, exifOrNull());
		}

		public SavedImage butSkipSave() {
			return new SavedImage(imageIdentifier, quality, saveMetadata, true, null, null);
		}
	}
}
