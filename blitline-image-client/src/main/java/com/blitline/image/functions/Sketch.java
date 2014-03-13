package com.blitline.image.functions;

public class Sketch extends AbstractGaussianFunction<Sketch> {
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
