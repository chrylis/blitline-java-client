package com.blitline.image.functions;

public class Blur extends AbstractGaussianFunction<Blur> {
	@Override
	public String getName() {
		return "blur";
	}
	
	@Override
	protected Blur getThis() {
		return this;
	}
}
