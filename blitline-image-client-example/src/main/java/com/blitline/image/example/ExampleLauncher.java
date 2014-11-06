package com.blitline.image.example;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.blitline.image.BlitlinePostback;
import com.blitline.image.spring.BlitlinePostbackHeader;
import com.blitline.image.spring.annotation.EnableBlitlineImageService;
import com.blitline.image.spring.postback.BlitlinePostbackHandler;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableBlitlineImageService
public class ExampleLauncher {

    private final Log log = LogFactory.getLog(ExampleLauncher.class);

    public static void main(String[] args) {
        SpringApplication.run(ExampleLauncher.class, args);
    }

    @Bean
    public BlitlinePostbackHandler blitlinePostbackHandler() {
        return new BlitlinePostbackHandler() {
            @Override
            public void handlePostback(BlitlinePostback postback, Map<String, List<String>> headers) {
                log.info("got a postback with jobId " + postback + " and these interesting headers: " + headers);
            }
        };
    }

    @Bean
    @BlitlinePostbackHeader
    public String interestingHttpHeader() {
        return "Interesting-Header";
    }

    @Bean
    @BlitlinePostbackHeader
    public String contentType() {
        return "Content-Type";
    }
}
