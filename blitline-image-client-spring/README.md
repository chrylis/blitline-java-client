Blitline image-processing Spring support
========================================

This module contains optional Spring helpers for use with the Blitline JSON
builder. The `BlitlineConfiguration` class is a mixin that can be added as
an `@Import` on any of your own Spring `@Configuration` classes; it will
create Jackson mappers configured to work with the Blitline syntax and a
`RestTemplate` instance backed by those mappers. It will also component-scan
and include the `BlitlineImageService`, which will grab the `RestTemplate`
and configure boilerplate fields from properties.

property name | required? | meaning
------------- | --------- | -------
`blitline.applicationId` | yes | the application ID (API key) to use when submitting jobs to Blitline
`blitline.postbackUrl` | no | the HTTP(S) URL where Blitline should POST results when your job is finished
`blitline.s3sourceBucket` | no | an "input" bucket from which you can load source images by specifying just the key

This module declares an optional dependency on the `spring-web` artifact that
provides the traditional Spring `RestTemplate`; the Spring for Android
implementation implements an identically-named interface and should work
(with manual configuration of parameters) but has not been tested. Include
the appropriate dependency for `spring-web` or `spring-android` in your
project. This library uses Jackson because granular control of the JSON
formatting is required; pull requests for Gson compatibility are welcome.