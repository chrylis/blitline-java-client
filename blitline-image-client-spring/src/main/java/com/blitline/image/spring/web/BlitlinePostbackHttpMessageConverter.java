package com.blitline.image.spring.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import com.blitline.image.BlitlinePostback;
import com.blitline.image.spring.BlitlineObjectMapperHolder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class BlitlinePostbackHttpMessageConverter extends AbstractHttpMessageConverter<BlitlinePostback> {

	private final ObjectMapper blitlineObjectMapper;

	@Autowired
	public BlitlinePostbackHttpMessageConverter(final BlitlineObjectMapperHolder holder) {
		super(MediaType.APPLICATION_JSON);
		this.blitlineObjectMapper = holder.getMapper();
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		return BlitlinePostback.class.equals(clazz);
	}

	@Override
	protected BlitlinePostback readInternal(Class<? extends BlitlinePostback> clazz, HttpInputMessage inputMessage)
		throws IOException, HttpMessageNotReadableException {
		try {
			return blitlineObjectMapper.readValue(inputMessage.getBody(), clazz);
		} catch (JsonProcessingException e) {
			throw new HttpMessageNotReadableException("error reading JSON", e);
		}
	}

	@Override
	protected void writeInternal(BlitlinePostback t, HttpOutputMessage outputMessage) throws IOException,
		HttpMessageNotWritableException {
		try {
			blitlineObjectMapper.writeValue(outputMessage.getBody(), t);
		} catch (JsonProcessingException e) {
			throw new HttpMessageNotWritableException("error writing JSON", e);
		}
	}
}
