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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.blitline.image.BlitlinePostback;
import com.blitline.image.spring.BlitlineObjectMapperHolder;
import com.blitline.image.spring.postback.BlitlinePostbackHandler;

public class BlitlinePostbackControllerTest extends AbstractBlitlinePostbackControllerTest {

    @Mock
    private BlitlinePostbackHandler postbackHandler;

    @Captor
    private ArgumentCaptor<BlitlinePostback> postbackCaptor;

    @Captor
    private ArgumentCaptor<Map<String, List<String>>> headersCaptor;

    @InjectMocks
    private BlitlinePostbackController controller;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).setMessageConverters(new BlitlinePostbackHttpMessageConverter(new BlitlineObjectMapperHolder())).build();
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
