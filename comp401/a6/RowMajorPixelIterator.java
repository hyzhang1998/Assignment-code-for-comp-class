package a6jedi;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RowMajorPixelIterator implements Iterator<Pixel>{
	private Picture p;
	private int rowIndex = 0;
	private int columnIndex = 0;

//	Constructor
	public RowMajorPixelIterator(Picture p) {
		this.p = p;
	}

//	check whether there are more pixels in the operator
	@Override
	public boolean hasNext() {
		if(rowIndex < p.getHeight() - 1) {
			return true;
		}
		else if(rowIndex == p.getHeight() - 1) {
			if(columnIndex == p.getWidth()) {
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

//	return the next pixel of the picture.
	@Override
	public Pixel next() {
		if(!this.hasNext()) {
			throw new NoSuchElementException("Has already reached the end of iterator!");
		}
		if(rowIndex < p.getHeight() - 1) {
			Coordinate c = new Coordinate(columnIndex, rowIndex);
			if(columnIndex == p.getWidth() - 1) {
				columnIndex = 0;
				rowIndex++;
			}
			else {
				columnIndex++;
			}
			return p.getPixel(c);
		}
		else {
			Coordinate c = new Coordinate(columnIndex, rowIndex);
			columnIndex++;
			return p.getPixel(c);
		}
	}



}
