#Blitline Java client

This is a parent project for Java clients for the
[Blitline](https://www.blitline.com) processing services. The project
currently has support for the Blitline image API.

##Updates

###0.16.0

This release **moves the default Spring endpoint** to `/webhook/blitline/image`
to make it easier to manage security policy for a collection of such endpoints.

###0.15.0

This release adds support for setting HTTP headers for S3 objects, including
a built-in method for setting the Cache-Control header to "cache forever".

The Spring component now requires Spring 4.1.3 or higher, as it registers
an HTTP message converter for incoming postbacks using a new hook on
`WebMvcConfigurerAdapter`.