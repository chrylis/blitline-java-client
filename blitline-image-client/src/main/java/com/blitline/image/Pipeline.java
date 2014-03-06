package com.blitline.image;

import java.util.List;


public class Pipeline {
	
	private List<Pipeline> functions;

	public Pipeline() {
		// TODO Auto-generated constructor stub
	}

	public Pipeline andSave(String imageIdentifier) {
		return null;
	}
	
	//public void annotate(String text, lotsa optionals)
	
	//public void append(boolean appendVertically, Str) how can these both be optional?
	
	public void autoLevel() {
	}
	
	public void autoGamma(){}
	
	public void autoEnhance() {}
	
	public void blur(double sigma, double radius){}
	
	public void backgroundColor(String htmlColor) {}
	public void backgroundColor(int red, int green, int blue) {}
	
	
}
