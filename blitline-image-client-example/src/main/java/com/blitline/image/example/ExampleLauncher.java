package com.blitline.image.example;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.blitline.image.BlitlinePostback;
import com.blitline.image.spring.annotation.EnableBlitlineImageService;
import com.blitline.image.spring.postback.BlitlinePostbackHandler;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableBlitlineImageService
public class ExampleLauncher {
	public static void main(String[] args) {
		SpringApplication.run(ExampleLauncher.class, args);
	}

	@Bean
	public BlitlinePostbackHandler blitlinePostbackHandler() {
		return new BlitlinePostbackHandler() {
			@Override
			public Object handlePostback(BlitlinePostback postback, HttpServletRequest req) {
				System.out.println(postback);
				return "";
			}
		};
	}
}
