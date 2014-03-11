package com.blitline.image.spring;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.blitline.image.BlitlineImageJob;
import com.blitline.image.S3Location;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BlitlineImageServiceTest.TestConfig.class)
public class BlitlineImageServiceTest {

	@Autowired
	private BlitlineImageService service;

	@Configuration
	@ComponentScan
	@PropertySource("classpath:/blitline.properties")
	public static class TestConfig {
		@Bean
		public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
			return new PropertySourcesPlaceholderConfigurer();
		}
	}

	@Test
	public void testAppIdAndPostback() {
		assertEquals("testBlitlineAppId", service.getApplicationId());
		BlitlineImageJob job = service.loadUrl("").apply();
		assertEquals("testBlitlineAppId", job.getApplicationId());
		assertEquals("http://example.com/postback", job.getPostbackUrl());
		assertEquals("", job.getSrc());
	}

	@Test
	public void testS3() {
		final String TEST_KEY = "my-test-key.jpg";
		S3Location expected = S3Location.of("my-source-bucket", TEST_KEY);
		
		BlitlineImageJob job = service.loadS3Key(TEST_KEY).apply();
		assertEquals(expected, job.getSrc());
	}
}
