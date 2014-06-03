package com.blitline.image.functions;

import org.apache.commons.lang3.Validate;

public class Resample extends AbstractFunction {

	private static final long serialVersionUID = 1L;

	public Resample(int dpi) {
		Validate.isTrue(dpi > 0);
		params.put("density", dpi);
	}

	@Override
	public String getName() {
		return "resample";
	}

}
