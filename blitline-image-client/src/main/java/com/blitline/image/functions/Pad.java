package com.blitline.image.functions;

import com.blitline.image.functions.params.Gravity;

public class Pad extends AbstractFunction {
	@Override
	public String getName() {
		return "pad";
	}
	
	public Pad(int thickness) {
		params.put("size", thickness);
	}
	
	public Pad gravity(Gravity gravity) {
		params.put("gravity", gravity);
		return this;
	}
	
	public Pad color(String color) {
		params.put("color", color);
		return this;
	}
}
