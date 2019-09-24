package a7;

import java.util.ArrayList;
import java.util.List;

public class ObservablePictureImpl implements ObservablePicture {

	private Picture p;
	private List<ROIObserver> o = new ArrayList<ROIObserver>();
	private List<Region> r = new ArrayList<Region>();
	private boolean running = true;
	private Region suspended;

	public ObservablePictureImpl(Picture p) {
		this.p = p;
	}

	@Override
	public int getWidth() {
		return p.getWidth();
	}

	@Override
	public int getHeight() {
		return p.getHeight();
	}

	@Override
	public Pixel getPixel(int x, int y) {
		return p.getPixel(x, y);
	}

	@Override
	public Pixel getPixel(Coordinate c) {
		return p.getPixel(c);
	}

	@Override
	public void setPixel(int x, int y, Pixel p) {
		this.p.setPixel(x, y, p);
		this.notifyObservers(x, y);
	}

	@Override
	public void setPixel(Coordinate c, Pixel p) {
		setPixel(c.getX(), c.getY(), p);
	}

	@Override
	public SubPicture extract(int xoff, int yoff, int width, int height) {
		return p.extract(xoff, yoff, width, height);
	}

	@Override
	public SubPicture extract(Coordinate a, Coordinate b) {
		return p.extract(a, b);
	}

	@Override
	public void registerROIObserver(ROIObserver observer, Region r) {
		o.add(observer);
		this.r.add(r);
	}

	@Override
	public void unregisterROIObservers(Region r) {
		for(int i = 0; i < this.r.size(); i++) {
			try {
				this.r.get(i).intersect(r);
				o.remove(i);
				this.r.remove(i);
				i--;
			} catch(NoIntersectionException n) {
			}
		}
	}

	@Override
	public void unregisterROIObserver(ROIObserver observer) {
		while(o.contains(observer)) {
			r.remove(o.indexOf(observer));
			o.remove(o.indexOf(observer));
		}
	}

	@Override
	public ROIObserver[] findROIObservers(Region r) {
		int count = 0;
		int c = 0;
		for(Region i : this.r) {
			try {
				i.intersect(r);
				count++;
			}catch(NoIntersectionException n) {
			}
		}
		ROIObserver[] find = new ROIObserver[count];
		for(Region i : this.r) {
			try {
				i.intersect(r);
				find[c] = o.get(this.r.indexOf(i));
				c++;
			}catch(NoIntersectionException n) {
			}
		}
		return find;
	}

	@Override
	public void suspendObservable() {
		running = false;
	}

	@Override
	public void resumeObservable() {
		running = true;
		for(ROIObserver o : this.o) {
			try {
				Region changed_region = suspended.intersect(r.get(this.o.indexOf(o)));
				o.notify(this, changed_region);
			}catch(NoIntersectionException n) {
			}
		}
		suspended = null;
	}

	private void notifyObservers(int x, int y) {
		if(running) {
			for(Region i : r) {
				if(x <= i.getRight() && x >= i.getLeft() && y <= i.getBottom() && y >= i.getTop()) {
					try {
					o.get(r.indexOf(i)).notify(this, i.intersect(new RegionImpl(new Coordinate(x, y), new Coordinate(x, y))));
					}catch(NoIntersectionException n) {
					}
				}
			}
		}
		else {
			if(suspended == null) {
				suspended = new RegionImpl(new Coordinate(x, y), new Coordinate(x, y));
			}
			else {
				suspended = suspended.union(new RegionImpl(new Coordinate(x, y), new Coordinate(x, y)));
			}
		}
	}
}
