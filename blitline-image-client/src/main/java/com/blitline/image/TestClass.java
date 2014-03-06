package com.blitline.image;

public class TestClass {

	public static void main(String[] args) {
		varargs();
	}
	
	public static void varargs(Object... objects) {
		System.out.printf("I got %d objects%n", objects.length);
	}
}
