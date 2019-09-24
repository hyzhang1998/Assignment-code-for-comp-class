package a8;

import java.awt.GridLayout;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class FramePuzzleWidget extends JPanel implements MouseListener, KeyListener{
	private PictureView picture_view[][] = new PictureView[5][5];
	private Picture picture;
	private int whitex = 4, whitey = 4;
	private Picture white;

	public FramePuzzleWidget(Picture picture) {

		this.picture = picture;
		white = new PictureImpl(picture.getWidth()/5 + picture.getWidth()%5, picture.getHeight()/5 + picture.getHeight()%5);
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				picture_view[j][i] = new PictureView(dissect(this.picture, i, j));
				add(picture_view[j][i]);
				picture_view[j][i].addMouseListener(this);
			}
		}
		addKeyListener(this);
		setLayout(new GridLayout(5, 5));
		setFocusable(true);
	}

	private ObservablePicture dissect(Picture p, int a, int b) {
		int width, height;
		if(a < 4) {
			height = p.getHeight()/5;
		}
		else {
			height = p.getHeight()/5 + p.getHeight()%5;
		}
		if(b < 4) {
			width = p.getWidth()/5;
		}
		else {
			width = p.getWidth()/5 + p.getWidth()%5;
		}
		Picture region = new PictureImpl(width, height);
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(a == 4 && b == 4) {
					white.setPixel(j, i, new GrayPixel(1));
					region = white;
				}
				else {
					region.setPixel(j, i, p.getPixel(b*(p.getWidth()/5)+j, a*(p.getHeight()/5)+i));
				}
			}
		}
		return region.createObservable();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	private void left() {
		picture_view[whitex][whitey].setPicture(picture_view[whitex+1][whitey].getPicture());
		picture_view[whitex+1][whitey].setPicture(white.createObservable());
		whitex++;
	}

	private void up() {
		picture_view[whitex][whitey].setPicture(picture_view[whitex][whitey+1].getPicture());
		picture_view[whitex][whitey+1].setPicture(white.createObservable());
		whitey++;
	}

	private void right() {
		picture_view[whitex][whitey].setPicture(picture_view[whitex-1][whitey].getPicture());
		picture_view[whitex-1][whitey].setPicture(white.createObservable());
		whitex--;
	}

	private void down() {
		picture_view[whitex][whitey].setPicture(picture_view[whitex][whitey-1].getPicture());
		picture_view[whitex][whitey-1].setPicture(white.createObservable());
		whitey--;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {

		case 37: if(whitex < 4) {
			left();
		}
		break;
		case 38: if(whitey < 4) {
			up();
		}
		break;
		case 39: if(whitex > 0) {
			right();
		}
		break;
		case 40: if(whitey > 0) {
			down();
		}
		break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(whitex == 4) {
			if(whitey == 4) {
				if(e.getSource() == picture_view[whitex-1][whitey]) {
					right();
				}
				else if(e.getSource() == picture_view[whitex][whitey-1]) {
					down();
				}
			}
			else if(whitey == 0){
				if(e.getSource() == picture_view[whitex][whitey+1]) {
					up();
				}
				else if(e.getSource() == picture_view[whitex-1][whitey]) {
					right();
				}
			}
			else {
				if(e.getSource() == picture_view[whitex][whitey+1]) {
					up();
				}
				else if(e.getSource() == picture_view[whitex-1][whitey]) {
					right();
				}
				else if(e.getSource() == picture_view[whitex][whitey-1]) {
					down();
				}
			}
		}
		else if (whitex == 0) {
			if(whitey == 4) {
				if(e.getSource() == picture_view[whitex+1][whitey]) {
					left();
				}
				else if(e.getSource() == picture_view[whitex][whitey-1]) {
					down();
				}
			}
			else if(whitey == 0) {
				if(e.getSource() == picture_view[whitex+1][whitey]) {
					left();
				}
				else if(e.getSource() == picture_view[whitex][whitey+1]) {
					up();
				}
			}
			else {
				if(e.getSource() == picture_view[whitex+1][whitey]) {
					left();
				}
				else if(e.getSource() == picture_view[whitex][whitey-1]) {
					down();
				}
				else if(e.getSource() == picture_view[whitex][whitey+1]) {
					up();
				}
			}
		}
		else {
			if(whitey == 4) {
				if(e.getSource() == picture_view[whitex+1][whitey]) {
					left();
				}
				else if(e.getSource() == picture_view[whitex][whitey-1]) {
					down();
				}
				else if(e.getSource() == picture_view[whitex-1][whitey]) {
					right();
				}
			}
			else if(whitey == 0) {
				if(e.getSource() == picture_view[whitex+1][whitey]) {
					left();
				}
				else if(e.getSource() == picture_view[whitex][whitey+1]) {
					up();
				}
				else if(e.getSource() == picture_view[whitex-1][whitey]) {
					right();
				}
			}
			else {
				if(e.getSource() == picture_view[whitex+1][whitey]) {
					left();
				}
				else if(e.getSource() == picture_view[whitex][whitey+1]) {
					up();
				}
				else if(e.getSource() == picture_view[whitex-1][whitey]) {
					right();
				}
				else if(e.getSource() == picture_view[whitex][whitey-1]) {
					down();
				}
			}
		}
		requestFocus();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
