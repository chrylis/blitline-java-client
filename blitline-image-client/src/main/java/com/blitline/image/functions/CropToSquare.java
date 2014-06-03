package com.blitline.image.functions;

import com.blitline.image.functions.params.Gravity;

public class CropToSquare extends AbstractFunction {

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "crop_to_square";
	}

	public CropToSquare gravity(Gravity gravity) {
		params.put("gravity", gravity);
		return this;
	}
}
