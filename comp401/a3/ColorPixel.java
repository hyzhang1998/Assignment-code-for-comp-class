package a3jedi;

public class ColorPixel implements Pixel {
	private double red;
	private double green;
	private double blue;
	private double intensity;

	public ColorPixel(double red, double green, double blue) {
		if(red <= 1 && red >= 0 && blue <= 1 && blue >= 0 && green <= 1 && green >= 0) {
			this.red = red;
			this.blue = blue;
			this.green = green;
		}
		else {
			throw new RuntimeException("Input value out of bound!");
		}
		intensity = 0.299 * red + 0.587 * green + 0.114 * blue;
	}

	@Override
	public double getRed() {
		return red;
	}

	@Override
	public double getGreen() {
		return green;
	}

	@Override
	public double getBlue() {
		return blue;
	}

	@Override
	public double getIntensity() {
		return intensity;
	}

	@Override
	public char getChar() {
		if(intensity >= 0 && intensity < 0.1) {
			return '#';
		}
		else if(intensity >= 0.1 && intensity < 0.2) {
			return 'M';
		}
		else if(intensity >= 0.2 && intensity < 0.3) {
			return 'X';
		} 
		else if(intensity >= 0.3 && intensity < 0.4) {
			return 'D';
		}
		else if(intensity >= 0.4 && intensity < 0.5) {
			return '<';
		}
		else if(intensity >= 0.5 && intensity < 0.6) {
			return '>';
		}
		else if(intensity >= 0.6 && intensity < 0.7) {
			return 's';
		}
		else if(intensity >= 0.7 && intensity < 0.8) {
			return ':';
		}
		else if(intensity >= 0.8 && intensity < 0.9) {
			return '-';
		}
		else
			return ' ';
	}

	@Override
	public Pixel blend(Pixel p, double weight) {
		if(weight >= 0 && weight <= 1) {
			double newRed = red*weight + p.getRed()*(1 - weight);
			double newBlue = blue*weight + p.getBlue()*(1 - weight);
			double newGreen = green*weight + p.getGreen()*(1 - weight);
			Pixel blended = new ColorPixel(newRed, newGreen, newBlue);
			return blended;
		}
		else {
			throw new RuntimeException("Input value invalid!");
		}
	}

	@Override
	public Pixel lighten(double factor) {
		if(factor >= 0 && factor <= 1) {
			double newRed = red + (1 - red)*factor;
			double newGreen = green + (1 - green)*factor;
			double newBlue = blue + (1 - blue)*factor;
			Pixel lightened = new ColorPixel(newRed, newGreen, newBlue);
			return lightened;
		}
		else {
			throw new RuntimeException("Input value invalid!");
		}
	}

	@Override
	public Pixel darken(double factor) {
		if(factor >= 0 && factor <= 1) {
			double newRed = red - red*factor;
			double newGreen = green - green*factor;
			double newBlue = blue - blue*factor;
			Pixel darkened = new ColorPixel(newRed, newGreen, newBlue);
			return darkened;
		}
		else {
			throw new RuntimeException("Input value invalid!");
		}
	}

	@Override
	public boolean equals(Pixel p) {
		double brighter;
		if(intensity > p.getIntensity()) {
			brighter = intensity;
		}
		else {
			brighter = p.getIntensity();
		}
		if(Math.abs(red - p.getRed()) < brighter*0.1 && Math.abs(green - p.getGreen()) < brighter*0.1 && Math.abs(blue - p.getBlue()) < brighter*0.1) {
			return true;
		}
		else {
			return false;
		}
	}
}
