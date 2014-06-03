package com.blitline.image.functions;

public class Blur extends AbstractGaussianFunction<Blur> {

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "blur";
	}

	@Override
	protected Blur getThis() {
		return this;
	}
}
