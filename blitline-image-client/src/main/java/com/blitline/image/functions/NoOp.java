package com.blitline.image.functions;

public class NoOp extends AbstractFunction {
	
	public static final NoOp INSTANCE = new NoOp();
	
	@Override
	public String getName() {
		return "no_op";
	}
}
