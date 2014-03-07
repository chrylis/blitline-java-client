package com.blitline.image;

import java.util.Map;

/**
 * A Blitline image-processing function. Implementing classes are expected to ensure that their required parameters are collected
 * and to expose all parameters in the {@code params} {@code Map} property with the Blitline parameter name as the key and a value
 * that is serializable to a sensible JSON value.
 * 
 * @author Christopher Smith
 * 
 */
public interface Function {
	String getName();

	Map<String, Object> getParams();
}
