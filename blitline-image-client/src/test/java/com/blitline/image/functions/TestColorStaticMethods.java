package com.blitline.image.functions;

import org.junit.Test;

import static com.blitline.image.functions.AbstractFunction.normalizeHtmlColor;
import static com.blitline.image.functions.AbstractFunction.rgbToHtmlColor;
import static org.junit.Assert.*;

public class TestColorStaticMethods {

	@Test
	public void testNormalizeColor() {
		assertEquals("#ffffff", normalizeHtmlColor("FFFFFF"));
		assertEquals("#ffffff", normalizeHtmlColor("#FfFfFf"));
		assertEquals("#048b10", normalizeHtmlColor("048B10"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testShortColor() {
		normalizeHtmlColor("#fff");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonHex() {
		normalizeHtmlColor("#f0gf00");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLongColor() {
		normalizeHtmlColor("#f0ffff00");
	}
	
	@Test
	public void testRgb() {
		assertEquals("#ffffff", rgbToHtmlColor(255, 255, 255));
		assertEquals("#0f0f0f", rgbToHtmlColor(15, 15, 15));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRgbHigh() {
		rgbToHtmlColor(256, 123, 123);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testRgbLow() {
		rgbToHtmlColor(123, -1, 123);
	}
}
