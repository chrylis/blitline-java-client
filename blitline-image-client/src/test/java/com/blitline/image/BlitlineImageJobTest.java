package com.blitline.image;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.blitline.image.functions.NoOp;

public class BlitlineImageJobTest {

	@Test
	public void testIdentifyOnlyJob() {
		BlitlineImageJob idJob = BlitlineImageJob.Builder
			.forApplication("appId")
			.fromUrl("http://cdn.blitline.com/filters/boys.jpeg")
			.identifyMetadataOnly();

		assertTrue(idJob.getExtendedMetadata());

		List<Function> functions = idJob.getFunctions();
		assertEquals(1, functions.size());

		Function noOp = functions.get(0);
		assertSame(NoOp.class, noOp.getClass());
	}
}
