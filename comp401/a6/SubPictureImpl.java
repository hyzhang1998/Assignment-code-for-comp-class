package a6jedi;

public class SubPictureImpl extends AllPicture implements SubPicture{
	private Picture source;
	private int xOffset;
	private int yOffset;

	public SubPictureImpl(Picture source, int xOffset, int yOffset, int width, int height) {
		super(width, height);
		if(source == null || xOffset < 0 || yOffset < 0 || xOffset > source.getWidth() || yOffset > source.getHeight() || width > (source.getWidth() - xOffset) || height > source.getHeight() - yOffset) {
			throw new IllegalArgumentException("Input value invalid!");
		}
		this.source = source;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}


	@Override
	public void setPixel(int x, int y, Pixel p) {
		if(p == null) {
			throw new IllegalArgumentException("Input value invalid!");
		}
		if(x < width && y < height && x >= 0 && y >= 0) {
			source.setPixel(x+this.getXOffset(), y+this.getYOffset(), p);
		}
		else {
			throw new IllegalArgumentException("Index invalid!");
		}
	}

	@Override
	public Pixel getPixel(int x, int y) {
		if(x < width && y < height && x >= 0 && y >=0) {
			return source.getPixel(x+this.getXOffset(), y+this.getYOffset());
		}
		else {
			throw new IllegalArgumentException("Index invalid!");
		}
	}

	@Override
	public int countRange(double low, double high) {
		int count = 0;
		if(low <= high && low <= 1 && low >= 0 && high <= 1 && high >= 0) {
			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++) {
					if(source.getPixel(this.getXOffset()+j, this.getYOffset()+i).getIntensity() <= high && source.getPixel(this.getXOffset()+j, this.getYOffset()+i).getIntensity() >= low) {
						count++;
					}
				}
			}
		}
		else {
			throw new RuntimeException("Input value invalid!");
		}
		return count;
	}

	@Override
	public void print() {
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				System.out.print(source.getPixel(this.getXOffset()+j, this.getYOffset()+i).getChar());
			}
			System.out.println();
		}
	}

	@Override
	public Picture getSource() {
		return source;
	}

	@Override
	public int getXOffset() {
		return xOffset;
	}

	@Override
	public int getYOffset() {
		return yOffset;
	}


}
