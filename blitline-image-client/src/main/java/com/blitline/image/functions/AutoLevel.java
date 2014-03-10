package com.blitline.image.functions;

public class AutoLevel extends AbstractFunction {
	
	public static final AutoLevel INSTANCE = new AutoLevel();
	
	@Override
	public String getName() {
		return "auto_level";
	}
}
