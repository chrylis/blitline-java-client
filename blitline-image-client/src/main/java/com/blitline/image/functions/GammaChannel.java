package com.blitline.image.functions;

public class GammaChannel extends AbstractFunction {

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "gamma_channel";
	}

	public GammaChannel(double gammaAdjustment) {
		params.put("gamma", gammaAdjustment);
	}
}
