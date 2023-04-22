import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Basket extends GameObject {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	Basket(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 1;
		if (needImage) {
		    loadImage ("basket.png");
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
		width=200;
		height = 200;
		y=y-100;
		x=x-100;
	}
	public void sizeDown() {
		width=100;
		height = 100;
		y=y+100;
		x=x+100;
	}
	public void right() {
		for (int i = 0; i<10; i++) {
		x += speed;
		
		}
	}

	public void left() {
		for (int i = 0; i<10; i++) {
		x -= speed;
		}
	}
	public void fastRight(int fast) {
		for (int i = 0; i<10; i++) {
		x += speed*fast;
		}
		
	}

	public void fastLeft(int fast) {
		for (int i = 0; i<10; i++) {
		x -= speed*fast;
		}
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
