package a4jedi;

public class TransformedPicture implements Picture {
	private Picture source;
	private PixelTransformation xform;
	public TransformedPicture (Picture source, PixelTransformation xform) {
		if(source == null || xform == null) {
			throw new IllegalArgumentException("Input value invalid!");
		}
		this.source = source;
		this.xform = xform;
	}
	
	@Override
	public int getWidth() {
		return source.getWidth();
	}

	@Override
	public int getHeight() {
		return source.getHeight();
	}

	@Override
	public void setPixel(int x, int y, Pixel p) {
		throw new UnsupportedOperationException("Method unsupported in Transformed Picture!");
	}

	@Override
	public Pixel getPixel(int x, int y) {
		return xform.transform(source.getPixel(x, y));
	}

	@Override
	public int countRange(double low, double high) {
		return source.countRange(low, high);
	}

	@Override
	public void print() {
		source.print();
	}

	@Override
	public SubPicture extract(int xOffset, int yOffset, int width, int height) {
		return source.extract(xOffset, yOffset, width, height);
	}

	@Override
	public TransformedPicture transform(PixelTransformation xform) {
		TransformedPicture n = new TransformedPicture(this, xform);
		for(int i = 0; i < source.getWidth(); i++) {
			for(int j = 0; j < source.getHeight(); j++) {
				n.getPixel(j, i);
			}
		}
		return n;
	}

}
