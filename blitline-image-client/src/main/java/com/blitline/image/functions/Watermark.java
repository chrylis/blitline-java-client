package com.blitline.image.functions;


public class Watermark extends AbstractTextFunction<Watermark> {

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "watermark";
	}

	public Watermark(String text) {
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
