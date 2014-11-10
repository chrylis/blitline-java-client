package com.blitline.image;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.blitline.image.functions.AbstractFunction;
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

        AbstractFunction noOp = (AbstractFunction) functions.get(0);
        assertSame(NoOp.class, noOp.getClass());
        assertNull(noOp.getSave().setExif);
    }

    @Test
    public void withSetExif() {
        BlitlineImageJob job = BlitlineImageJob.Builder
            .
            forApplication("appId")
            .
            fromUrl("http://cdn.blitline.com/filters/boys.jpeg")
            .
            apply(
                Blitline.noOp()
                    .andSaveResultTo(SavedImage.withId("imageId").withExifHeader("exifHeader", 42).toBlitlineContainer()));

        List<Function> functions = job.getFunctions();
        assertEquals(1, functions.size());

        AbstractFunction noOp = (AbstractFunction) functions.get(0);
        assertEquals(42, noOp.getSave().setExif.get("exifHeader"));
    }

    @Test
    public void withPostbackHeader() {
        BlitlineImageJob job = BlitlineImageJob.Builder.
            forApplication("appId").
            fromUrl("http://cdn.blitline.com/filters/boys.jpeg").
            withPostbackHeader("My-Test-Header", "mytestvalue").
            apply(Blitline.noOp().andSaveResult("imageId"));

        assertEquals("mytestvalue", job.getPostbackHeader("My-Test-Header"));
        assertNull(job.getPostbackHeader("Foo-Header"));
    }

    @Test
    public void withoutPostbackHeaders() {
        // ensure that we get a null rather than throwing an NPE checking the map
        BlitlineImageJob job = BlitlineImageJob.Builder.
            forApplication("appId").
            fromUrl("http://cdn.blitline.com/filters/boys.jpeg").
            apply(Blitline.noOp().andSaveResult("imageId"));

        assertNull(job.getPostbackHeader("My-Test-Header"));
    }
}
