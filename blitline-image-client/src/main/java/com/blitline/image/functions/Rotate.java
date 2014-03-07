package com.blitline.image.functions;

/**
 * Rotates the image the specified angle clockwise. Negative angles rotate the image counter-clockwise.
 * 
 * @author Christopher Smith
 * 
 */
public class Rotate extends AbstractFunction {
	@Override
	public String getName() {
		return "rotate";
	}

	public Rotate(double degrees) {
		params.put("amount", degrees);
	}
}
