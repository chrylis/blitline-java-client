Blitline image-processing client
================================

This is a library for building and submitting image-processing jobs to
Blitline. Its main data classes, `Job` and the function classes, form a
write-only builder for the Blitline API. Because of the flexibility of
several of the JSON attributes in the API and the fact that the server
consuming the JSON is not written in Java, this library does not support
turning a job definition into any Java-sensible object.

##The Blitline pipeline

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

##Technical notes

The `Function` class contains static convenience wrappers for the constructors
for all of the Blitline image functions. As intentional technical debt, these
wrappers are currently maintained by hand because working around
[MCOMPILER-157](http://jira.codehaus.org/browse/MCOMPILER-157) is more trouble
than manually updating the directory. **Any updates to the Blitline function
API will need to be reflected here.**