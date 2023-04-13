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
	Basket basket = new Basket(250, 650, 100, 100);
	ObjectManager obManage = new ObjectManager(basket);

	@Override
	public void paintComponent(Graphics g) {
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
		g.setFont(smallFont);
		g.drawString("an apple touched the ground", 175, 240);
		g.setFont(textFont);
		g.drawString("Press ENTER to go back to menu", 75, 400);
		String score1 = String.valueOf(obManage.getScore());
		g.drawString("Your Score Was: " + score1, 10, 30);
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
		
		appleSpawn = new Timer(2000-obManage.getScore()*100, obManage);
		appleSpawn.start();
		
		if (basket.isActive == false) {
			boolean hi = true;
			while (hi == true) {
			hi = false;
			stopGame();
			currentState = END;
			}
		}
	}

	private void drawMenuState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, AppleFall.WIDTH, AppleFall.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("Apple Fall", 200, 200);
		g.setFont(textFont);
		g.drawString("Press ENTER to start", 150, 400);
		g.drawString("Press SPACE for instructions", 120, 550);
		if (showRules == true) {
			g.setFont(smallFont);
			g.drawString("1. Use arrows to move", 120, 600);
			g.drawString("2. Collect Apples", 120, 620);
			g.drawString("3. If one touches the ground you lose", 120, 640);
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
				System.out.println("LEFT");
				if (basket.x > 0) {
					basket.left();
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				System.out.println("RIGHT");
				if (basket.x >= AppleFall.WIDTH-50) {

				} else {
					basket.right();
				}
			}
		}
	}

	private void startGame() {
		appleSpawn = new Timer(2000, obManage);
		appleSpawn.start();
		basket.isActive = true;
		obManage.score =0;
	}

	private void stopGame() {
		appleSpawn.stop();
		System.out.println("stop");
		basket.x = 250;
		basket.y = 650;
		obManage.clearApples();
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
