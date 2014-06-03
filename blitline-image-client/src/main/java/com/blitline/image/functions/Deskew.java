package com.blitline.image.functions;

import org.apache.commons.lang3.Validate;

public class Deskew extends AbstractFunction {

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "deskew";
	}

	public Deskew threshold(double threshold) {
		Validate.inclusiveBetween(0.0, 1.0, threshold);
		params.put("threshold", threshold);
		return this;
	}
}
