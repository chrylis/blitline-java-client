package com.blitline.image.functions;

import org.apache.commons.lang3.Validate;

import com.blitline.image.functions.params.Gravity;

public abstract class AbstractTextFunction<T extends AbstractTextFunction<T>> extends AbstractFunction {

	private static final long serialVersionUID = 1L;

	protected AbstractTextFunction(String text) {
		params.put("text", text);
	}

	protected abstract T getThis();

	public T gravity(Gravity gravity) {
		params.put("gravity", gravity);
		return getThis();
	}

	public T pointSize(int pointSize) {
		Validate.isTrue(pointSize > 0, "text size in points must be positive");

		params.put("point_size", pointSize);
		return getThis();
	}

	public T fontFamily(String fontFamily){
		params.put("font_family", fontFamily);
		return getThis();
	}
}
