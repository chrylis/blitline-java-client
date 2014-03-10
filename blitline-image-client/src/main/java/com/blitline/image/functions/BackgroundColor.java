package com.blitline.image.functions;

public class BackgroundColor extends AbstractFunction {
	@Override
	public String getName() {
		return "background_color";
	}
	
	public BackgroundColor color(String color) {
		params.put("color", color);
		return this;
	}
	
	public BackgroundColor of(String color) {
		return color(color);
	}
	
	public BackgroundColor with(String color) {
		return color(color);
	}
}
