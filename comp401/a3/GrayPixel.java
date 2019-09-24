package a3jedi;

public class GrayPixel implements Pixel {
	private double intensity;

	public GrayPixel(double intensity) {
		if(intensity <= 1 && intensity >= 0) {
			this.intensity = intensity;
		}
		else {
			throw new RuntimeException("Input value invalid!");
		}
	}

	@Override
	public double getRed() {
		return intensity;
	}

	@Override
	public double getGreen() {
		return intensity;
	}

	@Override
	public double getBlue() {
		return intensity;
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
			double newIntensity = intensity*weight + p.getIntensity()*(1 - weight);
			Pixel blended = new GrayPixel(newIntensity);
			return blended;
		}
		else {
			throw new RuntimeException("Input value invalid!");
		}
	}

	@Override
	public Pixel lighten(double factor) {
		if(factor >= 0 && factor <= 1) {
			double newIntensity = intensity + (1 - intensity)*factor;
			Pixel lightened = new GrayPixel(newIntensity);
			return lightened;
		}
		else {
			throw new RuntimeException("Input value invalid!");
		}
	}

	@Override
	public Pixel darken(double factor) {
		if(factor >= 0 && factor <= 1) {
			double newIntensity = intensity - intensity*factor;
			Pixel darkened = new GrayPixel(newIntensity);
			return darkened;
		}
		else {
			throw new RuntimeException("Input value invalid!");
		}
	}

	@Override
	public boolean equals(Pixel p) {
		double bigger;
		if(intensity > p.getIntensity()) {
			bigger = intensity;
		}
		else {
			bigger = p.getIntensity();
		}
		if(Math.abs(intensity - p.getIntensity()) < bigger*0.1) {
			return true;
		}
		else {
			return false;
		}
	}
}