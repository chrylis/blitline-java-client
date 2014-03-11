package com.blitline.image.functions;

import java.net.URL;
import java.util.Collections;

import org.apache.commons.lang3.Validate;

import com.blitline.image.AzureLocation;
import com.blitline.image.S3Location;
import com.blitline.image.functions.params.Gravity;
import com.fasterxml.jackson.annotation.JsonValue;

public class Composite extends AbstractFunction {
	@Override
	public String getName() {
		return "composite";
	}

	public Composite(String srcUrl) {
		params.put("src", srcUrl);
	}

	public Composite(URL src) {
		params.put("src", src.toString());
	}

	public Composite(S3Location src) {
		params.put("src", Collections.singletonMap("s3_destination", src));
	}

	public Composite(AzureLocation src) {
		params.put("src", Collections.singletonMap("azure_destination", src));
	}

	public Composite scaledToMatchCurrent() {
		params.put("scale_to_match", true);
		return this;
	}

	public Composite asGrayscaleMask() {
		params.put("as_mask", true);
		return this;
	}

	public Composite atXOffset(int xOffset) {
		Validate.validState(!params.containsKey("gravity"), "only one of an x,y offset or gravity can be specified");
		params.put("x", xOffset);
		return this;
	}

	public Composite atYOffset(int yOffset) {
		Validate.validState(!params.containsKey("gravity"), "only one of an x,y offset or gravity can be specified");
		params.put("y", yOffset);
		return this;
	}

	public Composite atOffset(int xOffset, int yOffset) {
		Validate.validState(!params.containsKey("gravity"), "only one of an x,y offset or gravity can be specified");
		params.put("x", xOffset);
		params.put("y", yOffset);
		return this;
	}

	public Composite withGravity(Gravity gravity) {
		Validate.isTrue(!(params.containsKey("x") || params.containsKey("y")),
			"only one of an x,y offset or gravity can be specified");
		params.put("gravity", gravity);
		return this;
	}

	public Composite usingOperation(Operation op) {
		params.put("composite_op", op);
		return this;
	}

	public enum Operation {
		/**
		 * No composite operator has been specified.
		 */
		UNDEFINED("UndefinedCompositeOp"),

		/**
		 * The result of composite image + image, with overflow wrapping around (mod 256).
		 */
		ADD("AddCompositeOp"),

		/**
		 * The result is the same shape as image, with composite image obscuring image where the image shapes overlap. Note that
		 * this differs from OverCompositeOp because the portion of composite image outside of image’s shape does not appear in the
		 * result.
		 */
		ATOP("AtopCompositeOp"),

		/**
		 * The result image shaded by composite image.
		 */
		BUMPMAP("BumpmapCompositeOp"),

		/**
		 * Darkens the destination color to reflect the source color. Painting with white produces no change.
		 */
		COLOR_BURN("ColorBurnCompositeOp"),

		/**
		 * Brightens the destination color to reflect the source color. Painting with black produces no change.
		 */
		COLOR_DODGE("ColorDodgeCompositeOp"),

		/**
		 * Each pixel in the result image is the combination of the brightness of the target image and the saturation and hue of the
		 * composite image. This is the opposite of LuminizeCompositeOp.
		 */
		COLORIZE("ColorizeCompositeOp"),

		/**
		 * Replace the target image with the composite image.
		 */
		COPY("CopyCompositeOp"),

		/**
		 * Copy the black channel from the composite image to the target image.
		 */
		COPY_BLACK("CopyBlackCompositeOp"),

		/**
		 * Copy the blue channel from the composite image to the target image.
		 */
		COPY_BLUE("CopyBlueCompositeOp"),

		/**
		 * Copy the cyan channel from the composite image to the target image.
		 */
		COPY_CYAN("CopyCyanCompositeOp"),

		/**
		 * Copy the green channel from the composite image to the target image.
		 */
		COPY_GREEN("CopyGreenCompositeOp"),

		/**
		 * Copy the magenta channel from the composite image to the target image.
		 */
		COPY_MAGENTA("CopyMagentaCompositeOp"),

		/**
		 * If the composite image’s matte attribute is true, copy the opacity channel from the composite image to the target image.
		 * Otherwise, set the target image pixel’s opacity to the intensity of the corresponding pixel in the composite image.
		 */
		COPY_OPACITY("CopyOpacityCompositeOp"),

		/**
		 * Copy the red channel from the composite image to the target image.
		 */
		COPY_RED("CopyRedCompositeOp"),

		/**
		 * Copy the yellow channel from the composite image to the target image.
		 */
		COPY_YELLOW("CopyYellowCompositeOp"),

		/**
		 * Replace target image pixels with darker pixels from the composite image.
		 */
		DARKEN("DarkenCompositeOp"),

		/**
		 * The result of abs(composite image - image). This is useful for comparing two very similar images.
		 */
		DIFFERENCE("DifferenceCompositeOp"),

		/**
		 * Displace target image pixels as defined by a displacement map. The operator used by the displace method.
		 */
		DISPLACE("DisplaceCompositeOp"),

		/**
		 * The operator used in the dissolve method.
		 */
		DISSOLVE("DissolveCompositeOp"),

		/**
		 * The part of the destination lying inside of the source is composited over the source and replaces the destination.
		 */
		DESTINATION_ATOP("DstAtopCompositeOp"),

		/**
		 * The part of the destination lying inside of the source replaces the destination.
		 */
		DESTINATION_INSIDE("DstInCompositeOp"),

		/**
		 * The part of the destination lying outside of the source replaces the destination.
		 */
		DESTINATION_OUTSIDE("DstOutCompositeOp"),

		/**
		 * The destination is composited over the source and the result replaces the destination.
		 */
		DESTINATION_OVER("DstOverCompositeOp"),

		/**
		 * Produces an effect similar to that of ’difference’, but appears as lower contrast. Painting with white inverts the
		 * destination color. Painting with black produces no change.
		 */
		EXCLUSION("ExclusionCompositeOp"),

		/**
		 * Multiplies or screens the colors, dependent on the source color value. If the source color is lighter than 0.5, the
		 * destination is lightened as if it were screened. If the source color is darker than 0.5, the destination is darkened, as
		 * if it were multiplied. The degree of lightening or darkening is proportional to the difference between the source color
		 * and 0.5. If it is equal to 0.5 the destination is unchanged. Painting with pure black or white produces black or white.
		 */
		HARD_LIGHT("HardLightCompositeOp"),

		/**
		 * Each pixel in the result image is the combination of the hue of the target image and the saturation and brightness of the
		 * composite image.
		 */
		HUE("HueCompositeOp"),

		/**
		 * The result is simply composite image cut by the shape of image. None of the image data of image is included in the
		 * result.
		 */
		IN("InCompositeOp"),

		/**
		 * Replace target image pixels with lighter pixels from the composite image.
		 */
		LIGHTEN("LightenCompositeOp"),

		/**
		 * Same as LinearDodgeCompositeOp, but also subtract one from the result. Sort of a additive ’Screen’ of the images.
		 */
		LINEAR_BURN("LinearBurnCompositeOp"),

		/**
		 * This is equivelent to PlusCompositeOp in that the color channels are simply added, however it does not "plus" the alpha
		 * channel, but uses the normal OverCompositeOp alpha blending, which transparencies are involved. Produces a sort of
		 * additive multiply-like result.
		 */
		LINEAR_DODGE("LinearDodgeCompositeOp"),

		/**
		 * Increase contrast slightly with an impact on the foreground’s tonal values.
		 */
		LINEAR_LIGHT("LinearLightCompositeOp"),

		/**
		 * Each pixel in the result image is the combination of the brightness of the composite image and the saturation and hue of
		 * the target image. This is the opposite of ColorizeCompositeOp.
		 */
		LUMINIZE("LuminizeCompositeOp"),

		/**
		 * The result of composite image - image, with overflow cropped to zero. The matte channel is ignored (set to 255, full
		 * coverage).
		 */
		MINUS("MinusCompositeOp"),

		/**
		 * Multiplies the color of each target image pixel by the color of the corresponding composite image pixel. The result color
		 * is always darker.
		 */
		MULTIPLY("MultiplyCompositeOp"),

		/**
		 * No composite operator has been specified.
		 */
		NONE("NoCompositeOp"),

		/**
		 * The resulting image is composite image with the shape of image cut out.
		 */
		OUT("OutCompositeOp"),

		/**
		 * The result is the union of the the two image shapes with composite image obscuring image in the region of overlap. The
		 * matte channel of the composite image is respected, so that if the composite pixel is part or all transparent, the
		 * corresponding image pixel will show through.
		 */
		OVER("OverCompositeOp"),

		/**
		 * Multiplies or screens the colors, dependent on the destination color. Source colors overlay the destination whilst
		 * preserving its highlights and shadows. The destination color is not replaced, but is mixed with the source color to
		 * reflect the lightness or darkness of the destination.
		 */
		OVERLAY("OverlayCompositeOp"),

		/**
		 * Similar to HardLightCompositeOp, but using sharp linear shadings, to similate the effects of a strong ’pinhole’ light
		 * source.
		 */
		PIN_LIGHT("PinLightCompositeOp"),

		/**
		 * The result is just the sum of the image data. Output values are cropped to 255 (no overflow)
		 */
		PLUS("PlusCompositeOp"),

		/**
		 * The resulting image is image replaced with composite image. Here the matte information is ignored.
		 */
		REPLACE("ReplaceCompositeOp"),

		/**
		 * Each pixel in the result image is the combination of the saturation of the target image and the hue and brightness of the
		 * composite image.
		 */
		SATURATE("SaturateCompositeOp"),

		/**
		 * Multiplies the inverse of each image’s color information.
		 */
		SCREEN("ScreenCompositeOp"),

		/**
		 * Darkens or lightens the colors, dependent on the source color value. If the source color is lighter than 0.5, the
		 * destination is lightened. If the source color is darker than 0.5, the destination is darkened, as if it were burned in.
		 * The degree of darkening or lightening is proportional to the difference between the source color and 0.5. If it is equal
		 * to 0.5, the destination is unchanged. Painting with pure black or white produces a distinctly darker or lighter area, but
		 * does not result in pure black or white.
		 */
		SOFT_LIGHT("SoftLightCompositeOp"),

		/**
		 * The part of the source lying inside of the destination is composited onto the destination.
		 */
		SOURCE_ATOP("SrcAtopCompositeOp"),

		/**
		 * The source is copied to the destination. The destination is not used as input.
		 */
		SOURCE("SrcCompositeOp"),

		/**
		 * The part of the source lying inside of the destination replaces the destination.
		 */
		SOURCE_INSIDE("SrcInCompositeOp"),

		/**
		 * The part of the source lying outside of the destination replaces the destination.
		 */
		SOURCE_OUTSIDE("SrcOutCompositeOp"),

		/**
		 * The source is composited over the destination.
		 */
		SOURCE_OVER("SrcOverCompositeOp"),

		/**
		 * The result of composite image - image, with underflow wrapping around (mod 256). The add and subtract operators can be
		 * used to perform reversible transformations.
		 */
		SUBTRACT("SubtractCompositeOp"),

		/**
		 * The result is the image data from both composite image and image that is outside the overlap region. The overlap region
		 * will be blank.
		 */
		XOR("XorCompositeOp");

		public final String blitlineValue;

		private Operation(String blitlineValue) {
			this.blitlineValue = blitlineValue;
		}
		
		@JsonValue
		@Override
		public String toString() {
			return blitlineValue;
		}
	}
}
