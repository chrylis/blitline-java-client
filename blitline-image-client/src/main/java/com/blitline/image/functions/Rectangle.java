package com.blitline.image.functions;

import org.apache.commons.lang3.Validate;

public class Rectangle extends AbstractFunction {
	@Override
	public String getName() {
		return "line";
	}
	
	Rectangle(int x1, int y1, int x2, int y2) {
		params.put("x", x1);
		params.put("y", y1);
		params.put("x1", x2);
		params.put("y1", y2);
	}
	
	public Rectangle strokeColor(String color) {
		params.put("color", color);
		return this;
	}
	
	public Rectangle strokeWidth(int width) {
		Validate.isTrue(width > -1, "line width must be non-negative");
		params.put("stroke_width", width);
		return this;
	}
	
	public Rectangle strokeOpacity(double opacity) {
		Validate.inclusiveBetween(0.0, 1.0, opacity);
		params.put("stroke_opacity", opacity);
		return this;
	}
	
	public Rectangle fillColor(String color) {
		params.put("fill_color", color);
		return this;
	}
	
	public Rectangle fillOpacity(double opacity) {
		Validate.inclusiveBetween(0.0, 1.0, opacity);
		params.put("fill_opacity", opacity);
		return this;
	}
	
	public Rectangle roundedCorners(int cornerWidth, int cornerHeight) {
		params.put("cw", cornerWidth);
		params.put("ch", cornerHeight);
		return this;
	}
}
