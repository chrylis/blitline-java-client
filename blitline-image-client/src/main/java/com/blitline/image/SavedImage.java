package com.blitline.image;

/**
 * A base class representing a place where a processed image can be saved.
 * 
 * @author Christopher Smith
 *
 */
public class SavedImage {
	
	public final String imageIdentifier;
	public final Integer quality;
	public final Boolean saveMetadata;
	
	public final S3Location s3Destination;
	public final AzureLocation azureDestination;

	public SavedImage(String imageIdentifier, Integer quality, boolean saveMetadata, S3Location s3Destination, AzureLocation azureDestination) {
		if(s3Destination != null && azureDestination != null)
			throw new IllegalArgumentException("only one destination location may be specified");
		
		this.imageIdentifier = imageIdentifier;
		this.quality = quality;
		
		// since the default value is false, only send the field if it's true
		this.saveMetadata = saveMetadata ? Boolean.TRUE : null;
		
		this.s3Destination = s3Destination;
		this.azureDestination = azureDestination;
	}

	public static Builder withId(String imageIdentifier) {
		return new Builder(imageIdentifier);
	}
	
	public static class Builder {
		private final String imageIdentifier;
		private Integer quality;
		private boolean saveMetadata = false;
		
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
		
		public SavedImage toS3(S3Location s3Destination) {
			return new SavedImage(imageIdentifier, quality, saveMetadata, s3Destination, null);
		}
		
		public SavedImage toAzure(AzureLocation azureDestination) {
			return new SavedImage(imageIdentifier, quality, saveMetadata, null, azureDestination);
		}
		
		public SavedImage toBlitlineContainer() {
			return new SavedImage(imageIdentifier, quality, saveMetadata, null, null);
		}
	}
}
