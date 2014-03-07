package com.blitline.image.functions;

import com.blitline.image.functions.params.Gravity;

public class PadResizeToFit extends AbstractFunction {
	@Override
	public String getName() {
		return "pad";
	}
	
	public PadResizeToFit(int width, int height) {
		params.put("width", width);
		params.put("height", height);
	}
	
	public PadResizeToFit gravity(Gravity gravity) {
		params.put("gravity", gravity);
		return this;
	}
	
	public PadResizeToFit color(String color) {
		params.put("color", color);
		return this;
	}
}
