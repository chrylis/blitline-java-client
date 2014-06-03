package com.blitline.image.functions;

public class PhotographEffect extends AbstractFunction {

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "photograph";
	}

	public PhotographEffect rotateClockwise(double degrees) {
		params.put("angle", degrees);
		return this;
	}
}
