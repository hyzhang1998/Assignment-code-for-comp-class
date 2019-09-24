package a6jedi;

public class PictureImpl extends AllPicture{
	private Pixel pic[][];

	public PictureImpl(int width, int height) {
		super(width, height);
		if(width <= 0 || height <= 0) {
			throw new IllegalArgumentException("Input value invalid!");
		}
		pic = new Pixel[width][height];
		for(int i = 0; i < pic[0].length; i++) {
			for(int j = 0; j < pic.length; j++) {
				pic[j][i] = new ColorPixel(0.5, 0.5, 0.5);
			}
		}
	}

	@Override
	public void setPixel(int x, int y, Pixel p) {
		if(p == null) {
			throw new IllegalArgumentException("Input value invalid!");
		}
		if(x < width && y < height && x >= 0 && y >= 0) {
			pic[x][y] = p;
		}
		else {
			throw new IllegalArgumentException("Index invalid!");
		}
	}

	@Override
	public Pixel getPixel(int x, int y) {
		if(x < width && y < height && x >= 0 && y >=0) {
			return pic[x][y];
		}
		else {
			throw new IllegalArgumentException("Index invalid!");
		}
	}

	@Override
	public int countRange(double low, double high) {
		int count = 0;
		if(low <= high && low <= 1 && low >= 0 && high <= 1 && high >= 0) {
			for(int i = 0; i < pic[0].length; i++) {
				for(int j = 0; j < pic.length; j++) {
					if(pic[j][i].getIntensity() <= high && pic[j][i].getIntensity() >= low) {
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
		for(int i = 0; i < pic[0].length; i++) {
			for(int j = 0; j < pic.length; j++) {
				System.out.print(pic[j][i].getChar());
			}
			System.out.println();
		}
	}


}
