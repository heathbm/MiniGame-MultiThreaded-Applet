public class Shooting extends App implements Runnable {
	Thread trd;

	Shooting(String shoot) {
		trd = new Thread(this, shoot);
		trd.start();
	}

	@Override
	public void run() {

		App.BulletCol = App.posPlayer;
		App.BulletRow = 5;
		App.shooting = true;
		for (int i = 0; i < 5; i++) {

			repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			App.BulletRow--;
		}

		App.shooting = false;

		if (App.BulletCol == App.posEnemy) {
			App.enemy = false;
			App.score += 100;
			App.remainder--;
			if (App.score == 500) {
				App.game = false;
				repaint();
			}
		}
		repaint();
	}
}
