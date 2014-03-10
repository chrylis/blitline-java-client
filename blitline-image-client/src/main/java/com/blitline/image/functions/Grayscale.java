package com.blitline.image.functions;

public class Grayscale extends AbstractFunction {
	
	public static final Grayscale INSTANCE = new Grayscale();
	
	@Override
	public String getName() {
		return "gray_colorspace";
	}
}
