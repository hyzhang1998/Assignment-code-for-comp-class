package a7;

public class RegionImpl implements Region {
	
//	field
	private Coordinate upperLeft;
	private Coordinate lowerRight;

//	Constructor
	public RegionImpl(Coordinate a, Coordinate b) {
		if (a == null || b == null) {
			throw new IllegalArgumentException("One or both coordinates is null");
		}
		int min_x = a.getX() < b.getX() ? a.getX() : b.getX();
		int min_y = a.getY() < b.getY() ? a.getY() : b.getY();
		int max_x = a.getX() > b.getX() ? a.getX() : b.getX();
		int max_y = a.getY() > b.getY() ? a.getY() : b.getY();
		
		upperLeft = new Coordinate(min_x, min_y);
		lowerRight = new Coordinate(max_x, max_y);
	}
	
//	returns the upper left corner of the region
	@Override
	public Coordinate getUpperLeft() {
		return upperLeft;
	}
	
//	returns the lower right corner of the region
	@Override
	public Coordinate getLowerRight() {
		return lowerRight;
	}

//	returns the y value of the top side of the region
	@Override
	public int getTop() {
		return upperLeft.getY();
	}

//	returns the y value of the bottom side of the region
	@Override
	public int getBottom() {
		return lowerRight.getY();
	}

//	returns the x value of the left side of the region
	@Override
	public int getLeft() {
		return upperLeft.getX();
	}

//	returns the x value of the right side of the region
	@Override
	public int getRight() {
		return lowerRight.getX();
	}

//	returns the intersection region between two regions
	@Override
	public Region intersect(Region other) throws NoIntersectionException{
		if (other == null) {
			throw new NoIntersectionException();
		}
		if(getLeft() > other.getRight() || getRight() < other.getLeft() || getTop() > other.getBottom() || getBottom() < other.getTop()) {
			throw new NoIntersectionException();
		}
		int min_x = getLeft() > other.getLeft() ? getLeft() : other.getLeft();
		int min_y = getTop() > other.getTop() ? getTop() : other.getTop();
		int max_x = getRight() < other.getRight() ? getRight() : other.getRight();
		int max_y = getBottom() < other.getBottom() ? getBottom() : other.getBottom();
		return new RegionImpl(new Coordinate(min_x, min_y), new Coordinate(max_x, max_y));
	}
	
//	returns the encompassing region of two regions
	@Override
	public Region union(Region other) {
		if(other == null) {
			return this;
		}
		int min_x = getLeft() < other.getLeft() ? getLeft() : other.getLeft();
		int min_y = getTop() < other.getTop() ? getTop() : other.getTop();
		int max_x = getRight() > other.getRight() ? getRight() : other.getRight();
		int max_y = getBottom() > other.getBottom() ? getBottom() : other.getBottom();
		return new RegionImpl(new Coordinate(min_x, min_y), new Coordinate(max_x, max_y));
	}

}
