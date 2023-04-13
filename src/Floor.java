import java.awt.Color;
import java.awt.Graphics;

public class Floor extends GameObject {
	Floor(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}
	void draw(Graphics g) {
		g.setColor(Color.gray);
        g.fillRect(x, y, width, height);
	}
	void update() {
		super.Update();

	}

}
