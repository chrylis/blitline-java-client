package com.blitline.image.functions;

import com.blitline.image.functions.params.Gravity;

public class ResizeToFill extends AbstractFunction {

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "resize_to_fill";
	}

	/**
	 * Specify the maximum dimensions of the resized image. You may specify zero for one of the dimensions to leave it
	 * unconstrained.
	 *
	 * @param width
	 *            the maximum width of the resulting image, or zero for any width
	 * @param maxHeight
	 *            the maximum height of the resulting image, or zero for any height
	 */
	public ResizeToFill(int width, int maxHeight) {
		if (width == 0)
			throw new IllegalArgumentException("width must be specified");

		params.put("width", width);

		if (maxHeight > 0)
			params.put("height", maxHeight);
	}

	/**
	 * Do not upscale the image if the original is already smaller than the maximum dimensions. Sets the {@code only_shrink_larger}
	 * JSON option.
	 *
	 * @return {@literal this}
	 */
	public ResizeToFill doNotUpscale() {
		params.put("only_shrink_larger", Boolean.TRUE);
		return this;
	}

	public ResizeToFill gravity(Gravity gravity) {
		params.put("gravity", gravity);
		return this;
	}
}
