package com.blitline.image.functions;

public class AutoEnhance extends AbstractFunction {
	
	public static final AutoEnhance INSTANCE = new AutoEnhance();
	
	@Override
	public String getName() {
		return "auto_enhance";
	}
}
