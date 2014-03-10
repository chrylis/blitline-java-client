package com.blitline.image.functions;

public class Despeckle extends AbstractFunction {
	
	public static final Despeckle INSTANCE = new Despeckle();
	
	@Override
	public String getName() {
		return "despeckle";
	}
}
