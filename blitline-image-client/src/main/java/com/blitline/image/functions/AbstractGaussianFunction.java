package com.blitline.image.functions;

import org.apache.commons.lang3.Validate;

public abstract class AbstractGaussianFunction<T extends AbstractGaussianFunction<T>> extends AbstractFunction {

	public AbstractGaussianFunction() {
		super();
	}
	
	protected abstract T getThis();

	public T sigma(double sigma) {
		Validate.isTrue(sigma >= 0, "sigma must be non-negative");
		params.put("sigma", sigma);
		return getThis();
	}

	public T radius(double radius) {
		Validate.isTrue(radius >= 0, "radius must be non-negative");
		putRadius(radius);
		return getThis();
	}

}