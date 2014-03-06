Blitline Java client
====================

This is a parent project for Java clients for the
[Blitline](https://www.blitline.com) processing services. The project
currently has support for the Blitline image API.

Job.Builder
	.forApplication(apiKey)
	.fromSrc(bucket, itemKey)
	.withPostback(handlerUrl)
	.
	
	applyAll(
		Function.resizeToFit(640,480).saveResult(targetBucket,"vga-color.jpg")
		.grayColorspace().saveResult(targetBucket,"vga-grayscale.jpg"),
		Function.resizeToFit(1920x1080).saveResult(targetBucket,"1080p-color.jpg")
	).build();