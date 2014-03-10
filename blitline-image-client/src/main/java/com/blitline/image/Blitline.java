package com.blitline.image;

import java.net.URL;

import com.blitline.image.functions.*;

public class Blitline {
	private Blitline() {
	}

	public static Annotate annotate(String text) {
		return new Annotate(text);
	}

	public static AutoEnhance autoEnhance() {
		return AutoEnhance.INSTANCE;
	}

	public static AutoGamma autoGamma() {
		return AutoGamma.INSTANCE;
	}

	public static AutoLevel autoLevel() {
		return AutoLevel.INSTANCE;
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

	public static Composite compositeWith(URL src) {
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
		return Despeckle.INSTANCE;
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
		return Enhance.INSTANCE;
	}

	public static Equalize equalize() {
		return Equalize.INSTANCE;
	}

	public static Equalize autoEqualize() {
		return Equalize.INSTANCE;
	}

	public static GammaChannel adjustGammaBy(double gammaAdjustment) {
		return new GammaChannel(gammaAdjustment);
	}

	public static Grayscale toGrayscale() {
		return Grayscale.INSTANCE;
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
		return NoOp.INSTANCE;
	}

	public static Normalize normalize() {
		return Normalize.INSTANCE;
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

	public static Stegano steganoWatermark(URL src) {
		return new Stegano(src);
	}

	public static Tile tile(String srcUrl) {
		return new Tile(srcUrl);
	}

	public static Tile tile(URL src) {
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