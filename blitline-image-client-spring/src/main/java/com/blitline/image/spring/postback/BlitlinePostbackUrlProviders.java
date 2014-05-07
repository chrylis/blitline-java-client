package com.blitline.image.spring.postback;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.ConfigurationCondition;
import org.springframework.core.type.AnnotatedTypeMetadata;

import com.blitline.image.spring.web.BlitlinePostbackController;

/**
 * Utility class with common strategies for providing Blitline postback URLs.
 *
 * @author Christopher Smith
 *
 */
public class BlitlinePostbackUrlProviders {

	public static final String POSTBACK_URL_PROPERTY = "blitline.postbackUrl";

	/**
	 * Spring configuration condition that is true when a property is set specifying a postback URL.
	 *
	 * @author Christopher Smith
	 *
	 */
	public static class PropertyCondition implements ConfigurationCondition {
		@Override
		public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
			return context.getEnvironment().containsProperty(POSTBACK_URL_PROPERTY);
		}

		@Override
		public ConfigurationPhase getConfigurationPhase() {
			return ConfigurationPhase.REGISTER_BEAN;
		}
	}

	/**
	 * Simple provider that always points the postback at a specific URL.
	 *
	 * @param url
	 *            the URL for the postback
	 * @return a provider that always returns the specified URL
	 */
	public static BlitlinePostbackUrlProvider fixedUrl(final String url) {
		return new FixedUrl(url);
	}

	public static class FixedUrl implements BlitlinePostbackUrlProvider {
		private final String url;

		public FixedUrl(final String url) {
			this.url = url;
		}

		@Override
		public String getPostbackUrl() {
			return url;
		}

		@Override
		public String toString() {
			return ("FixedUrl[" + url + "]");
		}
	}

	/**
	 * A provider that attempts to infer the location of the {@link BlitlinePostbackController} by examining the HTTP request
	 * context for a base URL mapping. Once it succcessfully resolves the controller, it returns that single URL for all subsequent
	 * queries, permitting non-HTTP contexts to invoke image jobs. This provider is essentially suitable only for testing purposes
	 * where the developer is using an external tunnel or dynamic IP address.
	 *
	 * @return a provider that resolves and remembers the URL based on the HTTP request
	 */
	public static BlitlinePostbackUrlProvider bindOnPickup() {
		return new BindOnPickupUrl();
	}

	public static class BindOnPickupUrl implements BlitlinePostbackUrlProvider {

		private final Log log = LogFactory.getLog(BlitlinePostbackUrlProviders.class);

		private String url;

		@Override
		public String getPostbackUrl() {
			if (url == null)
				try {
					url = linkTo(methodOn(BlitlinePostbackController.class).handlePostback(null, null)).toString();
					log.info("bound to postback URL " + url);
				} catch (IllegalStateException e) {
					log.warn("tried to find postback URL outside of an HTTP request context; will retry");
				} catch (Exception e) {
					log.error("shouldn't happen; just here because the controller throws Exception");
				}

			return url;
		}

		@Override
		public String toString() {
			return ("BindOnPickupUrl[" + (url == null ? "unset" : url) + "]");
		}
	}

	/**
	 * A provider that infers the location of the {@link BlitlinePostbackController} by examining the HTTP request but does not
	 * remember the URL.
	 *
	 * @return a per-request resolving provider
	 */
	public static BlitlinePostbackUrlProvider perRequest() {
		return new PerRequestUrl();
	}

	public static class PerRequestUrl implements BlitlinePostbackUrlProvider {
		private final Log log = LogFactory.getLog(BlitlinePostbackUrlProviders.class);

		@Override
		public String getPostbackUrl() {
			String url = null;
			try {
				url = linkTo(methodOn(BlitlinePostbackController.class).handlePostback(null, null)).toString();
				log.info("using postback URL " + url + " for this request");
			} catch (Exception e) {
				log.error("couldn't find postback URL", e);
			}

			return url;
		}
	}
}
