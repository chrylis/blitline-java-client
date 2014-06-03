package com.blitline.image.functions;

public class SepiaTone extends AbstractFunction {

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "sepia_tone";
	}

	/**
	 * Specify the threshold of the effect as a fractional value from 0.0 to 1.0. Higher values are possible but not recommended.
	 *
	 * @param threshold
	 *            the sepia-tone threshold; 0.80 is a good default
	 * @return {@literal this}
	 * @see <a href="http://www.imagemagick.org/script/command-line-options.php#sepia-tone>the ImageMagick documentation</a>
	 */
	public SepiaTone threshold(double threshold) {
		params.put("threshold", threshold);
		return this;
	}
}
