package com.blitline.image.functions;

public class Sharpen extends AbstractGaussianFunction<Sharpen> {
	@Override
	public String getName() {
		return "sharpen";
	}
	
	@Override
	protected Sharpen getThis() {
		return this;
	}
}
