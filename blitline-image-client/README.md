Blitline image-processing client
================================

This is a library for building and submitting image-processing jobs to
Blitline. Its main data classes, `BlitlineImageJob` and the function classes,
form a write-only builder for the Blitline API. Because of the flexibility of
several of the JSON attributes in the API and the fact that the server
consuming the JSON is not written in Java, this library does not support
turning a job definition into any Java-sensible object.

###The Blitline pipeline

A Blitline job starts by retrieving a single source image and applying a
number of transformations (functions) to it in a manner very similar to an
ImageMagick command-line pipeline. The function pipeline is specified as a
tree whose root is the source retrieval operation; Blitline then processes
the output of each function by applying each child function independently
in parallel. For example, simply resizing a source image to several target
sizes would produce a function tree with one level of child nodes containing
several instances of the appropriate resize function (generally
`ResizeToFit`).

The output of any function node may be saved, and the output of each leaf
node *must* be saved (Blitline will refuse to execute operations whose results
are thrown away).

###Technical notes

The `Blitline` class contains static convenience wrappers for the constructors
for all of the Blitline image functions. As intentional technical debt, these
wrappers are currently maintained by hand because working around
[MCOMPILER-157](https://jira.codehaus.org/browse/MCOMPILER-157) is more trouble
than manually updating the directory. **Any updates to [the Blitline function
API](https://www.blitline.com/docs/functions) will need to be reflected here.**

###Example usage

````java
// manual submission

BlitlineImageJob j = BlitlineImageJob.forApplication("myApplicationId")
    .fromUrl("http://cdn.blitline.com/filters/boys.jpeg") // also supports S3
    .withPostback("https://backend.example.com/blitline") // for job status
    .apply( // function pipeline starts here
        Blitline.drawEllipse(150,150,200,100).andSaveResult("ellipse-overlay"),
        Blitline.cropToSquare().gravity(Gravity.SOUTHWEST).andSaveResult("square-color").thenApply(
            Blitline.toGrayscale().andSaveResultTo(
                SavedImage.withId("square-gray").toS3("my-s3-bucket", "square-gray.jpg")
                )
            )
        );
        
String json = mapper.writeValueAsString(j);
````

````java
// using Spring injection for identifiers and RestTemplate for HTTP operations

final String IMAGE_IDENTIFIER = "ellipse-overlay"; 

@Autowired BlitlineImageService bis; // applicationId and optionally postbackUrl read from properties

BlitlineImageJob j = bis.loadUrl("http://cdn.blitline.com/filters/boys.jpeg")
    .apply(Blitline.drawEllipse(150,150,200,100).andSaveResult(IMAGE_IDENTIFIER));

BlitlinePostResults results = bis.submitJob(j).getBody();

if(results.isSuccessful())
    System.out.println("Blitline will save the image to " +
        results.getImageDestinations().get(IMAGE_IDENTIFIER));
else
    System.out.println("There was a problem with the job submission: " + results.getError());
````