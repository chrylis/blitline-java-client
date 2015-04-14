package com.blitline.image.spring.web;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.blitline.image.BlitlinePostback;
import com.blitline.image.spring.BlitlineApi;
import com.blitline.image.spring.BlitlinePostbackHeader;
import com.blitline.image.spring.postback.BlitlinePostbackHandler;

/**
 * This is a general-purpose delegating Spring MVC controller that listens for Blitline postbacks and hands them off to a handler
 * supplied by the client application.
 *
 * This controller requires a {@link BlitlinePostbackHandler} to be registered in the application context to hand incoming requests
 * to, and the client application can optionally register a {@code Set<String>} qualified with the {@link BlitlineApi} annotation
 * to ask for HTTP headers to be passed through with the request.
 *
 * @author Christopher Smith
 *
 */
@RestController
@RequestMapping(BlitlinePostbackController.BASE_ENDPOINT)
public class BlitlinePostbackController {

    public static final String BASE_ENDPOINT = "/webhook/blitline";
    public static final String IMAGE_PATH = "/image";

    private final Log log = LogFactory.getLog(BlitlinePostbackController.class);

    private BlitlinePostbackHandler handler;

    @Autowired
    public void setHandler(BlitlinePostbackHandler handler) {
        this.handler = handler;
    }

    public BlitlinePostbackHandler getHandler() {
        return handler;
    }

    private Collection<String> interestingHeaders = Collections.emptySet();

    @Autowired(required = false)
    @BlitlinePostbackHeader
    public void setInterestingHeaders(Collection<String> interestingHeaders) {
        this.interestingHeaders = Collections.unmodifiableSet(new HashSet<String>(interestingHeaders));
        log.debug("the BlitlinePostbackController will pass through these HTTP headers: " + interestingHeaders);
    }

    public Collection<String> getInterestingHeaders() {
        return interestingHeaders;
    }

    @RequestMapping(IMAGE_PATH)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public HttpHeaders handlePostback(HttpEntity<BlitlinePostback> postbackEntity) throws Exception {
        BlitlinePostback postback = postbackEntity.getBody();
        log.info("handling Blitline postback for job " + postback.getJobId());

        HttpHeaders requestHeaders = postbackEntity.getHeaders();
        Map<String, List<String>> headers = new HashMap<>();
        for (String header : interestingHeaders) {
            if (requestHeaders.containsKey(header)) {
                headers.put(header, requestHeaders.get(header));
            }
        }

        handler.handlePostback(postback, headers);
        return new HttpHeaders();
    }

    @PostConstruct
    public void log() {
        log.info("using built-in Blitline image postback endpoint");
    }
}
