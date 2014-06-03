package com.blitline.image.functions;

public class Density extends AbstractFunction {

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "density";
	}

	public Density(int dpi) {
		params.put("dpi", dpi);
	}
}
