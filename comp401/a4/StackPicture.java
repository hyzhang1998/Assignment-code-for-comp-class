package a4jedi;

public abstract class StackPicture implements Picture {

	protected int width;
	protected int height;
	
	public StackPicture(Picture a, Picture b) {
		if(a == null || b == null) {
			throw new IllegalArgumentException("Input value invalid!");
		}
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
		// TODO Auto-generated method stub
		return null;
	}
}