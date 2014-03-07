package com.blitline.image.functions;

import java.net.URL;

public class Stegano extends AbstractFunction {
	@Override
	public String getName() {
		return "stegano";
	}
	
	public Stegano(String watermarkSrc) {
		params.put("watermark_url", watermarkSrc);
	}
	
	public Stegano(URL watermarkSrc) {
		this(watermarkSrc.toString());
	}
	
	public Stegano embeddingOffset(double pixelOffset) {
		params.put("offset", pixelOffset);
		return this;
	}
}
