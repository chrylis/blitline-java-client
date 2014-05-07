package com.blitline.image.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(BlitlineConfigurationSelector.class)
public @interface EnableBlitlineImageService {
	boolean builtinPostback() default true;
}
