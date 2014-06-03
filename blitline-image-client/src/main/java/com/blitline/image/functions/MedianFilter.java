package com.blitline.image.functions;

public class MedianFilter extends AbstractFunction {

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "median_filter";
	}

	public MedianFilter radius(double radius) {
		putRadius(radius);
		return this;
	}
}
