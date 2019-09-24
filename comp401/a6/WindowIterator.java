package a6jedi;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class WindowIterator implements Iterator<SubPicture> {
	private Picture p;
	private int w_width;
	private int w_height;
	private int rowIndex = 0;
	private int columnIndex = 0;

//	Constructor
	public WindowIterator(Picture p, int window_width, int window_height) {
		this.p = p;
		w_width = window_width;
		w_height = window_height;
	}

//	check whether there are more pixels in the operator
	@Override
	public boolean hasNext() {
		if(rowIndex < p.getHeight() - w_height) {
			return true;
		}
		else if(rowIndex == p.getHeight() - w_height) {
			if(columnIndex == p.getWidth() - w_width + 1) {
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

//	return the next subpicture according to the given width and height.
	@Override
	public SubPicture next() {
		if(!this.hasNext()) {
			throw new NoSuchElementException("Has already reached the end of iterator!");
		}
		if(rowIndex < p.getHeight() - w_height) {
			Coordinate c = new Coordinate(columnIndex, rowIndex);
			if(columnIndex == p.getWidth() - w_width) {
				columnIndex = 0;
				rowIndex++;
			}
			else {
				columnIndex++;
			}
			return p.extract(c.getX(), c.getY(), w_width, w_height);
		}
		else {
			Coordinate c = new Coordinate(columnIndex, rowIndex);
			columnIndex++;
			return p.extract(c.getX(), c.getY(), w_width, w_height);
		}
	}

}
