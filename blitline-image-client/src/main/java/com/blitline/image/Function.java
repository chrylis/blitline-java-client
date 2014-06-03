package com.blitline.image;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * A Blitline image-processing function. Implementing classes are expected to ensure that their required parameters are collected
 * and to expose all parameters in the {@code params} {@code Map} property with the Blitline parameter name as the key and a value
 * that is serializable to a sensible JSON value.
 *
 * @author Christopher Smith
 *
 */
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
@JsonInclude(Include.NON_EMPTY)
@JsonFormat(shape = Shape.STRING)
public interface Function extends Serializable {
	String getName();

	Map<String, Object> getParams();

	Collection<Function> getFunctions();

	// returns this
	Function andSaveResult(String imageIdentifier);

	// returns this
	Function andSaveResultTo(SavedImage location);

	// returns this
	Function andSkipSave(String imageIdentifier);

	// returns this
	Function thenApply(Function... functions);
}
