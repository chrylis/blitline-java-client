Blitline example client
=======================

This module contains a small but fully-working Web application demonstrating
live use of the Blitline image service using Spring Boot, Spring MVC, and the
`RestTemplate`.

###Launching

To run the example, run `ExampleLauncher` from your IDE as a standalone Java
application, or run from the command line using Maven:

````sh
blitline-java-client$ mvn install  # this example depends on the other modules
blitline-java-client$ mvn exec:java -pl blitline-image-client-example \
                      -Dexec.mainClass=com.blitline.image.example.ExampleLauncher
````

The example by default uses the test application ID `test_job`, which is
specified in `blitline.properties`. This will return a successful result if
Blitline parses the job correctly but will not actually execute it. To run
the job for real, add your own application ID as a property to the launch:

````sh
blitline-java-client$ mvn exec:java -pl blitline-image-client-example \
                      -Dexec.mainClass=com.blitline.image.example.ExampleLauncher \
                      -Dblitline.applicationId=MY_APP_ID_HERE
````

###Usage

The example uses a canned image script that loads a source image, crops it to
a square, rotates 90 degrees clockwise, and applies a sepia filter. The
result is saved to the image identifier provided in the path. The optional
`sourceImage` parameter will load the specified image instead of the default
harbor photograph used in Blitline examples. The URI for the example is

````
http://localhost:8080/blitline/{imageIdentifier}[?sourceImage=ENCODED_URL]
````

The endpoint returns the job confirmation received from Blitline, which has
been turned into a Java object by Jackson and then re-serialized by Spring MVC.

###Shutdown

To close the example, either send a Ctrl+C, which will cause Spring Boot to
shut down, or load

````
http://localhost:8080/shutdown
````