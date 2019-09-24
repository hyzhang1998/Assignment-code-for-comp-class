package a8;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ImageAdjusterWidget extends JPanel implements ChangeListener{

	private JSlider blur;
	private JSlider saturation;
	private JSlider brightness;
	private JPanel bottom = new JPanel();
	private JPanel labels = new JPanel();
	private JPanel sliders = new JPanel();
	private Picture picture;
	private PictureView picture_view;
	Picture temp;

	public ImageAdjusterWidget(Picture picture) {

		setLayout(new BorderLayout());
		bottom.setLayout(new BorderLayout());
		labels.setLayout(new GridLayout(3, 1));
		sliders.setLayout(new GridLayout(3, 1));

		blur = new JSlider(0, 5, 0);
		saturation = new JSlider(-100, 100, 0);
		brightness = new JSlider(-100, 100, 0);

		picture_view = new PictureView(picture.createObservable());
		this.picture = picture;
		add(picture_view, BorderLayout.CENTER);
		add(bottom, BorderLayout.SOUTH);
		bottom.add(labels, BorderLayout.WEST);
		bottom.add(sliders, BorderLayout.CENTER);
		labels.add(new JLabel("Blur:"));
		labels.add(new JLabel("Saturation:"));
		labels.add(new JLabel("Brightness"));
		sliders.add(blur);
		sliders.add(saturation);
		sliders.add(brightness);

		blur.setMajorTickSpacing(1);
		blur.setPaintTicks(true);
		blur.setPaintLabels(true);
		saturation.setPaintTicks(true);
		saturation.setMajorTickSpacing(25);
		saturation.setPaintLabels(true);
		brightness.setPaintTicks(true);
		brightness.setPaintLabels(true);
		brightness.setMajorTickSpacing(25);

		blur.addChangeListener(this);
		saturation.addChangeListener(this);
		brightness.addChangeListener(this);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		Picture p1 = blur(picture, blur.getValue());
		Picture p2 = saturation(p1, saturation.getValue());
		Picture p3 = brightness(p2, brightness.getValue());
		picture_view.setPicture(p3.createObservable());
	}

	private Picture blur(Picture p, int b) {
		Picture newp = new PictureImpl(p.getWidth(), p.getHeight());
		double tred, tgreen, tblue;
		int count;
		for(int i = 0; i < p.getHeight(); i++) {
			for(int j = 0; j < p.getWidth(); j++) {
				tred = 0; tgreen = 0; tblue = 0; count = 0;
				for(int k = 0-b; k <= b; k++) {
					for(int l = 0-b; l <= b; l++) {
						try {
							tred += p.getPixel(j+l, i+k).getRed();
							tgreen += p.getPixel(j+l, i+k).getGreen();
							tblue += p.getPixel(j+l, i+k).getBlue();
							count++;
						} catch (Exception e) {
						}
					}
				}

				newp.setPixel(j, i, new ColorPixel(tred/count, tgreen/count, tblue/count));
			}
		}
		return newp;
	}

	private Picture saturation(Picture p, int s) {
		Picture newp = new PictureImpl(p.getWidth(), p.getHeight());
		double ired, igreen, iblue;
		double nred = 0, ngreen = 0, nblue = 0;
		for(int i = 0; i < p.getHeight(); i++) {
			for(int j = 0; j < p.getWidth(); j++) {
				double max;
				ired = p.getPixel(j, i).getRed();
				igreen = p.getPixel(j, i).getGreen();
				iblue = p.getPixel(j, i).getBlue();
				if(ired > igreen) {
					if(ired > iblue) {
						max = ired;
					}
					else {
						max = iblue;
					}
				}
				else {
					if(igreen > iblue) {
						max = igreen;
					}
					else {
						max = iblue;
					}
				}
				if(s < 0) {
					nred = ired * (1.0 + (s / 100.0) ) - (p.getPixel(j, i).getIntensity() * s / 100.0);
					ngreen = igreen * (1.0 + (s / 100.0) ) - (p.getPixel(j, i).getIntensity() * s / 100.0);
					nblue = iblue * (1.0 + (s / 100.0) ) - (p.getPixel(j, i).getIntensity() * s / 100.0);
				}
				else {
					try {
						nred = ired * ((max + ((1.0 - max) * (s / 100.0))) / max);
						ngreen = igreen * ((max + ((1.0 - max) * (s / 100.0))) / max);
						nblue = iblue * ((max + ((1.0 - max) * (s / 100.0))) / max);
					} catch (Exception e) {
					}
				}
				newp.setPixel(j, i, new ColorPixel(nred, ngreen, nblue));
			}
		}
		return newp;
	}

	private Picture brightness(Picture p, double b) {
		Picture newp = new PictureImpl(p.getWidth(), p.getHeight());
		double ired, igreen, iblue;
		for(int i = 0; i < p.getHeight(); i++) {
			for(int j = 0; j < p.getWidth(); j++) {
				ired = p.getPixel(j, i).getRed();
				igreen = p.getPixel(j, i).getGreen();
				iblue = p.getPixel(j, i).getBlue();
				if(b < 0) {
					newp.setPixel(j, i, new ColorPixel(ired*(1+b/100), igreen*(1+b/100), iblue*(1+b/100)));
				}
				else {
					newp.setPixel(j, i, new ColorPixel(ired+(1-ired)*(b/100), igreen+(1-igreen)*(b/100), iblue+(1-iblue)*(b/100)));
				}
			}
		}
		return newp;
	}

}
