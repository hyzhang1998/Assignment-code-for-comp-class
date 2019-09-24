package a3jedi;

public interface Picture {
	public int getWidth();
	public int getHeight();
	public void setPixel(int x, int y, Pixel p);
	public Pixel getPixel(int x, int y);
	public int countRange(double low, double high);
	public void print();
	public double unequalPixelRatio(Picture p);
	public double calculatePSNR(Picture p);
}
