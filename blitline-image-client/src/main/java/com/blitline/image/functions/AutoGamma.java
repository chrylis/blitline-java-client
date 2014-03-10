package com.blitline.image.functions;

public class AutoGamma extends AbstractFunction {
	
	public static final AutoGamma INSTANCE = new AutoGamma();
	
	@Override
	public String getName() {
		return "auto_gamma";
	}
}
