package a4jedi;

public class GammaCorrect implements PixelTransformation {
	private double gamma;
	private Pixel pixel;
	public GammaCorrect (double gamma) {
		if(gamma == 0) {
			throw new IllegalArgumentException("Input value invalid!");
		}
		this.gamma = gamma;
	}
	
	@Override
	public Pixel transform(Pixel p) {
		if(p == null) {
			throw new IllegalArgumentException("Input value invalid!");
		}
		pixel = new ColorPixel(Math.pow(p.getRed(), (1.0/gamma)), Math.pow(p.getGreen(), (1.0/gamma)), Math.pow(p.getBlue(), (1.0/gamma)));
		return pixel;
	}

}
