package com.blitline.image.spring.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

public abstract class AbstractBlitlinePostbackControllerTest {

    protected MockMvc mockMvc;

    public static final String POSTBACK_TO = BlitlinePostbackController.BASE_ENDPOINT + BlitlinePostbackController.IMAGE_PATH;

    public static final String SUCCESS_POSTBACK_CONTENT = "{\"results\":"
        + "{\"original_meta\":{\"width\":3740,\"height\":5573,\"date_created\":\"2011:06:07 22:09:30\"},"
        + "\"images\":[{\"image_identifier\":\"6odPpPC9Ayy8Z2ETs9i1Bk-ts\",\"s3_url\":\"http://s3.amazonaws.com/some-bucket/6odPpPC9Ayy8Z2ETs9i1Bk-ts.webp\",\"meta\":{\"width\":43,\"height\":64}}],"
        + "\"job_id\":\"3WkW98qII4EMriH4jV3eAkQ\"}}";


    public AbstractBlitlinePostbackControllerTest() {
        // TODO Auto-generated constructor stub
    }

    protected MockHttpServletRequestBuilder postPostback(String content) throws Exception {
        return post(POSTBACK_TO).contentType(MediaType.APPLICATION_JSON).content(content);
    }
}
