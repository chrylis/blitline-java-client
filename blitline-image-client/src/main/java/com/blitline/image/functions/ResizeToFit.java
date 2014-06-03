package com.blitline.image.functions;

public class ResizeToFit extends AbstractFunction {

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "resize_to_fit";
	}

	/**
	 * Specify the maximum dimensions of the resized image. You may specify zero for one of the dimensions to leave it
	 * unconstrained.
	 *
	 * @param maxWidth
	 *            the maximum width of the resulting image, or zero for any width
	 * @param maxHeight
	 *            the maximum height of the resulting image, or zero for any height
	 */
	public ResizeToFit(int maxWidth, int maxHeight) {
		if (maxWidth == 0 && maxHeight == 0)
			throw new IllegalArgumentException("at least one maximum dimension must be specified");

		if (maxWidth > 0)
			params.put("width", maxWidth);

		if (maxHeight > 0)
			params.put("height", maxHeight);
	}

	/**
	 * Do not upscale the image if the original is already smaller than the maximum dimensions. Sets the {@code only_shrink_larger}
	 * JSON option.
	 *
	 * @return {@literal this}
	 */
	public ResizeToFit doNotUpscale() {
		params.put("only_shrink_larger", Boolean.TRUE);
		return this;
	}
}
