package com.blitline.image;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.blitline.image.functions.AbstractFunction;

public class SavedImageTest {

	@Test
	public void testSkipSave() {
		AbstractFunction chain = (AbstractFunction) Blitline.noOp().andSkipSave("just_getting_metadata");

		assertNull(chain.getSave().s3Destination);
		assertNull(chain.getSave().azureDestination);
		assertTrue(chain.getSave().skip);
	}

}
