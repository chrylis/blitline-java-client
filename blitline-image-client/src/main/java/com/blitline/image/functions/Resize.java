package com.blitline.image.functions;

public class Resize extends AbstractFunction {

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "resize";
	}

	public Resize(int width, int height) {
		params.put("width", width);
		params.put("height", height);
	}
}
