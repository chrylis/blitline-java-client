package com.blitline.image.functions;

public class GammaChannel extends AbstractFunction {
	@Override
	public String getName() {
		return "gamma_channel";
	}
	
	public GammaChannel(double gammaAdjustment) {
		params.put("gamma", gammaAdjustment);
	}
}
