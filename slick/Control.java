package slick;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class Control extends Thread {

	private Projet2a game;
	private GameContainer gc;

	public Control(Projet2a p, GameContainer g) {
		super();
		this.game = p;
		this.gc = g;
	}

	public synchronized void run() {
		for (;;) {
			try {
				sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (this.gc.getInput().isKeyPressed((Input.KEY_F1))) {
				this.game.addMonstre();
			}

			if (this.gc.getInput().isKeyPressed((Input.KEY_F2))) {
				this.game.getItem().add(
						new Potion(Projet2a.xMax, Projet2a.yMax));
			}

			// Si on appuie sur la fleche Haut
			if (this.gc.getInput().isKeyDown((Input.KEY_DOWN))) {
				Projet2a.hero.setPosY(Projet2a.hero.getPosY() + 0.35f
						* Projet2a.delta);
				Projet2a.hero.setLocation("img/persobas.png");
				Projet2a.hero.setOnMove(true);
			}

			// Si on appuie sur la fleche Bas
			if (this.gc.getInput().isKeyDown(Input.KEY_UP)) {
				Projet2a.hero.setPosY(Projet2a.hero.getPosY() - 0.35f
						* Projet2a.delta);
				Projet2a.hero.setLocation("img/persohaut.png");
				Projet2a.hero.setOnMove(true);
			}

			if (this.gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
				Projet2a.hero.setPosX(Projet2a.hero.getPosX() + 0.35f
						* Projet2a.delta);
				Projet2a.hero.setLocation("img/persodroite.png");
				Projet2a.hero.setOnMove(true);
			}

			if (this.gc.getInput().isKeyDown(Input.KEY_LEFT)) {
				Projet2a.hero.setPosX(Projet2a.hero.getPosX() - 0.35f
						* Projet2a.delta);
				Projet2a.hero.setLocation("img/persogauche.png");
				Projet2a.hero.setOnMove(true);

			}

			if (this.gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				Balle b = Projet2a.hero.tirer(this.gc.getInput().getMouseX(),this.gc.getInput().getMouseY());
				b.start();
				this.game.getTir().add(b);
			}
		}
	}

	public Projet2a getGame() {
		return game;
	}

	public void setGame(Projet2a game) {
		this.game = game;
	}

	public GameContainer getGc() {
		return gc;
	}

	public void setGc(GameContainer gc) {
		this.gc = gc;
	}
}
