package com.blitline.image.functions;

import static com.blitline.image.functions.AbstractFunction.HTML_COLOR_PATTERN;
import static org.junit.Assert.*;

import java.util.regex.Matcher;

import org.junit.Test;

public class TestPatterns {

	@Test
	public void testHtmlColorPattern() {
		final String DARK_ORANGE = "#FF8C00",
				MEDIUM_TURQUOISE = "48d1cc",
				TOO_SHORT_COLOR = "8d1cc",
				CSS_RED = "#f00",
				NONSENSE_CHAR = "@123456",
				NONHEX = "#fg8c00";
		
		Matcher doMatcher = HTML_COLOR_PATTERN.matcher(DARK_ORANGE);
		assertTrue(doMatcher.matches());
		assertEquals("FF8C00", doMatcher.group(1));
		
		Matcher mtMatcher = HTML_COLOR_PATTERN.matcher(MEDIUM_TURQUOISE);
		assertTrue(mtMatcher.matches());
		assertEquals(MEDIUM_TURQUOISE, mtMatcher.group(1));
		
		assertFalse(HTML_COLOR_PATTERN.matcher(TOO_SHORT_COLOR).matches());
		assertFalse(HTML_COLOR_PATTERN.matcher(CSS_RED).matches());
		assertFalse(HTML_COLOR_PATTERN.matcher(NONSENSE_CHAR).matches());
		assertFalse(HTML_COLOR_PATTERN.matcher(NONHEX).matches());
	}
}
