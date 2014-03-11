package com.blitline.image.spring;

import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BlitlineImageServiceTest.TestConfig.class)
public class BlitlineOnlineTest {

	@Configuration
	@Import(BlitlineConfiguration.class)
	public static class TestConfig {
	}
}
