package com.blitline.image.functions;

public class Density extends AbstractFunction {
	@Override
	public String getName() {
		return "density";
	}
	
	public Density(int dpi) {
		params.put("dpi", dpi);
	}
}
