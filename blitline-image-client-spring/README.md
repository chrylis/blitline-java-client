Blitline Spring support
=======================

This module contains an out-of-the-box-ready suite of Spring components that
can handle all the communications with Blitline, including receiving postbacks,
with no boilerplate and minimal configuration.

For a running example of how to build a Blitline client with this library, see
the `blitline-image-client-example` module in this project.

###Components

The most basic component of this library is the `BlitlineImageService`, which
is a Spring `@Service` that wraps application-wide configuration and a
`RestTemplate` instance. It will try to load the application ID, S3 source
bucket name, and postback URL from the Spring environment, and these can also
be set directly on the component as JavaBean properties.

It is intended that the `BlitlineImageService` will be injected into the
application components that need to invoke Blitline jobs, and no configuration
in those components, other than an optional S3 destination bucket, needs to be
specified.

This library also includes a skeleton framework for handling postbacks either
programmatically via a Spring MVC controller or declaratively with Spring
Integration. It uses Jackson to completely deserialize Blitline postbacks into
comprehensive Java objects and makes those objects available by all of the
standard interfaces.

###Usage

The Blitline Spring support is enabled by a Spring-Boot style annotation.
Simply annotate one of the `@Configuration` classes in your project with
`@EnableBlitlineImageService`, and the library will autoconfigure these
components:

- A `PropertySource` pointed at `classpath:/blitline.properties`, which is
optional. You may place a properties file in the classpath root, and it will
be picked up with no further configuration, or you may specify your own
`PropertySource`s elsewhere in your configuration.
- If a postback URL is defined in the Spring environment, a
`BlitlinePostbackUrlProvider` that returns that URL. This provider will be
used automatically by the `BlitlineImageService`.
- A `BlitlineObjectMapperHolder` instance, which contains a Jackson
`ObjectMapper` configured with the specific options needed for Blitline
communication. The mapper is wrapped in a separate class to prevent it from
interfering with more typical mappers installed elsewhere in the context.
- A `RestTemplate`, marked with the `@BlitlineApi` qualifier annotation, using
the Blitline Jackson mapper.
- A `BlitlineImageService` instance, which will pick up all of the properties
it can find in the Spring environment (see below).
- A Spring type converter and HTTP message converter for `BlitlinePostback`
arguments.
- If the `builtinPostback` property of the `@EnableBlitlineImageService`
annotation is **not** set to `false`, a skeleton Spring MVC controller mapped
to `/webhook/blitline/image`. This controller will accept the postbacks sent from
Blitline and pass them on to a handler, which you must define.

####Properties

The library will look for these properties in the Spring environment, which
includes properties files specified in XML or Java configuration as well as
command-line properties and system environment variables.

| property | required? | effect |
|----------|-----------|--------|
| blitline.applicationId | yes | injected into the `BlitlineImageService` and used to identify the owner of the jobs being submitted |
| blitline.s3sourceBucket | no | injected into the `BlitlineImageService` and allows you to specify source images by S3 key only; application code does not need to be aware of the source bucket |
| blitline.postbackUrl | no | used to manually specify the endpoint where Blitline should send job notification postbacks; should be explicitly set for production |

####Customization

If you want to use polling or ignore the results of your jobs (not
recommended), you can simply add the `applicationId` to your properties,
inject the `BlitlineImageService` into your application code, and invoke jobs.
However, there are three major areas where you are likely to want to customize
the configuration:

#####Provide a postback URL provider

If you know what the postback URL is, provide it as a property; if the property
is present, the configuration will automatically register it as a fixed
postback URL.

If you don't know ahead of time what the postback URL will be (such as in a
development environment using a dynamic Localtunnel URL), you can use the
*bind-on-pickup* URL provider. This uses a rule similar to World of Warcraft's
where the first HTTP request involved in an image job is used to determine the
application's base URL and the full URL of the built-in postback endpoint is
resolved and then remembered for the rest of the run. This should not be used
for production code, as there's a laundry list of ways this could get confused,
but it is useful for development.

````java
@Bean
public BlitlinePostbackUrlProvider bindOnPickup() {
    return BlitlinePostbackUrlProviders.bindOnPickup();
}
````

#####Provide a postback handler

If you use the built-in endpoint (enabled by default), you need to provide the
logic for what the system should do when a postback arrives. Register a bean
implementing the `BlitlinePostbackHandler` interface, and it will be invoked
whenever postbacks show up. See the example for a minimal implementation.

You may be interested in custom HTTP headers that you specify to Blitline,
such as a job-correlation ID. Simply register Spring beans of type `String`
annotated with `@BlitlinePostbackHeader`, and any headers matching those
names will be passed to the handler.

Spring Integration's service-activator support can handle Blitline postbacks
easily. Use the following declaration:

````xml
<int:gateway
	service-interface="com.blitline.image.spring.postback.BlitlinePostbackHandler"
    default-request-channel="your-postback-channel" />
````

#####Use your own endpoint

If you want to heavily customize the postback handling, or if you would like
to wire an incoming HTTP gateway directly into Spring Integration, you can
disable the built-in postback controller:

````java
@EnableBlitlineImageService(builtinPostback = false)
````

In this case, if you want to use postbacks, you **must** specify the URL
explicitly, as the system has no way of finding your custom endpoint.

####Security

Since postbacks will be coming in asynchronously, CSRF protection will need
to be disabled for the postback endpoint. The built-in controller is mapped
under `/webhook`, and it is suggested that `/webhook/**` be exempted from the
CSRF policy and used to aggregate all such handlers.