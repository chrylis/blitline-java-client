package com.blitline.image.functions;

public class Trim extends AbstractFunction {

	private static final long serialVersionUID = 1L;

	public static final Trim INSTANCE = new Trim();

	@Override
	public String getName() {
		return "trim";
	}
}
