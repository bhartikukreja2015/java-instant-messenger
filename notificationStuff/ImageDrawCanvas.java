package notificationStuff;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;

public class ImageDrawCanvas extends Canvas {
	private static final long serialVersionUID = 1L;

	protected Image toDraw;
	
	public ImageDrawCanvas() { }
	
	public ImageDrawCanvas(Image theImage) {
		//super();
		toDraw = theImage;
	}
	
	
	public void paint(Graphics g) {
		System.out.println("Drawing...");
		g.drawImage(toDraw, 0, 0, this.getWidth(), this.getHeight(), null);
	}
}
