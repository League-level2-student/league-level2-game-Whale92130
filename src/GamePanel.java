import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Timer frameDraw;
	Timer appleSpawn;
	Font titleFont;
	Font textFont;
	Font smallFont;
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	boolean showRules = false;
	boolean harderGame = true;
	boolean isBasketBig = false;
	boolean isShieldUp = false;
	int off = 0;
	int c = 1;
	int count = 0;
	Graphics gg;
	Basket basket = new Basket(250, 650, 100, 100);
	Shield shield = new Shield(250, 1000, 100, 100);
	ObjectManager obManage = new ObjectManager(basket);

	@Override
	public void paintComponent(Graphics g) {
		gg = g;
		if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == GAME) {
			drawGameState(g);
		} else if (currentState == END) {
			drawEndState(g);
		}
	}

	GamePanel() {
		this.titleFont = new Font("Arial", Font.PLAIN, 48);
		this.textFont = new Font("Arial", Font.PLAIN, 28);
		this.smallFont = new Font("Arial", Font.PLAIN, 18);

		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
	}

	private void drawEndState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, AppleFall.WIDTH, AppleFall.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("Game Over", 175, 200);
		g.setFont(textFont);
		g.drawString("Press ENTER to go back to menu", 75, 400);
		String score1 = String.valueOf(obManage.getScore());
		g.drawString("Your Score Was: " + score1, 10, 30);
		obManage.powerUp = false;
	}

	void sizeDown() {
		basket.sizeDown();
	}

	private void drawGameState(Graphics g) {

		g.setColor(Color.CYAN);
		g.fillRect(0, 0, AppleFall.WIDTH, AppleFall.HEIGHT);

		g.setColor(Color.green);
		g.fillOval(-100, -50, 400, 200);
		g.fillOval(300, -50, 400, 200);

		g.setColor(Color.getHSBColor(200, 155, 200));
		g.fillRect(175, 0, 250, AppleFall.HEIGHT);

		g.setColor(Color.GREEN);
		g.fillRect(0, 700, AppleFall.WIDTH, 100);

		g.setColor(Color.black);
		g.fillOval(225, 50, 150, 150);
		obManage.draw(g);

		if (obManage.sizeUp == true) {
			if (count == 0) {
				basket.sizeUp();
				isBasketBig = true;
			}
			count = 1;
		}
		if (isShieldUp == true) {
			if (obManage.sizeUp == false) {
				basket.sizeDown();
				isShieldUp = false;
				count = 0;
			}
		}

		if (harderGame == true) {
			while (obManage.collectedApple()) {
				if (appleSpawn.getDelay() > 500) {
					appleSpawn.stop();
					appleSpawn = new Timer(2000 - obManage.getScore() * 30, obManage);
					appleSpawn.restart();
					obManage.setfalse();
				} else {
					stopHard();

				}
				System.out.println(appleSpawn.getDelay());
			}
		} else {

			if (c == 1) {
				System.out.println("stopped hard mode");
				c = 0;
			}
		}

		if (basket.isActive == false) {
			boolean hi = true;
			while (hi == true) {
				hi = false;
				stopGame();
				currentState = END;
			}
		}

	}

	void stopHard() {
		harderGame = false;
		obManage.setfalse();
		appleSpawn = new Timer(500, obManage);
	}

	private void drawMenuState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, AppleFall.WIDTH, AppleFall.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("Apple Fall", 200, 200);
		g.setFont(textFont);
		g.drawString("Press ENTER to start", 150, 400);
		g.drawString("Press SPACE for instructions", 120, 500);

		if (showRules == true) {
			g.setFont(smallFont);
			g.drawString("1. Use arrows to move", 120, 550);
			g.drawString("2. Collect Apples", 120, 570);
			g.drawString("3. If one touches the ground you lose", 120, 590);
			g.drawString("4. Watch out for bombs", 120, 610);
			g.drawString("5. Collect Power Ups on the way", 120, 630);
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == END) {
				currentState = MENU;
				;
			} else {
				currentState++;
				if (currentState == GAME) {
					startGame();
				} else if (currentState == END) {
					stopGame();
				}

			}
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (currentState == MENU) {
				showRules = true;
			}
		}

		if (currentState == GAME) {

			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				if (basket.x > 0) {
					if (obManage.powerUp == true) {
						basket.fastLeft(obManage.fast);
						System.out.println("fast");
					} else {
						basket.left();
					}

				}
			}

			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if (basket.x >= AppleFall.WIDTH - 100) {

				} else {
					if (obManage.powerUp == true) {
						basket.fastRight(obManage.fast);
						System.out.println("fast");
					} else {
						basket.right();
					}
				}
			}
		}
	}

	private void startGame() {
		appleSpawn = new Timer(2000, obManage);
		appleSpawn.start();
		basket.isActive = true;
		obManage.score = 0;
		basket.y = 650;
		basket.x = 250;
	}

	private void stopGame() {
		appleSpawn.stop();
		System.out.println("stop");
		basket.x = 250;
		basket.y = 650;
		if (off < 2) {
			obManage.clearObjects();
			obManage.sizeUp = false;
			obManage.powerUp = false;
			sizeDown();
		}
		off++;
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (currentState == MENU) {
			updateMenuState();
		} else if (currentState == GAME) {
			updateGameState();

		} else if (currentState == END) {

			updateEndState();
			if (off == 0) {
				obManage.clearObjects();
			}
			off = 1;
		}
		repaint();
	}

	private void updateEndState() {

	}

	private void updateGameState() {
		obManage.update();
	}

	private void updateMenuState() {

	}

}
