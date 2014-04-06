package com.blitline.image;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonRootName("results")
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class BlitlinePostback {

	private OriginalMetadata originalMeta;

	private String jobId;
	
	private String error;
	
	private Collection<String> failedImageIdentifiers = Collections.emptySet();
	
	private Collection<Image> images = Collections.emptySet();
	
	public OriginalMetadata getOriginalMeta() {
		return originalMeta;
	}

	public void setOriginalMeta(OriginalMetadata originalMeta) {
		this.originalMeta = originalMeta;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	
	public String getError() {
		return error;
	}
	
	public void setError(String error) {
		this.error = error;
	}
	
	@JsonIgnore
	public boolean isSuccessful() {
		return error == null;
	}
	
	public Collection<String> getFailedImageIdentifiers() {
		return failedImageIdentifiers;
	}
	
	public void setFailedImageIdentifiers(Collection<String> failedImageIdentifiers) {
		this.failedImageIdentifiers = failedImageIdentifiers;
	}
	
	public Collection<Image> getImages() {
		return images;
	}
	
	public void setImages(Collection<Image> images) {
		this.images = images;
	}

	public static class OriginalMetadata {
		private Integer width, height;
		private Date dateCreated;

		public Integer getWidth() {
			return width;
		}

		public void setWidth(Integer width) {
			this.width = width;
		}

		public Integer getHeight() {
			return height;
		}

		public void setHeight(Integer height) {
			this.height = height;
		}

		public Date getDateCreated() {
			return dateCreated;
		}

		public void setDateCreated(Date dateCreated) {
			this.dateCreated = dateCreated;
		}
	}

	public static class Image {
		private String imageIdentifier;
		private String s3Url;
		private Dimensions meta;

		public void setImageIdentifier(String imageIdentifier) {
			this.imageIdentifier = imageIdentifier;
		}

		public String getImageIdentifier() {
			return imageIdentifier;
		}

		public void setS3Url(String s3Url) {
			this.s3Url = s3Url;
		}

		public String getS3Url() {
			return s3Url;
		}

		public void setMeta(Dimensions meta) {
			this.meta = meta;
		}

		public Dimensions getMeta() {
			return meta;
		}

		public static class Dimensions {
			private Integer width, height;

			public Integer getHeight() {
				return height;
			}

			public void setHeight(Integer height) {
				this.height = height;
			}

			public Integer getWidth() {
				return width;
			}

			public void setWidth(Integer width) {
				this.width = width;
			}
		}
	}
}
