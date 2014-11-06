package com.blitline.image.spring;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.beans.factory.annotation.Qualifier;

/**
 * This annotation is a qualifier indicating components that are specially configured for the Blitline API.
 *
 * @author Christopher Smith
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface BlitlineApi {

}
