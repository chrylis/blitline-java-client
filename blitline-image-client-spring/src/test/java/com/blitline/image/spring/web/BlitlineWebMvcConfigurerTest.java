package com.blitline.image.spring.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.blitline.image.BlitlinePostback;
import com.blitline.image.spring.annotation.EnableBlitlineImageService;
import com.blitline.image.spring.postback.BlitlinePostbackHandler;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=BlitlineWebMvcConfigurerTest.Config.class)
@WebAppConfiguration
public class BlitlineWebMvcConfigurerTest extends AbstractBlitlinePostbackControllerTest {

    @Configuration
    @ComponentScan
    @EnableBlitlineImageService
    @EnableWebMvc
    public static class Config extends WebMvcConfigurerAdapter {
        @Bean
        public BlitlinePostbackHandler handler() {
            return new BlitlinePostbackHandler() {
                @Override
                public void handlePostback(BlitlinePostback postback, Map<String, List<String>> postbackHeaders) throws Exception {
                    System.out.println(postback);
                }
            };
        }
    }

    public static final String FAILURE_POSTBACK = "{\"results\":{\"original_meta\":null,\"images\":[],\"job_id\":\"6jWeWco5eDm0ZgfzgQ0dKCA\",\"errors\":[\"Image processing failed. Failed to load image at http://cdn.blitline.com/filters/boys.jpg, are you sure it is accessible at that url? (Exception Message=:Failed to download file from http://cdn.blitline.com/filters/boys.jpg. Server issued 5xx or 4xx response, so we bailed. If you are reading from S3 it is probably a permission problem.\"],\"failed_image_identifiers\":[\"FAILED_IMAGE_IDENTIFIER\"]}}";

    @Autowired
    WebApplicationContext wac;

    @Autowired
    OrdinaryController con;

    @Autowired
    RequestMappingHandlerAdapter rhma;

    @Test
    public void load() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        mockMvc.perform(postPostback(SUCCESS_POSTBACK_CONTENT)).andDo(print());
        mockMvc.perform(post("/times2").contentType(MediaType.APPLICATION_JSON).content("{\"foo\":4}")).andDo(print());
        Assert.assertTrue(true);
    }
}
