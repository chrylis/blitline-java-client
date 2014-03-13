package com.blitline.image.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

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
@ComponentScan
@PropertySource("classpath:/blitline.properties")
public class BlitlineConfiguration {

	/**
	 * Blitline's API wraps both submissions and responses in top-level elements. This {@link ObjectMapper} bean is configured to
	 * perform root wrapping, and pretty-printing is turned on by default.
	 * 
	 * @return a Jackson mapper that will wrap and unwrap root elements
	 */
	@Bean
	@BlitlineApi
	public ObjectMapper blitlineMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
		mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
		return mapper;
	}

	/**
	 * A message converter configured with the root-wrapping mapper from {@link #blitlineMapper()}.
	 * 
	 * @return a Blitline-compatible message converter
	 */
	@Bean
	@BlitlineApi
	public MappingJackson2HttpMessageConverter blitlineMessageConverter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(blitlineMapper());
		return converter;
	}

	/**
	 * A {@link RestTemplate} instance configured with a single message converter conforming to the Blitline API syntax.
	 * 
	 * @return a Blitline-compatible {@code RestTemplate}
	 */
	@Bean
	@BlitlineApi
	public RestTemplate blitlineRestTemplate() {
		List<HttpMessageConverter<?>> l = new ArrayList<HttpMessageConverter<?>>(1);
		l.add(blitlineMessageConverter());
		return new RestTemplate(l);
	}

}
