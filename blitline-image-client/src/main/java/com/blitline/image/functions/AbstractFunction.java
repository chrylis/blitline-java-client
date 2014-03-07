package com.blitline.image.functions;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.Validate;

import com.blitline.image.Function;

/**
 * Abstract base class extended by all of the Blitline functions included in this library. Uses a plain {@code HashMap} as the store
 * for parameters and includes a few convenience methods for adding parameters that need validation.
 * 
 * @author Christopher Smith
 * 
 */
public abstract class AbstractFunction implements Function {

	public static final Pattern HTML_COLOR_PATTERN = Pattern.compile("\\A#?(\\p{XDigit}{6})\\z");

	protected Map<String, Object> params = new HashMap<String, Object>();

	public Map<String, Object> getParams() {
		return params;
	}

	public static String normalizeHtmlColor(String htmlColor) {
		Matcher m = HTML_COLOR_PATTERN.matcher(htmlColor);
		if (!m.matches())
			throw new IllegalArgumentException("the string \"" + htmlColor + "\" is not a valid HTML color");

		return "#" + m.group(1).toLowerCase(Locale.ENGLISH);
	}

	public static String rgbToHtmlColor(int red, int green, int blue) {
		if (red > 255 || red < 0 || green > 255 || green < 0 || blue > 255 || blue < 0)
			throw new IllegalArgumentException("all RGB values must be in the range of 0 to 255");

		return String.format("#%02x%02x%02x", red, green, blue);
	}

	protected void putOpacity(double opacity) {
		Validate.inclusiveBetween(0.0, 1.0, opacity);
		params.put("opacity", opacity);
	}

	protected void putRadius(double radius) {
		Validate.isTrue(radius >= 0., "radius must be nonnegative");
		params.put("radius", radius);
	}
}
