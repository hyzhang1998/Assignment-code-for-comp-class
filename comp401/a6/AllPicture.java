package a6jedi;

import java.util.Iterator;

public abstract class AllPicture implements Picture {
	protected int width;
	protected int height;

	public AllPicture(int width, int height) {
		if(width <= 0 || height <= 0) {
			throw new IllegalArgumentException("Input value invalid!");
		}
		this.width = width;
		this.height = height;
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
	public void setPixel(Coordinate c, Pixel p) {
		if(c == null) {
			throw new IllegalArgumentException("Input value invalid!");
		}
		this.setPixel(c.getX(), c.getY(), p);
	}

	@Override
	public Pixel getPixel(Coordinate c) {
		if(c == null) {
			throw new IllegalArgumentException("Input value invalid!");
		}
		return this.getPixel(c.getX(), c.getY());
	}

	@Override
	public SubPicture extract(Coordinate corner_a, Coordinate corner_b) {
		if(corner_a == null || corner_b == null) {
			throw new IllegalArgumentException("Input value invalid!");
		}
		if(corner_a.getX() < corner_b.getX() && corner_a.getY() < corner_b.getY()) {
			return this.extract(corner_a.getX(), corner_a.getY(), corner_b.getX()-corner_a.getX()+1, corner_b.getY()-corner_a.getY()+1);
		}
		else if(corner_a.getX() < corner_b.getX() && corner_a.getY() > corner_b.getY()) {
			return this.extract(corner_a.getX(), corner_b.getY(), corner_b.getX()-corner_a.getX()+1, corner_a.getY()-corner_b.getY()+1);
		}
		else if(corner_a.getX() > corner_b.getX() && corner_a.getY() < corner_b.getY()) {
			return this.extract(corner_b.getX(), corner_a.getY(), corner_a.getX()-corner_b.getX()+1, corner_b.getY()-corner_a.getY()+1);
		}
		else {
			return this.extract(corner_b.getX(), corner_b.getY(), corner_a.getX()-corner_b.getX()+1, corner_a.getY()-corner_b.getY()+1);
		}
	}

	@Override
	public Iterator<Pixel> iterator(){
		return new RowMajorPixelIterator(this);
	}
	
	@Override
	public Iterator<Pixel> sample(int init_x, int init_y, int dx, int dy){
		if(init_x < 0 || init_y < 0 || init_x >= this.getWidth() || init_y >= this.getHeight() || dx <= 0 || dx <= 0) {
			throw new IllegalArgumentException("Input value invalid!");
		}
		return new SampleIterator(this, init_x, init_y, dx, dy);
	}
	
	@Override
	public Iterator<SubPicture> window(int window_width, int window_height){
		if(window_width <= 0 || window_height <= 0 || window_height > this.getHeight() || window_width > this.getWidth()) {
			throw new IllegalArgumentException("Input value invalid!");
		}
		return new WindowIterator(this, window_width, window_height);
	}
	
	@Override
	public Iterator<SubPicture> tile(int tile_width, int tile_height){
		if(tile_width <= 0 || tile_height <= 0 || tile_height > this.getHeight() || tile_width > this.getWidth()) {
			throw new IllegalArgumentException("Input value invalid!");
		}
		return new TileIterator(this, tile_width, tile_height);
	}
	
	@Override
	public Iterator<Pixel> zigzag(){
		return new ZigzagIterator(this);
	}
}
