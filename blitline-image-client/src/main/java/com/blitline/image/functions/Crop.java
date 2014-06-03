package com.blitline.image.functions;

import org.apache.commons.lang3.Validate;

public class Crop extends AbstractFunction {

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "crop";
	}

	public Crop(int width) {
		Validate.isTrue(width > 0);
		params.put("width", width);
	}

	public Crop height(int height) {
		Validate.isTrue(height > 0);
		params.put("height", height);
		return this;
	}

	public Crop xOffset(int xOffset) {
		Validate.isTrue(xOffset >= 0);
		params.put("x", xOffset);
		return this;
	}

	public Crop yOffset(int yOffset) {
		Validate.isTrue(yOffset >= 0);
		params.put("y", yOffset);
		return this;
	}

	public Crop atOffset(int xOffset, int yOffset) {
		// rewrite here to avoid partial update
		Validate.isTrue(xOffset >= 0 && yOffset >= 0);
		params.put("x", xOffset);
		params.put("y", yOffset);
		return this;
	}

	public Crop cropSmallerToAspectRatio() {
		Validate.isTrue(params.containsKey("height"), "aspect-ratio crop requires width and height to be set");
		params.put("preserve_aspect_if_smaller", Boolean.TRUE);
		return this;
	}
}
