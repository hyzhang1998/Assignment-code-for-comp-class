package a6jedi;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TileIterator implements Iterator<SubPicture> {
	private Picture p;
	private int rowIndex = 0;
	private int columnIndex = 0;
	private int t_width;
	private int t_height;
	private int bound_y;
	private int bound_x;

	public TileIterator(Picture p, int tile_width, int tile_height) {
		this.p = p;
		t_width = tile_width;
		t_height = tile_height;
		bound_x = tile_width*(p.getWidth()/tile_width - 1);
		bound_y = tile_height*(p.getHeight()/tile_height - 1);
	}

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

	@Override
	public SubPicture next() {
		if(!this.hasNext()) {
			throw new NoSuchElementException("Has already reached the end of iterator!");
		}
		if(rowIndex < bound_y) {
			Coordinate c = new Coordinate(columnIndex, rowIndex);
			if(columnIndex >= bound_x) {
				columnIndex = 0;
				rowIndex += t_height;
			}
			else {
				columnIndex += t_width;
			}
			return p.extract(c.getX(), c.getY(), t_width, t_height);
		}
		else {
			Coordinate c = new Coordinate(columnIndex, rowIndex);
			columnIndex += t_width;
			return p.extract(c.getX(), c.getY(), t_width, t_height);
		}
	}

}
