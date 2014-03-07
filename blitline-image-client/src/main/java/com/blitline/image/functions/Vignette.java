package com.blitline.image.functions;

import org.apache.commons.lang3.Validate;

public class Vignette extends AbstractGaussianFunction<Vignette> {
	@Override
	public String getName() {
		return "vignette";
	}
	
	@Override
	protected Vignette getThis() {
		return this;
	}
	
	public Vignette backgroundColor(String color) {
		params.put("color", color);
		return this;
	}
	
	/**
	 * Sets the distance in pixels from the left and right edges of the image to the midline of the vignette effect.
	 * 
	 * @param xOffset the horizontal distance in pixels
	 * @return {@literal this}
	 */
	public Vignette xOffset(int xOffset) {
		params.put("x", xOffset);
		return this;
	}

	/**
	 * Sets the distance in pixels from the top and bottom edges of the image to the midline of the vignette effect.
	 * 
	 * @param yOffset the horizontal distance in pixels
	 * @return {@literal this}
	 */
	public Vignette yOffset(int yOffset) {
		params.put("y", yOffset);
		return this;
	}

	public Vignette threshold(double threshold) {
		Validate.inclusiveBetween(0.0, 1.0, threshold);
		params.put("threshold", threshold);
		return this;
	}
}
