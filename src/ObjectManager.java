import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	Basket basket;
	int score = 0;
	ArrayList<Apple> apples = new ArrayList<Apple>();
	Random random = new Random();

	void addApple() {
		apples.add(new Apple(random.nextInt(AppleFall.WIDTH), 0, 50, 50));
	}

	ObjectManager(Basket bask) {
		basket = bask;
	}

	void update() {
		if (basket.isActive == true) {
			for (int i = 0; i < apples.size(); i++) {
				apples.get(i).update();
				if (apples.get(i).y > AppleFall.HEIGHT) {
					apples.get(i).isActive = false;
				}
			}
			basket.Update();
			purgeObjects();

		}
	}

	void draw(Graphics g) {
		basket.draw(g);
		for (int i = 0; i < apples.size(); i++) {
			apples.get(i).draw(g);
		}

	}

	void purgeObjects() {
		for (int i = 0; i < apples.size(); i++) {
			if (apples.get(i).isActive == false) {
				apples.remove(i);
			}

		}
	}

	void clearApples() {
		while (apples.size() > 0) {
				apples.remove(0);
			}
	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("apples added");
		addApple();
	}

}
