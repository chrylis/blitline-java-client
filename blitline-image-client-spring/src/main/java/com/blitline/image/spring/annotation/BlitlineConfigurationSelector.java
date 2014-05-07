package com.blitline.image.spring.annotation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import com.blitline.image.spring.BlitlineConfiguration;
import com.blitline.image.spring.web.BlitlinePostbackController;

public class BlitlineConfigurationSelector implements ImportSelector {

	public static final String KEY_USE_BUILTIN_POSTBACK = "builtinPostback";

	@Override
	public final String[] selectImports(AnnotationMetadata importingClassMetadata) {
		AnnotationAttributes attributes = AnnotationAttributes.fromMap(
			importingClassMetadata.getAnnotationAttributes(EnableBlitlineImageService.class.getName(),
				true));

		Boolean usePostback = (Boolean) attributes.get(KEY_USE_BUILTIN_POSTBACK);

		List<String> classes = new ArrayList<String>(2);
		classes.add(BlitlineConfiguration.class.getName());

		if(usePostback)
			classes.add(BlitlinePostbackController.class.getName());

		return classes.toArray(new String[0]);
	}
}
