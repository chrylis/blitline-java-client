package com.blitline.image.spring.postback;

import java.util.List;
import java.util.Map;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import com.blitline.image.BlitlinePostback;
import com.blitline.image.spring.web.BlitlinePostbackController;

/**
 * Interface for a bean that should do useful tricks when a postback is received.
 *
 * @author Christopher Smith
 *
 */
public interface BlitlinePostbackHandler {

	/**
	 * Process a postback received by the provided {@link BlitlinePostbackController}. This will normally do such things as logging,
	 * updating database records, and so on.
	 *
	 * <p>
	 * This method is annotated with Spring's {@link Async} annotation so that implementations may return a 202&nbsp;Accepted
	 * response immediately instead of keeping the Blitline billing clock ticking while performing updates. This annotation does not
	 * by itself enable asynchronous processing, which must be configured in the Spring context through XML configuration or the
	 * {@link EnableAsync} configuration annotation.
	 *
	 * @param postback the parsed postback information from the job
	 * @param postbackHeaders headers described as "interesting" to the {@code BlitlinePostbackController}; if none are declared or match, an empty {@code Map}
	 */
	@Async
	void handlePostback(BlitlinePostback postback, Map<String, List<String>> postbackHeaders) throws Exception;
}
