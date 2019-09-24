package a4jedi;

public class VerticalStackPicture extends StackPicture {
	private Picture up;
	private Picture bottom;

	public VerticalStackPicture(Picture up, Picture bottom) {
		super(up, bottom);
		
		if(up.getWidth() != bottom.getWidth()) {
			throw new IllegalArgumentException("Input picture incompatible!");
		}
		width = up.getWidth();
		height = up.getHeight()+bottom.getHeight();
		this.up = up;
		this.bottom = bottom;
	}

	@Override
	public void setPixel(int x, int y, Pixel p) {
		if(p == null) {
			throw new IllegalArgumentException("Input value invalid!");
		}
		if(x <= width && y <= height && x >= 0 && y >= 0) {
			if(y <= up.getHeight()) {
				up.setPixel(x, y, p);
			}
			else{
				bottom.setPixel(x, y-up.getHeight(), p);
			}
		}
		else {
			throw new IllegalArgumentException("Index invalid!");
		}
	}

	@Override
	public Pixel getPixel(int x, int y) {
		if(x <= width && y <= height && x >= 0 && y >=0) {
			if(y <= up.getHeight()) {
				return up.getPixel(x, y);
			}
			else{
				return bottom.getPixel(x, y-up.getHeight());
			}
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
					if(this.getPixel(j, i).getIntensity() <= high && this.getPixel(j, i).getIntensity() >= low) {
						count++;
					}
				}
			}
		}
		else {
			throw new IllegalArgumentException("Input value invalid!");
		}
		return count;
	}

	@Override
	public void print() {
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				System.out.print(this.getPixel(j, i).getChar());
			}
			System.out.println();
		}
	}

}
