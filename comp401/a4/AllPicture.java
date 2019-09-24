package a4jedi;

public abstract class AllPicture implements Picture {
	protected int width;
	protected int height;
	
	public AllPicture(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public abstract void setPixel(int x, int y, Pixel p);

	@Override
	public abstract Pixel getPixel(int x, int y);

	@Override
	public abstract int countRange(double low, double high);

	@Override
	public abstract void print();

	@Override
	public SubPicture extract(int xOffset, int yOffset, int width, int height) {
		return new SubPictureImpl(this, xOffset, yOffset, width, height);
	}

	@Override
	public TransformedPicture transform(PixelTransformation xform) {
		TransformedPicture n = new TransformedPicture(this, xform);
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				n.getPixel(j, i);
			}
		}
		return n;
	}
}
