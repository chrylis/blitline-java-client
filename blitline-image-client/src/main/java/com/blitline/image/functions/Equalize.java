package com.blitline.image.functions;

public class Equalize extends AbstractFunction {
	
	public static final Equalize INSTANCE = new Equalize();
	
	@Override
	public String getName() {
		return "gray_colorspace";
	}
}
