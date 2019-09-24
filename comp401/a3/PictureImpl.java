package a3jedi;

public class PictureImpl implements Picture {
	private int width;
	private int height;
	Pixel pic[][];

	public PictureImpl(int width, int height) {
		this.width = width;
		this.height = height;
		pic = new ColorPixel[width][height];
		for(int i = 0; i < pic[0].length; i++) {
			for(int j = 0; j < pic.length; j++) {
				pic[j][i] = new ColorPixel(0.5, 0.5, 0.5);
			}
		}
		if(width <= 0 || height <= 0) {
			throw new RuntimeException("Input value invalid!");
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
	public void setPixel(int x, int y, Pixel p) {
		if(x <= width && y <= height && x >= 0 && y >= 0 && p != null) {
			pic[x][y] = new ColorPixel(p.getRed(), p.getGreen(), p.getBlue());
		}
		else {
			throw new RuntimeException("Index invalid!");
		}
	}

	@Override
	public Pixel getPixel(int x, int y) {
		if(x <= width && y <= height && x >= 0 && y >=0 && pic[x][y] != null) {
			return pic[x][y];
		}
		else {
			throw new RuntimeException("Index invalid!");
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
			throw new RuntimeException("Input value invalid!");
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

	@Override
	public double unequalPixelRatio(Picture p) {
		double ratio;
		double unequal = 0;
		double total = height*width;
		if(p.getWidth() == this.getWidth() && p.getHeight() == this.getHeight()) {
			for(int i = 0; i < this.getHeight(); i++) {
				for(int j = 0; j < this.getWidth(); j++) {
					if(!pic[j][i].equals(p.getPixel(j, i))) {
						unequal++;
					}
				}
			}
			ratio = unequal/total;
			return ratio;
		}
		else {
			throw new RuntimeException("Input picture not the same size!");
		}
	}

	@Override
	public double calculatePSNR(Picture p) {
		double mse = 0;
		double max = 1;
		if(p.getWidth() == this.getWidth() && p.getHeight() == this.getHeight()) {
			for(int i = 0; i < this.getHeight(); i++) {
				for(int j = 0; j < this.getWidth(); j++) {
					mse+=((pic[j][i].getIntensity() - p.getPixel(j, i).getIntensity())*(pic[j][i].getIntensity() - p.getPixel(j, i).getIntensity()));
				}
			}
			mse/=(this.getWidth()*this.getHeight());
			if(mse != 0) {
				double psnr = 20*Math.log10(max) - 10*Math.log10(mse);
				return psnr;
			}
			else {
				throw new RuntimeException("Picture identical!");
			}
		}
		else {
			throw new RuntimeException("Input picture not the same size!");
		}
	}

}
