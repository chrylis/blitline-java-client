package com.blitline.image.functions;

import org.apache.commons.lang3.Validate;

public class Quantize extends AbstractFunction {

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "quantize";
	}

	public Quantize(int numberOfColors) {
		Validate.isTrue(numberOfColors > 1, "can't quantize to fewer than 2 colors");
		params.put("number_colors", numberOfColors);
	}

	public Quantize inColorSpace(String colorSpace) {
		params.put("color_space", colorSpace);
		return this;
	}

	public Quantize withDithering() {
		params.put("dither", Boolean.TRUE);
		return this;
	}
}
