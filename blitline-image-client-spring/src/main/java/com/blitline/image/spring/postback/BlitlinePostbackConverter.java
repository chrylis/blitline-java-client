package com.blitline.image.spring.postback;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.blitline.image.BlitlinePostback;
import com.blitline.image.spring.BlitlineObjectMapperHolder;

@Component
public class BlitlinePostbackConverter implements Converter<String, BlitlinePostback>{

	@Autowired
	private BlitlineObjectMapperHolder holder;

	@Override
	public BlitlinePostback convert(String source) {
		try {
			return holder.getMapper().readValue(source, BlitlinePostback.class);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
