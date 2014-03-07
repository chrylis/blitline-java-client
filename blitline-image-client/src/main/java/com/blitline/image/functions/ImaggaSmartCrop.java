package com.blitline.image.functions;

public class ImaggaSmartCrop extends AbstractFunction {
	@Override
	public String getName() {
		return "imagga_smart_crop";
	}
	
	public ImaggaSmartCrop(int width, int height) {
		params.put("resolution", height + "x" + width);
	}
	
	public ImaggaSmartCrop withNoScaling() {
		params.put("no_scaling", Boolean.TRUE);
		return this;
	}
}
