package com.blitline.image.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.blitline.image.spring.BlitlineConfiguration;

@Configuration
@ComponentScan
@Import(BlitlineConfiguration.class)
@EnableAutoConfiguration
public class ExampleLauncher {

	public static void main(String[] args) {
		SpringApplication.run(ExampleLauncher.class, args);
	}	
}
