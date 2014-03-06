package com.blitline.image.functions;

public class MedianFilter extends AbstractFunction {
	@Override
	public String getName() {
		return "median_filter";
	}

	public MedianFilter radius(double radius) {
		putRadius(radius);
		return this;
	}
}
