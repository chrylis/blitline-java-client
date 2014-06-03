package com.blitline.image.functions;

public class Sketch extends AbstractGaussianFunction<Sketch> {

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "sketch";
	}

	@Override
	protected Sketch getThis() {
		return this;
	}

	public Sketch sketchAngle(double degrees) {
		params.put("angle", degrees);
		return this;
	}
}
