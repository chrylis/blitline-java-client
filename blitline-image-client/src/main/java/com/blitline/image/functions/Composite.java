package com.blitline.image.functions;

import java.net.URL;
import java.util.Collections;

import org.apache.commons.lang3.Validate;

import com.blitline.image.AzureLocation;
import com.blitline.image.S3Location;
import com.blitline.image.functions.params.Gravity;

public class Composite extends AbstractFunction {
	@Override
	public String getName() {
		return "composite";
	}

	public Composite(String src) {
		params.put("src", src);
	}

	public Composite(URL src) {
		params.put("src", src.toString());
	}

	public Composite(S3Location src) {
		params.put("src", Collections.singletonMap("s3_destination", src));
	}

	public Composite(AzureLocation src) {
		params.put("src", Collections.singletonMap("azure_destination", src));
	}

	public Composite scaledToMatchCurrent() {
		params.put("scale_to_match", true);
		return this;
	}

	public Composite asGrayscaleMask() {
		params.put("as_mask", true);
		return this;
	}

	public Composite atXOffset(int xOffset) {
		Validate.validState(!params.containsKey("gravity"), "only one of an x,y offset or gravity can be specified");
		params.put("x", xOffset);
		return this;
	}

	public Composite atYOffset(int yOffset) {
		Validate.validState(!params.containsKey("gravity"), "only one of an x,y offset or gravity can be specified");
		params.put("y", yOffset);
		return this;
	}
	
	public Composite atOffset(int xOffset, int yOffset) {
		Validate.validState(!params.containsKey("gravity"), "only one of an x,y offset or gravity can be specified");
		params.put("x", xOffset);
		params.put("y", yOffset);
		return this;
	}
	
	public Composite withGravity(Gravity gravity) {
		Validate.isTrue(!(params.containsKey("x") || params.containsKey("y")), "only one of an x,y offset or gravity can be specified");
		params.put("gravity", gravity);
		return this;
	}
	
	public Composite usingOperation(Operation op) {
		params.put("composite_op", op);
		return this;
	}
	
	public enum Operation {
		
	}
}
