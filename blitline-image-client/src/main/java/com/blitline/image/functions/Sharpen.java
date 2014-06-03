package com.blitline.image.functions;

public class Sharpen extends AbstractGaussianFunction<Sharpen> {

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "sharpen";
	}

	@Override
	protected Sharpen getThis() {
		return this;
	}
}
