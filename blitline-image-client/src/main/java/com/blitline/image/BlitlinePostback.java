package com.blitline.image;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonRootName("results")
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class BlitlinePostback implements Serializable {

	private static final long serialVersionUID = 1L;

	private OriginalMetadata originalMeta;

	private String jobId;

	private Collection<String> errors = Collections.emptySet();

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

	public Collection<String> getErrors() {
		return errors;
	}

	public void setErrors(Collection<String> errors) {
		this.errors = errors;
	}

	@JsonIgnore
	public boolean isSuccessful() {
		return errors.isEmpty();
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

	@JsonIgnore
	public boolean isIdentifyOnly() {
		return isSuccessful() ?
			images.isEmpty() :
			(failedImageIdentifiers.size() == 1 &&
			failedImageIdentifiers.iterator().next().endsWith(BlitlineImageJob.IDENTIFY_ONLY_SUFFIX));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("BlitlinePostback[jobId=");
		sb.append(jobId).append(", successful=").append(isSuccessful());
		if (isSuccessful())
			sb.append(", ").append(images.size()).append(" images processed");
		else
			sb.append(", errors=").append(errors);
		sb.append(']');
		return sb.toString();
	}

	@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class OriginalMetadata implements Serializable {

		private static final long serialVersionUID = 1L;

		private Integer width, height, filesize;

		private Date isoDateCreated;

		private Map<String, String> allExif = Collections.emptyMap();

		public OriginalMetadata() {
		}

		public OriginalMetadata(Integer width, Integer height, Date isoDateCreated) {
			this.width = width;
			this.height = height;
			if (isoDateCreated != null)
				this.isoDateCreated = new Date(isoDateCreated.getTime());
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

		public Integer getFilesize() {
			return filesize;
		}

		public void setFilesize(Integer filesize) {
			this.filesize = filesize;
		}

		@Deprecated
		@JsonIgnore
		public Date getDateCreated() {
			return isoDateCreated;
		}

		public Date getIsoDateCreated() {
		    return isoDateCreated;
		}

		public void setIsoDateCreated(Date isoDateCreated) {
            this.isoDateCreated = isoDateCreated;
        }

		@JsonDeserialize(using = ExifDeserializer.class)
		public void setAllExif(Map<String, String> allExif) {
			this.allExif = allExif;
		}

		public Map<String, String> getAllExif() {
			return allExif;
		}

		@Override
		public String toString() {
			return "size " + width + 'x' + height + ", created " + isoDateCreated;
		}
	}

	@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
	public static class Image implements Serializable {

		private static final long serialVersionUID = 1L;

		private String imageIdentifier;
		private String s3Url;
		private ImageMeta meta;

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

		public void setMeta(ImageMeta meta) {
			this.meta = meta;
		}

		public ImageMeta getMeta() {
			return meta;
		}

		@Override
		public String toString() {
			return new StringBuilder("image[identifier=").append(imageIdentifier)
				.append(", size=").append(meta)
				.append(", s3Url=").append(s3Url).append(']').toString();
		}

		@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class ImageMeta {
			private Integer width, height, filesize, depth, quality;
			private String density;

			public ImageMeta() {
			}

			public ImageMeta(Integer width, Integer height) {
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

			public Integer getFilesize() {
				return filesize;
			}

			public void setFilesize(Integer filesize) {
				this.filesize = filesize;
			}

			public Integer getDepth() {
				return depth;
			}

			public void setDepth(Integer depth) {
				this.depth = depth;
			}

			public Integer getQuality() {
				return quality;
			}

			public void setQuality(Integer quality) {
				this.quality = quality;
			}

			public String getDensity() {
				return density;
			}

			public void setDensity(String density) {
				this.density = density;
			}

			@Override
			public String toString() {
				StringBuilder sb = new StringBuilder().append(width).append('x').append(height);
				if (filesize != null)
					sb.append(", ").append(filesize).append(" bytes");
				if (depth != null)
					sb.append(", depth=").append(depth);
				if (quality != null)
					sb.append(", quality=").append(quality);
				if (density != null)
					sb.append(", density=").append(density);
				return sb.toString();
			}
		}
	}

	public static class ExifDeserializer extends JsonDeserializer<Map<String, String>> {
		@Override
		public Map<String, String> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException,
			JsonProcessingException {

			Map<String, String> map = new HashMap<String, String>();

			JsonToken token;

			if ((token = jp.getCurrentToken()) != JsonToken.START_ARRAY)
				throw new JsonMappingException("expected start of array, but found " + token, jp.getCurrentLocation());

			while ((token = jp.nextToken()) == JsonToken.START_ARRAY) {
				map.put(jp.nextTextValue(), jp.nextTextValue());
				if ((token = jp.nextToken()) != JsonToken.END_ARRAY)
					throw new JsonMappingException("expected a 2-valued array, but next token was a " + token,
						jp.getCurrentLocation());
			}

			return map;
		}
	}
}
