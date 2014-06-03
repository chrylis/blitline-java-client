package com.blitline.image.functions;

import com.blitline.image.functions.params.FontStyle;

public class Annotate extends AbstractTextFunction<Annotate> {

	private static final long serialVersionUID = 1L;

	public Annotate(String text) {
		super(text);
	}

	@Override
	protected Annotate getThis() {
		return this;
	}

	@Override
	public String getName() {
		return "annotate";
	}

	public Annotate xOffset(int xOffset) {
		params.put("x", xOffset);
		return this;
	}

	public Annotate yOffset(int yOffset) {
		params.put("y", yOffset);
		return this;
	}

	public Annotate offset(int xOffset, int yOffset) {
		params.put("x", xOffset);
		params.put("y", yOffset);
		return this;
	}

	public Annotate color(String htmlColor) {
		// FIXME: validate?
		params.put("color", htmlColor);
		return this;
	}

	public Annotate fontStyle(FontStyle fontStyle) {
		params.put("style", fontStyle);
		return this;
	}

	public Annotate kerning(double kerning) {
		params.put("kerning", kerning);
		return this;
	}

	public Annotate strokeColor(String htmlColor) {
		//FIXME
		return this;
	}

	//dropShadowColor

	public Annotate dropShadowOffset(int pixels) {
		params.put("dropshadow_offset", pixels);
		return this;
	}
}
