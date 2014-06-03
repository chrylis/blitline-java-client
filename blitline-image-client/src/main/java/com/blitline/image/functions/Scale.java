package com.blitline.image.functions;

public class Scale extends AbstractFunction {

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "resize";
	}

	public Scale(double scaleFactor) {
		params.put("scale_factor", scaleFactor);
	}
}
