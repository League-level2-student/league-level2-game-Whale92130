import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Shield extends GameObject {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;

	Shield(int x, int y, int width, int height) {
		super(x, y, width, height);
		if (needImage) {
			loadImage("Shield.png");
		}
	}

	void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}
	}

	public void sizeUp() {
		width = 200;
		height = 200;
		y = y - 100;
		x = x - 100;
	}

	public void sizeDown() {
		width = 100;
		height = 100;
		y = 100;
		x = x + 100;
	}
	public void appear() {
		y=750;
	}


	void loadImage(String imageFile) {
		if (needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				gotImage = true;
			} catch (Exception e) {

			}
			needImage = false;
		}
	}

	boolean getActive() {
		return isActive;
	}
}
