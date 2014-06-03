package com.blitline.image;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonRootName("results")
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
@JsonInclude(Include.NON_EMPTY)
public class BlitlinePostResults implements Serializable {

	private static final long serialVersionUID = 1L;

	private String error;

	private String jobId;

	private List<Image> images;

	private Map<String,String> imageDestinations;

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	@JsonIgnore
	public Map<String,String> getImageDestinations() {
		if(imageDestinations == null && images != null) {
			Map<String, String> ids = new HashMap<String, String>();
			for(Image image : images)
				ids.put(image.getImageIdentifier(), image.getS3Url());

			imageDestinations = Collections.unmodifiableMap(ids);
		}

		return imageDestinations;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public boolean isSuccessful() {
		return error == null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("BlitlinePostResults[");
		if(isSuccessful()) {
			sb.append("job ID=").append(jobId).append(", images=").append(getImageDestinations());
		} else {
			sb.append("error=").append(error);
		}

		return sb.append(']').toString();
	}

	@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
	public static class Image {
		private String imageIdentifier;
		private String s3Url;

		public String getImageIdentifier() {
			return imageIdentifier;
		}

		public void setImageIdentifier(String imageIdentifier) {
			this.imageIdentifier = imageIdentifier;
		}

		public String getS3Url() {
			return s3Url;
		}

		public void setS3Url(String s3Url) {
			this.s3Url = s3Url;
		}

		@Override
		public String toString() {
			return new StringBuilder("\"").append(imageIdentifier).append("\"=>").append(s3Url).toString();
		}
	}
}
