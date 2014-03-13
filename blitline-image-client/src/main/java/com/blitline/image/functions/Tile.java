package com.blitline.image.functions;

import java.net.URI;

import org.apache.commons.lang3.Validate;

public class Tile extends AbstractFunction {
	@Override
	public String getName() {
		return "tile";
	}

	public Tile(String src) {
		params.put("src", src);
	}
	
	public Tile(URI src) {
		this(src.toString());
	}

	public Tile scaledAt(double scaleFactor) {
		Validate.validState(!params.containsKey("width"), "only one of scale factor or explicit size may be specified");
		params.put("scale", scaleFactor);
		return this;
	}

	public Tile resizedTo(int width, int height) {
		Validate.validState(!params.containsKey("scale"), "only one of scale factor or explicit size may be specified");
		Validate.isTrue(width > 0, "width must be positive");
		Validate.isTrue(height > 0, "height must be positive");
		params.put("width", width);
		params.put("height", height);
		return this;
	}
}
