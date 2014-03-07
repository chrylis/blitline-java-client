package com.blitline.image.functions;

import org.apache.commons.lang3.Validate;

public class Unsharpen extends AbstractGaussianFunction<Unsharpen> {
	@Override
	public String getName() {
		return "unsharp_mask";
	}

	@Override
	protected Unsharpen getThis() {
		return this;
	}

	public Unsharpen amount(double amount) {
		Validate.inclusiveBetween(0.0, 1.0, amount);
		params.put("amount", amount);
		return this;
	}
	
	public Unsharpen threshold(double threshold) {
		Validate.inclusiveBetween(0.0, 1.0, threshold);
		params.put("threshold", threshold);
		return this;
	}
}
