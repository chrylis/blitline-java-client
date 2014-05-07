package com.blitline.image.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.blitline.image.spring.postback.BlitlinePostbackUrlProvider;
import com.blitline.image.spring.postback.BlitlinePostbackUrlProviders;
import com.blitline.image.spring.web.BlitlinePostbackController;

/**
 * This class provides Spring beans that configure a working Blitline client
 * setup from properties. Blitline's JSON syntax requires some custom setup
 * for the JSON serialization, and qualified instances of type converters
 * are provided here.
 *
 * @author Christopher Smith
 *
 */
@Configuration
@ComponentScan(excludeFilters =
	@Filter(type = FilterType.ASSIGNABLE_TYPE, value = BlitlinePostbackController.class))
@PropertySource(value = "classpath:/blitline.properties", ignoreResourceNotFound = true)
public class BlitlineConfiguration {

	@Autowired
	private BlitlineObjectMapperHolder objectMapperHolder;

	/**
	 * A {@link RestTemplate} instance configured with a single message converter conforming to the Blitline API syntax.
	 *
	 * @return a Blitline-compatible {@code RestTemplate}
	 */
	@Bean
	@BlitlineApi
	public RestTemplate blitlineRestTemplate() {
		MappingJackson2HttpMessageConverter blitlineConverter = new MappingJackson2HttpMessageConverter();
		blitlineConverter.setObjectMapper(objectMapperHolder.getMapper());

		List<HttpMessageConverter<?>> l = new ArrayList<HttpMessageConverter<?>>(1);
		l.add(blitlineConverter);

		return new RestTemplate(l);
	}



	/**
	 * Adds a postback-URL provider if a URL is available via the property.
	 */
	@Conditional(BlitlinePostbackUrlProviders.PropertyCondition.class)
	@Configuration
	public static class BlitlinePostbackUrlConfiguration {
		@Value("${" + BlitlinePostbackUrlProviders.POSTBACK_URL_PROPERTY + "}")
		private String postbackUrl;

		@Bean
		public BlitlinePostbackUrlProvider blitlinePropertyBasedUrl() {
			return BlitlinePostbackUrlProviders.fixedUrl(postbackUrl);
		}
	}
}
