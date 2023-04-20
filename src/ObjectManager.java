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
	boolean collect;
	void addApple() {
		int spawnLocation = random.nextInt(AppleFall.WIDTH);
		if (spawnLocation > AppleFall.WIDTH - 60) {
			spawnLocation = AppleFall.WIDTH - 60;
		}
		else if (spawnLocation < 50) {
			spawnLocation = 50;
		}
		apples.add(new Apple(spawnLocation, 0, 50, 50));
	}

	ObjectManager(Basket bask) {
		basket = bask;
	}
	int getScore() {
		return score;
	}

	void update() {
		if (basket.isActive == true) {
			for (int i = 0; i < apples.size(); i++) {
				apples.get(i).update();
				if (apples.get(i).y > 660) {
					apples.get(i).isActive = false;
					basket.isActive = false;
				}
			}
			basket.Update();
			purgeObjects();
			checkCollision();
		}
	}

	void draw(Graphics g) {
		basket.draw(g);
		for (int i = 0; i < apples.size(); i++) {
			apples.get(i).draw(g);
		}
		String score1 = String.valueOf(getScore());
		g.setColor(Color.BLACK);
		g.drawString("Score: " + score1, 10, 20);
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
	
	void checkCollision() {
		for (int i = 0; i < apples.size(); i++) {
			if (apples.get(i).collisionBox.intersects(basket.collisionBox)) {
				apples.get(i).isActive = false;
				System.out.println("apple collected");
				score++;
				purgeObjects();
				collect = true;
				
			}
			
		}
	}
	
	boolean collectedApple() {
		
		return collect;
	}
	
	void setfalse() {
		collect = false;
	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("apples added");
		addApple();
	}

}
