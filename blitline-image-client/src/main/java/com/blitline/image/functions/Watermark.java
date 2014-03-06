package com.blitline.image.functions;


public class Watermark extends AbstractTextFunction<Watermark> {

	@Override
	public String getName() {
		return "watermark";
	}

	Watermark(String text) {
		super(text);
	}

	protected Watermark getThis() {
		return this;
	}

	public Watermark opacity(double opacity) {
		putOpacity(opacity);
		return this;
	}
}
