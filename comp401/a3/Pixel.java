package a3jedi;

public interface Pixel {
	
	double getRed();
	double getGreen();
	double getBlue();
	double getIntensity();
	char getChar();
	public Pixel blend(Pixel p, double weight);
	public Pixel lighten(double factor);
	public Pixel darken(double factor);
	public boolean equals(Pixel p);
	
}
