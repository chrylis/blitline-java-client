package com.blitline.image.functions;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;

import com.blitline.image.Function;
import com.blitline.image.SavedImage;

/**
 * Abstract base class extended by all of the Blitline functions included in this library. Uses a plain {@code HashMap} as the store
 * for parameters and includes a few convenience methods for adding parameters that need validation.
 * 
 * @author Christopher Smith
 * 
 */
public abstract class AbstractFunction implements Function {

	protected Map<String, Object> params = new HashMap<String, Object>();
	
	private SavedImage saveLocation;
	
	private List<Function> childFunctions = new LinkedList<Function>();

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
	
	public Function andSaveResultTo(SavedImage location) {
		Validate.validState(saveLocation == null, "only one save location is supported for each function; to save the output of a pipeline in multiple formats or locations, add a child NoOp function");
		saveLocation = location;
		return this;
	}
	
	public Function andSaveResult(String imageIdentifier) {
		return andSaveResultTo(SavedImage.withId(imageIdentifier).toBlitlineContainer());
	}
	
	public SavedImage getSave() {
		return saveLocation;
	}
	
	public Function thenApply(Function... functions) {
		childFunctions.addAll(Arrays.asList(functions));
		return this;
	}
	
	public List<Function> getFunctions() {
		return Collections.unmodifiableList(childFunctions);
	}
}
