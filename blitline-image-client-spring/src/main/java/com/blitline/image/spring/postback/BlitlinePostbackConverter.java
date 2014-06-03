package com.blitline.image.spring.postback;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.blitline.image.BlitlinePostback;
import com.blitline.image.spring.BlitlineObjectMapperHolder;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class BlitlinePostbackConverter implements Converter<String, BlitlinePostback>{

	private final ObjectMapper blitlineObjectMapper;

	@Autowired
	public BlitlinePostbackConverter(final BlitlineObjectMapperHolder holder) {
		blitlineObjectMapper = holder.getMapper();
	}

	@Override
	public BlitlinePostback convert(String source) {
		try {
			return blitlineObjectMapper.readValue(source, BlitlinePostback.class);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
