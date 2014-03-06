package com.blitline.image.functions;

public class Blur extends AbstractFunction {
	@Override
	public String getName() {
		return "blur";
	}
	
	public Blur sigma(double sigma) {
		params.put("sigma", sigma);
		return this;
	}
	
	public Blur radius(double radius) {
		putRadius(radius);
		return this;
	}
}
