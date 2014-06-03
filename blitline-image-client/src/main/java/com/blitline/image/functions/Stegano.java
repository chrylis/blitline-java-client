package com.blitline.image.functions;

import java.net.URI;

public class Stegano extends AbstractFunction {

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "stegano";
	}

	public Stegano(String watermarkSrc) {
		params.put("watermark_url", watermarkSrc);
	}

	public Stegano(URI watermarkSrc) {
		this(watermarkSrc.toString());
	}

	public Stegano embeddingOffset(double pixelOffset) {
		params.put("offset", pixelOffset);
		return this;
	}
}
