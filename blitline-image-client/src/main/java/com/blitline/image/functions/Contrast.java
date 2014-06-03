package com.blitline.image.functions;

public class Contrast extends AbstractFunction {

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "contrast";
	}

	public Contrast sharpen() {
		params.put("sharpen", Boolean.TRUE);
		return this;
	}
}
