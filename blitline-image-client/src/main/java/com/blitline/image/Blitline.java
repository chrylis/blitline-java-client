package com.blitline.image;

import java.net.URI;

import com.blitline.image.functions.Annotate;
import com.blitline.image.functions.AutoEnhance;
import com.blitline.image.functions.AutoGamma;
import com.blitline.image.functions.AutoLevel;
import com.blitline.image.functions.BackgroundColor;
import com.blitline.image.functions.BashScript;
import com.blitline.image.functions.Blur;
import com.blitline.image.functions.Composite;
import com.blitline.image.functions.Contrast;
import com.blitline.image.functions.ContrastStretchChannel;
import com.blitline.image.functions.Crop;
import com.blitline.image.functions.CropToSquare;
import com.blitline.image.functions.DeleteColorProfile;
import com.blitline.image.functions.Density;
import com.blitline.image.functions.Deskew;
import com.blitline.image.functions.Despeckle;
import com.blitline.image.functions.Ellipse;
import com.blitline.image.functions.Enhance;
import com.blitline.image.functions.Equalize;
import com.blitline.image.functions.GammaChannel;
import com.blitline.image.functions.Grayscale;
import com.blitline.image.functions.ImaggaSmartCrop;
import com.blitline.image.functions.Line;
import com.blitline.image.functions.MedianFilter;
import com.blitline.image.functions.Modulate;
import com.blitline.image.functions.NoOp;
import com.blitline.image.functions.Normalize;
import com.blitline.image.functions.Pad;
import com.blitline.image.functions.PadResizeToFit;
import com.blitline.image.functions.PhotographEffect;
import com.blitline.image.functions.Quantize;
import com.blitline.image.functions.Rectangle;
import com.blitline.image.functions.Resample;
import com.blitline.image.functions.Resize;
import com.blitline.image.functions.ResizeToFill;
import com.blitline.image.functions.ResizeToFit;
import com.blitline.image.functions.Rotate;
import com.blitline.image.functions.RunExecutable;
import com.blitline.image.functions.Scale;
import com.blitline.image.functions.SepiaTone;
import com.blitline.image.functions.Sharpen;
import com.blitline.image.functions.Sketch;
import com.blitline.image.functions.Stegano;
import com.blitline.image.functions.Tile;
import com.blitline.image.functions.Trim;
import com.blitline.image.functions.Unsharpen;
import com.blitline.image.functions.Vignette;
import com.blitline.image.functions.Watermark;

public class Blitline {

	/**
	 * The API version this library will invoke. 1.21 added the ability to append arbitrary EXIF to saved images.
	 */
	public static final String BLITLINE_API_VERSION = "1.21";

	private Blitline() {
	}

	public static Annotate annotate(String text) {
		return new Annotate(text);
	}

	public static AutoEnhance autoEnhance() {
		return new AutoEnhance();
	}

	public static AutoGamma autoGamma() {
		return new AutoGamma();
	}

	public static AutoLevel autoLevel() {
		return new AutoLevel();
	}

	public static BackgroundColor backgroundColor() {
		return new BackgroundColor();
	}

	public static BackgroundColor fillBackground() {
		return backgroundColor();
	}

	public static BackgroundColor fillBackgroundWith(String color) {
		return backgroundColor().with(color);
	}

	public static BashScript runBashScript(String script) {
		return new BashScript(script);
	}

	public static Blur blur() {
		return new Blur();
	}

	public static Composite compositeWith(String srcUrl) {
		return new Composite(srcUrl);
	}

	public static Composite compositeWith(URI src) {
		return new Composite(src);
	}

	public static Composite compositeWith(S3Location src) {
		return new Composite(src);
	}

	public static Composite compositeWith(AzureLocation src) {
		return new Composite(src);
	}

	public static Contrast autoContrast() {
		return new Contrast();
	}

	public static ContrastStretchChannel stretchContrast(int blackPoint) {
		return new ContrastStretchChannel(blackPoint);
	}

	public static ContrastStretchChannel stretchContrast(int blackPoint, int whitePoint) {
		return new ContrastStretchChannel(blackPoint).whitePoint(whitePoint);
	}

	public static Crop cropToWidth(int width) {
		return new Crop(width);
	}

	public static Crop cropToSize(int width, int height) {
		return new Crop(width).height(height);
	}

	public static CropToSquare cropToSquare() {
		return new CropToSquare();
	}

	public static DeleteColorProfile deleteColorProfile(String profileName) {
		return new DeleteColorProfile(profileName);
	}

	public static Deskew deskew() {
		return new Deskew();
	}

	public static Density setDpi(int dpi) {
		return new Density(dpi);
	}

	public static Despeckle despeckle() {
		return new Despeckle();
	}

	public static Ellipse ellipse(int centerX, int centerY, int width, int height) {
		return new Ellipse(centerX, centerY, width, height);
	}

	public static Ellipse drawEllipse(int centerX, int centerY, int width, int height) {
		return ellipse(centerX, centerY, width, height);
	}

	/**
	 * Invokes ImageMagick's {@code -enhance} option; most applications will prefer {@link #autoEnhance()}.
	 *
	 * @return the singleton instance of the {@link Enhance} function
	 */
	public static Enhance enhance() {
		return new Enhance();
	}

	public static Equalize equalize() {
		return new Equalize();
	}

	public static Equalize autoEqualize() {
		return equalize();
	}

	public static GammaChannel adjustGammaBy(double gammaAdjustment) {
		return new GammaChannel(gammaAdjustment);
	}

	public static Grayscale toGrayscale() {
		return new Grayscale();
	}

	public static ImaggaSmartCrop imaggaCrop(int width, int height) {
		return new ImaggaSmartCrop(width, height);
	}

	public static Line line(int x1, int y1, int x2, int y2) {
		return new Line(x1, y1, x2, y2);
	}

	public static Line drawLine(int x1, int y1, int x2, int y2) {
		return line(x1, y1, x2, y2);
	}

	public static MedianFilter medianFilter() {
		return new MedianFilter();
	}

	public static Modulate modulate() {
		return new Modulate();
	}

	public static NoOp noOp() {
		return new NoOp();
	}

	public static Normalize normalize() {
		return new Normalize();
	}

	public static Pad pad(int thickness) {
		return new Pad(thickness);
	}

	public static PadResizeToFit resizeAndPadTo(int width, int height) {
		return new PadResizeToFit(width, height);
	}

	public static PhotographEffect photographEffect() {
		return new PhotographEffect();
	}

	public static Quantize quantize(int numberOfColors) {
		return new Quantize(numberOfColors);
	}

	public static Rectangle rectangle(int x1, int y1, int x2, int y2) {
		return new Rectangle(x1, y1, x2, y2);
	}

	public static Rectangle drawRectangle(int x1, int y1, int x2, int y2) {
		return rectangle(x1, y1, x2, y2);
	}

	public static Resample resampleTo(int dpi) {
		return new Resample(dpi);
	}

	public static Resize resizeTo(int width, int height) {
		return new Resize(width, height);
	}

	public static ResizeToFill resizeToFill(int width, int maxHeight) {
		return new ResizeToFill(width, maxHeight);
	}

	public static ResizeToFit resizeToFit(int maxWidth, int maxHeight) {
		return new ResizeToFit(maxWidth, maxHeight);
	}

	public static Rotate rotateBy(double degrees) {
		return new Rotate(degrees);
	}

	public static Scale scaleBy(double scaleFactor) {
		return new Scale(scaleFactor);
	}

	public static RunExecutable runExecutable(String executableCommand) {
		return new RunExecutable(executableCommand);
	}

	public static SepiaTone sepiaFilter() {
		return new SepiaTone();
	}

	public static Sharpen sharpen() {
		return new Sharpen();
	}

	public static Sketch sketchEffect() {
		return new Sketch();
	}

	public static Stegano steganoWatermark(String srcUrl) {
		return new Stegano(srcUrl);
	}

	public static Stegano steganoWatermark(URI src) {
		return new Stegano(src);
	}

	public static Tile tile(String srcUrl) {
		return new Tile(srcUrl);
	}

	public static Tile tile(URI src) {
		return new Tile(src);
	}

	public static Trim trim() {
		return new Trim();
	}

	public static Trim autoTrim() {
		return trim();
	}

	public static Unsharpen unsharpen() {
		return new Unsharpen();
	}

	public static Vignette vignette() {
		return new Vignette();
	}

	public static Watermark watermark(String watermarkText) {
		return new Watermark(watermarkText);
	}

	public static Watermark textWatermark(String watermarkText) {
		return watermark(watermarkText);
	}
}