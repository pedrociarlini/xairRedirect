package br.com.pedrociarlini.xairedirect.ui.fields;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JSlider;
import javax.swing.plaf.basic.BasicSliderUI;

import org.springframework.util.ResourceUtils;

public class FaderSliderUi extends BasicSliderUI {

	private static BufferedImage thumbImg;
	static {
		try {
			thumbImg = ImageIO.read(ResourceUtils.getFile("classpath:images/thumb.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public FaderSliderUi(JSlider b) {
		super(b);
	}

	@Override
	protected void calculateThumbSize() {
		// TODO Auto-generated method stub
		super.calculateThumbSize();
		thumbRect.width = thumbImg.getWidth();
		thumbRect.height = thumbImg.getHeight();
	}

	@Override
	public void paintThumb(Graphics g) {
		g.drawImage(thumbImg, thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height, null);
//		super.paintThumb(g);
	}

}
