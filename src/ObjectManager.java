import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	Basket basket;
	int score = 0;
	int bombCount = 0;
	int count = 1;
	int fast = 1;
	int timer = 0;
	int timer2 = 0;
	int timer3 = 0;
	int bombProb = 7;
	ArrayList<Apple> apples = new ArrayList<Apple>();
	ArrayList<Bomb> bombs = new ArrayList<Bomb>();
	ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
	ArrayList<PowerUp2> powerUps2 = new ArrayList<PowerUp2>();
	ArrayList<PowerUp3> powerUps3 = new ArrayList<PowerUp3>();
	Random random = new Random();
	boolean collect;
	boolean powerUp = false;
	boolean sizeUp = false;
	boolean shieldUp = false;

	void addApple() {
		int spawnLocation = random.nextInt(AppleFall.WIDTH);
		if (spawnLocation > AppleFall.WIDTH - 60) {
			spawnLocation = AppleFall.WIDTH - 60;
		} else if (spawnLocation < 50) {
			spawnLocation = 50;
		}
		apples.add(new Apple(spawnLocation, 0, 50, 50));
	}

	void addBomb() {
		int spawnLocation = random.nextInt(AppleFall.WIDTH);
		if (spawnLocation > AppleFall.WIDTH - 60) {
			spawnLocation = AppleFall.WIDTH - 60;
		} else if (spawnLocation < 50) {
			spawnLocation = 50;
		}
		bombs.add(new Bomb(spawnLocation, 0, 50, 50));
	}

	void addPowerUp() {
		int spawnLocation = random.nextInt(AppleFall.WIDTH);
		if (spawnLocation > AppleFall.WIDTH - 60) {
			spawnLocation = AppleFall.WIDTH - 60;
		} else if (spawnLocation < 50) {
			spawnLocation = 50;
		}
		powerUps.add(new PowerUp(spawnLocation, 0, 50, 50));
	}

	void addPowerUp2() {
		int spawnLocation = random.nextInt(AppleFall.WIDTH);
		if (spawnLocation > AppleFall.WIDTH - 60) {
			spawnLocation = AppleFall.WIDTH - 60;
		} else if (spawnLocation < 50) {
			spawnLocation = 50;
		}
		powerUps2.add(new PowerUp2(spawnLocation, 0, 50, 50));
	}

	void addPowerUp3() {
		int spawnLocation = random.nextInt(AppleFall.WIDTH);
		if (spawnLocation > AppleFall.WIDTH - 60) {
			spawnLocation = AppleFall.WIDTH - 60;
		} else if (spawnLocation < 50) {
			spawnLocation = 50;
		}
		powerUps3.add(new PowerUp3(spawnLocation, 0, 50, 50));
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
				if (apples.get(i).y > 740) {
					apples.get(i).isActive = false;
					basket.isActive = false;
				}
			}
			for (int i = 0; i < bombs.size(); i++) {
				bombs.get(i).update();
				if (bombs.get(i).y > 740) {
					bombs.get(i).isActive = false;
				}
			}
			for (int i = 0; i < powerUps.size(); i++) {
				powerUps.get(i).update();
				if (powerUps.get(i).y > 740) {
					powerUps.get(i).isActive = false;
				}
			}
			for (int i = 0; i < powerUps2.size(); i++) {
				powerUps2.get(i).update();
				if (powerUps2.get(i).y > 740) {
					powerUps2.get(i).isActive = false;
				}
			}
			for (int i = 0; i < powerUps3.size(); i++) {
				powerUps3.get(i).update();
				if (powerUps3.get(i).y > 740) {
					powerUps3.get(i).isActive = false;
				}
			}
			basket.Update();
			purgeObjects();
			checkCollision();
		}
	}

	void draw(Graphics g) {
		basket.draw(g);
		for (int i = 0; i < bombs.size(); i++) {
			bombs.get(i).draw(g);
		}
		for (int i = 0; i < apples.size(); i++) {
			apples.get(i).draw(g);
		}
		for (int i = 0; i < powerUps.size(); i++) {
			powerUps.get(i).draw(g);
		}
		for (int i = 0; i < powerUps2.size(); i++) {
			powerUps2.get(i).draw(g);
		}
		for (int i = 0; i < powerUps3.size(); i++) {
			powerUps3.get(i).draw(g);
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
		for (int i = 0; i < bombs.size(); i++) {
			if (bombs.get(i).isActive == false) {
				bombs.remove(i);
			}

		}
		for (int i = 0; i < powerUps.size(); i++) {
			if (powerUps.get(i).isActive == false) {
				powerUps.remove(i);
			}

		}
		for (int i = 0; i < powerUps2.size(); i++) {
			if (powerUps2.get(i).isActive == false) {
				powerUps2.remove(i);
			}

		}
		for (int i = 0; i < powerUps3.size(); i++) {
			if (powerUps3.get(i).isActive == false) {
				powerUps3.remove(i);
			}

		}
	}

	void clearObjects() {
		System.out.println("objects cleared");
		while (apples.size() > 0) {
			apples.remove(0);
		}
		while (bombs.size() > 0) {
			bombs.remove(0);
		}
		while (powerUps.size() > 0) {
			powerUps.remove(0);
		}
		while (powerUps2.size() > 0) {
			powerUps2.remove(0);
		}
		while (powerUps3.size() > 0) {
			powerUps3.remove(0);
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
				
				
				
				if (timer + 5 == score) {
					powerUp = false;
					System.out.println("PowerUp ran out");
					fast = 1;
				}
				if (timer2 + 5 == score) {
					sizeUp = false;
					System.out.println("SizeUp ran out");
				}

			}

		}
		for (int i = 0; i < bombs.size(); i++) {
			if (bombs.get(i).collisionBox.intersects(basket.collisionBox)) {
				bombs.get(i).isActive = false;
				bombCount++;
				System.out.println("bombCount = " + bombCount);
				if (shieldUp == false) {
					purgeObjects();
					System.out.println("bomb hit");
					basket.isActive = false;
				}
				if (timer3 + 5 == bombCount) {
					shieldUp = false;
					System.out.println("SheildUp ran out");
				}
			}

		}

		for (int i = 0; i < powerUps.size(); i++) {
			if (powerUps.get(i).collisionBox.intersects(basket.collisionBox)) {
				powerUps.get(i).isActive = false;
				purgeObjects();
				System.out.println("collected power up");
				powerUp = true;
				timer = score;
				fast++;
			}

		}
		for (int i = 0; i < powerUps2.size(); i++) {
			if (powerUps2.get(i).collisionBox.intersects(basket.collisionBox)) {
				powerUps2.get(i).isActive = false;
				purgeObjects();
				System.out.println("collected size up");
				sizeUp = true;
				timer2 = score;

			}

		}
		for (int i = 0; i < powerUps3.size(); i++) {
			if (powerUps3.get(i).collisionBox.intersects(basket.collisionBox)) {
				powerUps3.get(i).isActive = false;
				purgeObjects();
				System.out.println("collected power up");
				shieldUp = true;
				timer3 = bombCount;

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
		int randomInt = random.nextInt(bombProb);
		if (randomInt == 0) {
			addBomb();
			System.out.println("Bomb added");

		}
		randomInt = random.nextInt(15);

		if (randomInt == 1) {
			addPowerUp();
			System.out.println("powerUp added");

		}
		
		randomInt = random.nextInt(20);

		if (randomInt == 10) {
			addPowerUp2();
			System.out.println("sizeUp added");

		}
		randomInt = random.nextInt(25);

		if (randomInt == 8) {
			addPowerUp3();
			System.out.println("ShieldUp added");

		}
		count++;
		System.out.println("count=" + count);

	}

}
