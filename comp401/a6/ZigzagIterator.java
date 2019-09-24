package a6jedi;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ZigzagIterator implements Iterator<Pixel> {
	private Picture p;
	private int rowIndex = 0;
	private int columnIndex = 0;
	private boolean upright = true;
	private Coordinate c = new Coordinate(columnIndex, rowIndex);

//	Constructor
	public ZigzagIterator(Picture p) {
		this.p = p;
	}

//	check whether there are more pixels in the operator
	@Override
	public boolean hasNext() {
		if(c.getY() == p.getHeight() - 1 && c.getX() == p.getWidth() - 1) {
			return false;
		}
		else {
			return true;
		}
	}

//	return the next pixel in zigzag order of the picture.
	@Override
	public Pixel next() {
		if(!this.hasNext()) {
			throw new NoSuchElementException("Has already reached the end of iterator!");
		}
		if(upright && columnIndex + 1 < p.getWidth() && rowIndex > 0) {
			c = new Coordinate(columnIndex, rowIndex);
			this.goUpright();
		}
		else if(upright && columnIndex + 1 == p.getWidth()) {
			c = new Coordinate(columnIndex, rowIndex);
			this.goDown();
		}
		else if(upright && columnIndex + 1 < p.getWidth() && rowIndex == 0) {
			c = new Coordinate(columnIndex, rowIndex);
			this.goRight();
		}
		else if(!upright && columnIndex > 0 && rowIndex < p.getHeight() - 1) {
			c = new Coordinate(columnIndex, rowIndex);
			this.goDownleft();
		}
		else if(!upright && rowIndex == p.getHeight() - 1) {
			c = new Coordinate(columnIndex, rowIndex);
			this.goRight();
		}
		else if (!upright && rowIndex < p.getHeight() - 1 && columnIndex == 0) {
			c = new Coordinate(columnIndex, rowIndex);
			this.goDown();
		}
			return p.getPixel(c);
	}

	private void goRight() {
		columnIndex++;
		upright = !upright;
	}

	private void goDown() {
		rowIndex++;
		upright = !upright;
	}

	private void goUpright() {
		rowIndex--;
		columnIndex++;
	}
	
	private void goDownleft() {
		rowIndex++;
		columnIndex--;
	}
}
