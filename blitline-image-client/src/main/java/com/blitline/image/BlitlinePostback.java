package com.blitline.image;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonRootName("results")
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class BlitlinePostback {

	public static final SimpleDateFormat BLITLINE_DATE_FORMAT = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", Locale.US);

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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("BlitlinePostback[jobId=");
		sb.append(jobId).append(", successful=").append(isSuccessful());
		if (isSuccessful())
			sb.append(", ").append(images.size()).append(" images processed");
		else
			sb.append(", error=").append(error);
		sb.append(']');
		return sb.toString();
	}

	@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
	public static class OriginalMetadata {
		private Integer width, height;
		private Date dateCreated;
		
		public OriginalMetadata() {
		}
		
		public OriginalMetadata(Integer width, Integer height, Date dateCreated) {
			this.width = width;
			this.height = height;
			if(dateCreated != null)
				this.dateCreated = new Date(dateCreated.getTime());
		}

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

		@Override
		public String toString() {
			return "size " + width + 'x' + height + ", created " + dateCreated;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
			result = prime * result + ((height == null) ? 0 : height.hashCode());
			result = prime * result + ((width == null) ? 0 : width.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof OriginalMetadata))
				return false;
			OriginalMetadata other = (OriginalMetadata) obj;
			if (dateCreated == null) {
				if (other.dateCreated != null)
					return false;
			} else if (!dateCreated.equals(other.dateCreated))
				return false;
			if (height == null) {
				if (other.height != null)
					return false;
			} else if (!height.equals(other.height))
				return false;
			if (width == null) {
				if (other.width != null)
					return false;
			} else if (!width.equals(other.width))
				return false;
			return true;
		}
	}

	@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
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

		@Override
		public String toString() {
			return new StringBuilder("image[identifier=").append(imageIdentifier)
				.append(", size=").append(meta)
				.append(", s3Url=").append(s3Url).append(']').toString();
		}

		@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
		public static class Dimensions {
			private Integer width, height;

			public Dimensions() {
			}
			
			public Dimensions(Integer width, Integer height) {
				this.width = width;
				this.height = height;
			}
			
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

			@Override
			public String toString() {
				return width + "x" + height;
			}

			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + ((height == null) ? 0 : height.hashCode());
				result = prime * result + ((width == null) ? 0 : width.hashCode());
				return result;
			}

			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (!(obj instanceof Dimensions))
					return false;
				Dimensions other = (Dimensions) obj;
				if (height == null) {
					if (other.height != null)
						return false;
				} else if (!height.equals(other.height))
					return false;
				if (width == null) {
					if (other.width != null)
						return false;
				} else if (!width.equals(other.width))
					return false;
				return true;
			}
		}
	}
}
