package com.blitline.image.functions;

public class Modulate extends AbstractFunction {

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "modulate";
	}

	public Modulate scaleBrightness(double scale) {
		params.put("brightness", scale);
		return this;
	}

	public Modulate scaleSaturation(double scale) {
		params.put("saturation", scale);
		return this;
	}

	/**
	 * Rotates the image's hues through the HSL color space.
	 *
	 * @param rotation
	 *            A value from 0.0 to 2.0, where 1.0 (the default value) indicates no change, a value below 1.0 represents a
	 *            counter-clockwise rotation, and a value above 1.0 represents a clockwise rotation. The rotation is linear along
	 *            the range [0..2] so that 0.5 represents a 90-degree counter-clockwise rotation, and both 0.0 and 2.0 represent a
	 *            complete 180-degree rotation of the color space.
	 * @return this {@code Modulate} object
	 *
	 * @see <a href="http://www.imagemagick.org/script/command-line-options.php#modulate">the ImageMagick documentation for the operation</a>
	 */
	public Modulate rotateHue(double rotation) {
		params.put("hue", rotation);
		return this;
	}
}
