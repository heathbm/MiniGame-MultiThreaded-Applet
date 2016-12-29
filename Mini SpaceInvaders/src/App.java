// an AWT-based / Applet mini space invaders game with multithreading
import java.awt.*;
import java.util.Scanner;
import java.applet.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

/*
<applet code="AppletSkel" width=300 height=100>
</applet>
*/

public class App extends Applet implements KeyListener, Runnable {
	Thread t;
	private static final long serialVersionUID = 1L;
	static int posEnemy = 0;
	static int posPlayer = 0;
	static boolean game = true;
	static int BulletCol, BulletRow;
	static int Array[][] = new int[7][7];
	static int score = 0;
	static boolean enemy = true;
	static boolean dirRight = true;
	static boolean shooting = false;
	public static int remainder = 5;

	// called first
	public void init() {

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_RIGHT) {
					posPlayer++;

					player();
				}
			}
		});

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_LEFT) {
					posPlayer--;
					player();

				}
			}
		});

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_SPACE) {
					shoot();

				}
			}
		});
	}

	// called second after init() also called whenever the applet is restarted.
	public void start() {

		t = new Thread(this);
		t.start();
	}

	public void player() {

		if (posPlayer == -1) {
			posPlayer = 6;
		} else if (posPlayer > 6) {
			posPlayer = 0;
		}

		Array[6][posPlayer] = 8;
		repaint();

	}

	private void shoot() {

		String shoot = "fire";
		Shooting shooting = new Shooting(shoot);

	}

	public void run() {

		for (int i = 0; i < 5; i++) {
			enemy = true;
			while (enemy == true) {

				repaint();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (posEnemy == 0)
					dirRight = true;
				else if (posEnemy == 6)
					dirRight = false;

				if (dirRight == true) {
					Array[0][posEnemy] = 9;
					repaint();
					posEnemy++;
				} else {
					Array[0][posEnemy] = 9;
					repaint();
					posEnemy--;
				}
				enemy = getenemyState();
			}

			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean getenemyState() {
		return enemy;
	}

	// called when the applet is stopped
	public void stop() {
		// suspends execution
	}

	// called when the applet is terminated. This is the last method executed.
	public void destroy() {
		// perform shutdown activities
	}

	// called when an AWT-based appley's window must be restored.
	public void paint(Graphics g) {
		this.setSize(290,210);
		if (App.game == true) {
			g.drawString("/^|^\\", posPlayer * 40, 6 * 25);
			g.drawString("Score: " + score, 10, 7 * 25);
			g.drawString("Remaining: " + remainder + "/5", 180, 7 * 25);

			if (enemy == true) {
				g.drawString("∆|•|∆", posEnemy * 40, 20);
			}

			if (shooting == true) {
				g.drawString("X", BulletCol * 40, BulletRow * 25);
				g.drawString("shooting!", 10, 8 * 25);
			}
		} else if (App.game == false) {
			g.drawString("Thanks for playing!!!", 90, 3 * 25);
			g.drawString("  Your score: 500", 90, 4 * 25);
			g.drawString("    Made by HBM", 90, 5 * 25);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// necessary
	}

	public void keyPressed(KeyEvent e) {
		// necessary
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// necessary

	}

	public static void shutdown() {
		// Turn off app here

	}
}