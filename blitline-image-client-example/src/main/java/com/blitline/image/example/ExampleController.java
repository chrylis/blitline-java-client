package com.blitline.image.example;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blitline.image.Blitline;
import com.blitline.image.BlitlineImageJob;
import com.blitline.image.BlitlinePostResults;
import com.blitline.image.spring.BlitlineImageService;
import com.blitline.image.spring.postback.BlitlinePostbackUrlProvider;
import com.blitline.image.spring.postback.BlitlinePostbackUrlProviders;

/**
 * This is a Web-driven Blitline client that will run a pre-scripted transform job on one of the stock Blitline images.
 *
 * @author Christopher Smith
 *
 */
@RestController
public class ExampleController implements ApplicationContextAware {

	@Autowired
	private BlitlineImageService bis;

	public static final String BLITLINE_SAMPLE_IMAGE_URL = "http://cdn.blitline.com/filters/boys.jpeg";

	@Autowired(required = false)
	private BlitlinePostbackUrlProvider postbackUrlProvider;

	@PostConstruct
	public void checkUrlProvider() {
		if(postbackUrlProvider == null)
			postbackUrlProvider = BlitlinePostbackUrlProviders.perRequest();
	}

	/**
	 * Applies a canned function chain and saves the result with the {@code imageIdentifer} provided on the path.
	 *
	 * @param imageIdentifer
	 *            the identifier to use for the Blitline job
	 * @param sourceImage
	 *            the URL of the source image; defaults to the stock image of boys at the harbor
	 * @param postbackUrl
	 *            the URL for postback on job completion; defaults to none (polling)
	 * @return the results returned from dispatching the job
	 */
	@RequestMapping("/blitline/{imageIdentifier}")
	public BlitlinePostResults runJob(
		@PathVariable String imageIdentifier,
		@RequestParam(defaultValue = BLITLINE_SAMPLE_IMAGE_URL) String sourceImage,
		@RequestParam(required = false) String postbackUrl
		) {
		if("auto".equals(postbackUrl))
			postbackUrl = postbackUrlProvider.getPostbackUrl();

		BlitlineImageJob job = bis.jobBuilder().fromUrl(sourceImage).withPostback(postbackUrl).apply(
			Blitline.cropToSquare().thenApply(
				Blitline.sepiaFilter().thenApply(
					Blitline.rotateBy(90.).andSaveResult(imageIdentifier)
					)
				)
			);

		return bis.submitJob(job).getBody();
	}

	@RequestMapping("/test/{id}")
	public String test(@PathVariable String id) {
		return id;
	}

	@RequestMapping("/shutdown")
	public String shutdown() {
		new Thread(SHUTDOWN_TASK).start();
		return "Shutting down example.";
	}

	/* handler to close out the Spring context for exit */

	private ConfigurableApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		if (context instanceof ConfigurableApplicationContext)
			this.context = (ConfigurableApplicationContext) context;
	}

	private final Runnable SHUTDOWN_TASK = new Runnable() {
		@Override
		public void run() {
			try {
				Thread.sleep(500L);
			} catch (InterruptedException e) {
				// ignore
			}

			ExampleController.this.context.close();
		}
	};
}
