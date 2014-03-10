package com.blitline.image.functions;

public class Trim extends AbstractFunction {
	
	public static final Trim INSTANCE = new Trim();
	
	@Override
	public String getName() {
		return "trim";
	}
}
