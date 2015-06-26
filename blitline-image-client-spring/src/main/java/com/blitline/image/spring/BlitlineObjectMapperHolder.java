package com.blitline.image.spring;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * This class holds a single Jackson {@link ObjectMapper} that is configured with the root-wrapping and date-formatting requirements
 * for Blitline interaction. The mapper is not exposed directly to the Spring context because doing so would stomp on any other
 * mappers and prevent registration of defaults.
 *
 * @author Christopher Smith
 *
 */
@Component
public class BlitlineObjectMapperHolder {

	private final ObjectMapper mapper;

	public BlitlineObjectMapperHolder() {
		mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
		mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
	}

	/**
	 * Returns a Jackson {@code ObjectMapper} configured for root wrapping and the Blitline date format.
	 */
	public ObjectMapper getMapper() {
		return mapper;
	}
}
