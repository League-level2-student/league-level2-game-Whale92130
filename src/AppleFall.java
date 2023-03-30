import javax.swing.JFrame;

public class AppleFall {
	
	JFrame gameFrame;
	GamePanel panel;
	public static final int WIDTH = 600;
	public static final int HEIGHT = 800;		
	
	public static void main(String[] args) {
		AppleFall game = new AppleFall();
		game.setup();
	}
	AppleFall() {
		this.gameFrame = new JFrame();
		this.panel = new GamePanel();
	}
	void setup() {
		gameFrame.add(panel);
		gameFrame.setSize(WIDTH, HEIGHT);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
		gameFrame.addKeyListener(panel);
	}
}
