package a4jedi;

public class HorizontalStackPicture extends StackPicture {
	private Picture left;
	private Picture right;

	public HorizontalStackPicture(Picture left, Picture right) {
		super(left, right);
		
		if(left.getHeight() != right.getHeight()) {
			throw new IllegalArgumentException("Input picture incompatible!");
		}
		width = left.getWidth()+right.getWidth();
		height = left.getHeight();
		this.left = left;
		this.right = right;
	}

	@Override
	public void setPixel(int x, int y, Pixel p) {
		if(p == null) {
			throw new IllegalArgumentException("Input value invalid!");
		}
		if(x <= width && y <= height && x >= 0 && y >= 0) {
			if(x <= left.getWidth()) {
				left.setPixel(x, y, p);
			}
			else{
				right.setPixel(x-left.getWidth(), y, p);
			}
		}
		else {
			throw new IllegalArgumentException("Index invalid!");
		}
	}

	@Override
	public Pixel getPixel(int x, int y) {
		if(x <= width && y <= height && x >= 0 && y >=0) {
			if(x <= left.getWidth()) {
				return left.getPixel(x, y);
			}
			else{
				return right.getPixel(x-left.getWidth(), y);
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
