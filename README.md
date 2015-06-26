#Blitline Java client

This is a parent project for Java clients for the
[Blitline](https://www.blitline.com) processing services. The project
currently has support for the Blitline image API.

##Updates

###0.17.0

EXIF date-and-time information is presented in a format that is usually
`yyyy:MM:dd HH:mm:ss` but can vary, including ISO8601-style formats. This library
formerly tried to parse the conventional date format out of postback information,
but this was unreliable.

Blitline now uses the Ruby Chronic library to guess the date format server-side
and returns a reliable ISO8601-formatted date string. This is now available as
`OriginalMetadata#getIsoDateCreated()`. The older `getDateCreated()` is
deprecated and forwards to the new method.

###0.16.0

This release **moves the default Spring endpoint** to `/webhook/blitline/image`
to make it easier to manage security policy for a collection of such endpoints.

###0.15.0

This release adds support for setting HTTP headers for S3 objects, including
a built-in method for setting the Cache-Control header to "cache forever".

The Spring component now requires Spring 4.1.3 or higher, as it registers
an HTTP message converter for incoming postbacks using a new hook on
`WebMvcConfigurerAdapter`.