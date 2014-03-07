package com.blitline.image.functions;

public class Scale extends AbstractFunction {
	@Override
	public String getName() {
		return "resize";
	}
	
	public Scale(double scaleFactor) {
		params.put("scale_factor", scaleFactor);
	}
}
