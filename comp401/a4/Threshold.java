package a4jedi;

public class Threshold implements PixelTransformation {
	private Pixel pixel;
	private double threshold;
	public Threshold (double threshold) {
		if(threshold < 0 || threshold > 1) {
			throw new IllegalArgumentException("Input value invalid!");
		}
		this.threshold = threshold;
	}

	@Override
	public Pixel transform(Pixel p) {
		if(p == null) {
			throw new IllegalArgumentException("Input value invalid!");
		}
		if(p.getIntensity() > threshold) {
			pixel = new GrayPixel(1);
		}
		else {
			pixel = new GrayPixel(0);
		}
		return pixel;
	}

}
