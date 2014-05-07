package com.blitline.image.spring.postback;

/**
 * Provides a means to determine the correct postback URL for a Blitline job. Usually set application-wide.
 *
 * @author Christopher Smith
 *
 */
public interface BlitlinePostbackUrlProvider {
	String getPostbackUrl();
}
