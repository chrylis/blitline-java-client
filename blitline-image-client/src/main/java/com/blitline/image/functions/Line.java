package com.blitline.image.functions;

import org.apache.commons.lang3.Validate;

import com.blitline.image.functions.params.LineCap;

public class Line extends AbstractFunction {
	@Override
	public String getName() {
		return "line";
	}
	
	public Line(int x1, int y1, int x2, int y2) {
		params.put("x", x1);
		params.put("y", y1);
		params.put("x1", x2);
		params.put("y1", y2);
	}
	
	public Line width(int width) {
		Validate.isTrue(width > 0, "line width must be positive");
		params.put("width", width);
		return this;
	}
	
	public Line color(String color) {
		params.put("color", color);
		return this;
	}
	
	public Line opacity(double opacity) {
		putOpacity(opacity);
		return this;
	}
	
	public Line capWith(LineCap cap) {
		params.put("line_cap", cap);
		return this;
	}
}
