package com.blitline.image.functions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

	protected Map<String, Object> params = new HashMap<String, Object>();

	public Map<String, Object> getParams() {
		return Collections.unmodifiableMap(params);
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
