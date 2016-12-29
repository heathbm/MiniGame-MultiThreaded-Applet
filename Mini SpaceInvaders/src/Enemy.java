
public class Enemy extends App implements Runnable {
	Thread trd;

	Enemy(String en) {
		trd = new Thread(this, en);
		trd.start();
	}

	@Override
	public void run() {

		for (int i = 0; i < 5; i++) {
			while (App.enemy == true) {

				repaint();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (App.posEnemy == 1)
					App.dirRight = true;
				else if (App.posEnemy == 4)
					App.dirRight = false;

				if (App.dirRight == true) {
					App.Array[0][App.posEnemy] = 9;
					repaint();
					App.posEnemy++;
				} else {
					App.Array[0][App.posEnemy] = 9;
					repaint();
					App.posEnemy--;
				}
			}

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
