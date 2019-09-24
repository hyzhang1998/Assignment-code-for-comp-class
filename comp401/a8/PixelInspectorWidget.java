package a8;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PixelInspectorWidget extends JPanel implements MouseListener{
	
	private PictureView picture_view;
	private JLabel pixelX = new JLabel("X: ");
	private JLabel pixelY = new JLabel("Y: ");
	private JLabel pixelR = new JLabel("Red: ");
	private JLabel pixelG = new JLabel("Green: ");
	private JLabel pixelB = new JLabel("Blue: ");
	private JLabel pixelI = new JLabel("Brightness: ");
	private JPanel panel = new JPanel();
	
	public PixelInspectorWidget(Picture picture) {
		setLayout(new BorderLayout());
		
		picture_view = new PictureView(picture.createObservable());
		picture_view.addMouseListener(this);
		add(picture_view, BorderLayout.CENTER);
		add(panel, BorderLayout.WEST);
		
		panel.setLayout(new GridLayout(6, 0));
		panel.add(pixelX);
		panel.add(pixelY);
		panel.add(pixelR);
		panel.add(pixelG);
		panel.add(pixelB);
		panel.add(pixelI);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		updatePanel(e.getX(), e.getY());
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
	
	private void updatePanel(int x, int y) {
		pixelX.setText("X: " + x);
		pixelY.setText("Y: " + y);
		pixelR.setText("Red: " + ((int)(picture_view.getPicture().getPixel(x, y).getRed()*100+0.5))/100.0);
		pixelG.setText("Green: " + ((int)(picture_view.getPicture().getPixel(x, y).getGreen()*100+0.5))/100.0);
		pixelB.setText("Blue: " + ((int)(picture_view.getPicture().getPixel(x, y).getBlue()*100+0.5))/100.0);
		pixelI.setText("Brightness: " + ((int)(picture_view.getPicture().getPixel(x, y).getIntensity()*100+0.5))/100.0);
	}

}
