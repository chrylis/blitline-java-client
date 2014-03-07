package com.blitline.image.functions;

import java.net.URL;

import com.blitline.image.S3Location;

public class Composite extends AbstractFunction {
	@Override
	public String getName() {
		return "composite";
	}
	
	public Composite(String src) {
		params.put("src", src);
	}
	
	public Composite(URL src) {
		params.put("src", src.toString());
	}
	
	public Composite(S3Location src) {
		params.put
	}
}
