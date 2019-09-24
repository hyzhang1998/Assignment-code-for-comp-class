package a6jedi;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SampleIterator implements Iterator<Pixel> {
	private Picture p;
	private int rowIndex;
	private int columnIndex;
	private int dx;
	private int dy;
	private int bound_y;
	private int bound_x;
	private int init_x;

//	Constructor
	public SampleIterator(Picture p, int init_x, int init_y, int dx, int dy) {
		this.p = p;
		this.init_x = init_x;
		columnIndex = init_x;
		rowIndex = init_y;
		this.dx = dx;
		this.dy = dy;
		if((p.getWidth() - init_x) % dx == 0) {
			bound_x = init_x + dx*((p.getWidth() - init_x)/dx - 1);
		}
		else {
			bound_x = init_x + dx*((p.getWidth() - init_x)/dx);
		}
		if((p.getHeight() - init_y) % dy == 0) {
			bound_y = init_y + dy*((p.getHeight() - init_y)/dy - 1);
		}
		else {
			bound_y = init_y + dy*((p.getHeight() - init_y)/dy);
		}
	}

//	check whether there are more pixels in the operator
	@Override
	public boolean hasNext() {
		if(rowIndex < bound_y) {
			return true;
		}
		else if(rowIndex == bound_y) {
			if(columnIndex > bound_x) {
				return false;
			}
			else {
				return true;
			}
		}
		else {
			return false;
		}
	}

//	return the next pixel according to the given dx and dy.
	@Override
	public Pixel next() {
		if(!this.hasNext()) {
			throw new NoSuchElementException("Has already reached the end of iterator!");
		}
		if(rowIndex < bound_y) {
			Coordinate c = new Coordinate(columnIndex, rowIndex);
			if(columnIndex >= bound_x) {
				columnIndex = init_x;
				rowIndex += dy;
			}
			else {
				columnIndex += dx;
			}
			return p.getPixel(c);
		}
		else {
			Coordinate c = new Coordinate(columnIndex, rowIndex);
			columnIndex += dx;
			return p.getPixel(c);
		}
	}

}
