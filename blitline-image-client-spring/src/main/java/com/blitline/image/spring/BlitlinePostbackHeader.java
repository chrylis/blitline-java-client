package com.blitline.image.spring;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.beans.factory.annotation.Qualifier;

/**
 * This annotation is a qualifier indicating that the annotated {@code String} is the name of an HTTP header whose value should be
 * passed on from the built-in controller to the postback handler.
 *
 * @author Christopher Smith
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface BlitlinePostbackHeader {
}
