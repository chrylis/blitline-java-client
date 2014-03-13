package com.blitline.image.functions;

import org.apache.commons.lang3.Validate;

public class Ellipse extends AbstractFunction {
	@Override
	public String getName() {
		return "ellipse";
	}
	
	public Ellipse(int centerX, int centerY, int width, int height) {
		Validate.isTrue(width >= 0 && height >= 0, "height and width of ellipse must be positive");
		params.put("origin_x", centerX);
		params.put("origin_y", centerY);
		params.put("ellipse_width", width);
		params.put("ellipse_height", height);
	}
	
	public Ellipse strokeWidth(int strokeWidth) {
		Validate.isTrue(strokeWidth >= 0);
		params.put("stroke_width", strokeWidth);
		return this;
	}
	
	// default white
	public Ellipse strokeColor(String strokeColor) {
		params.put("color", strokeColor);
		return this;
	}
	
	public Ellipse strokeOpacity(double strokeOpacity) {
		Validate.inclusiveBetween(0., 1., strokeOpacity);
		params.put("stroke_opacity", strokeOpacity);
		return this;
	}
	
	public Ellipse fillColor(String fillColor) {
		params.put("fill_color", fillColor);
		return this;
	}

	public Ellipse fillOpacity(double fillOpacity) {
		Validate.inclusiveBetween(0., 1., fillOpacity);
		params.put("fill_opacity", fillOpacity);
		return this;
	}
}
