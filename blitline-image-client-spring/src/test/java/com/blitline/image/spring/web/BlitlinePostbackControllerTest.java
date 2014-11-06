package com.blitline.image.spring.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.blitline.image.BlitlinePostback;
import com.blitline.image.spring.BlitlineObjectMapperHolder;
import com.blitline.image.spring.postback.BlitlinePostbackHandler;

public class BlitlinePostbackControllerTest {

    @Mock
    private BlitlinePostbackHandler postbackHandler;

    @Captor
    private ArgumentCaptor<BlitlinePostback> postbackCaptor;

    @Captor
    private ArgumentCaptor<Map<String, List<String>>> headersCaptor;

    @InjectMocks
    private BlitlinePostbackController controller;

    public static final String POSTBACK_TO = BlitlinePostbackController.BASE_ENDPOINT + BlitlinePostbackController.IMAGE_PATH;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).setMessageConverters(new BlitlinePostbackHttpMessageConverter(new BlitlineObjectMapperHolder())).build();
    }

    public static final String SUCCESS_POSTBACK_CONTENT = "{\"results\":"
        + "{\"original_meta\":{\"width\":3740,\"height\":5573,\"date_created\":\"2011:06:07 22:09:30\"},"
        + "\"images\":[{\"image_identifier\":\"6odPpPC9Ayy8Z2ETs9i1Bk-ts\",\"s3_url\":\"http://s3.amazonaws.com/some-bucket/6odPpPC9Ayy8Z2ETs9i1Bk-ts.webp\",\"meta\":{\"width\":43,\"height\":64}}],"
        + "\"job_id\":\"3WkW98qII4EMriH4jV3eAkQ\"}}";

    private MockHttpServletRequestBuilder postPostback(String content) throws Exception {
        return post(POSTBACK_TO).contentType(MediaType.APPLICATION_JSON).content(content);
    }

    @Test
    public void noContentType() throws Exception {
        mockMvc.perform(post(POSTBACK_TO).content("{}")).andExpect(status().isUnsupportedMediaType());
    }

    @Test
    public void postbackWithoutHeaders() throws Exception {
        mockMvc.perform(postPostback(SUCCESS_POSTBACK_CONTENT)).andExpect(status().isAccepted());

        verify(postbackHandler).handlePostback(postbackCaptor.capture(), headersCaptor.capture());

        BlitlinePostback postback = postbackCaptor.getValue();
        assertTrue(postback.isSuccessful());
        assertEquals("3WkW98qII4EMriH4jV3eAkQ", postback.getJobId());
        assertEquals("6odPpPC9Ayy8Z2ETs9i1Bk-ts", postback.getImages().iterator().next().getImageIdentifier());

        assertTrue(headersCaptor.getValue().isEmpty());
    }

    public static final String INTERESTING_HEADER_NAME = "Foo-header", INTERESTING_HEADER_VALUE = "bar";

    @Test
    public void postbackAndIgnoreHeaders() throws Exception {
        mockMvc.perform(postPostback(SUCCESS_POSTBACK_CONTENT).header(INTERESTING_HEADER_NAME, INTERESTING_HEADER_VALUE)).andExpect(status().isAccepted());

        verify(postbackHandler).handlePostback(postbackCaptor.capture(), headersCaptor.capture());

        assertTrue(headersCaptor.getValue().isEmpty());
    }

    @Test
    public void postbackWithInterestingHeader() throws Exception {
        controller.setInterestingHeaders(Collections.singleton(INTERESTING_HEADER_NAME));
        mockMvc.perform(postPostback(SUCCESS_POSTBACK_CONTENT).
            header(INTERESTING_HEADER_NAME, INTERESTING_HEADER_VALUE).
            header("Other-header", "value")).
            andExpect(status().isAccepted());

        verify(postbackHandler).handlePostback(postbackCaptor.capture(), headersCaptor.capture());

        Map<String, List<String>> headers = headersCaptor.getValue();

        assertEquals(1, headers.size());
        assertEquals(Collections.singletonList(INTERESTING_HEADER_VALUE), headers.get(INTERESTING_HEADER_NAME));
    }
}
