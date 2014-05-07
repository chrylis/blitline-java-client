package com.blitline.image.spring.postback;

import javax.servlet.http.HttpServletRequest;

import org.springframework.integration.annotation.Header;
import org.springframework.integration.annotation.Payload;

import com.blitline.image.BlitlinePostback;

/**
 * Interface for a bean that should do useful tricks when a postback is received.
 *
 * @author Christopher Smith
 *
 */
public interface BlitlinePostbackHandler {
	Object handlePostback(@Payload BlitlinePostback postback, @Header("httpServletRequest") HttpServletRequest req) throws Exception;
}
