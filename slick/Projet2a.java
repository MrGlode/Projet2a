package slick;

import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.Log;

//Il faut étendre la classe BasicGame
public class Projet2a extends BasicGame {

	static final int xMax = 800;
	static final int yMax = 600;
	static int delta;
	static Joueur hero;
	private ArrayList<Monstre> monstre;
	private ArrayList<Balle> tir;
	private ArrayList<Potion> item;
	private Image cursor;
	private Control control;

	// Il faut un constructeur de base.
	// Toutes les erreurs seront catchés au moment de
	// l'exécution. (c'est à dire, dans le main)
	public Projet2a() throws SlickException {
		// Initialise le nom de la fenetre ..
		super("Projet2a");
		Log.info("fin d'initialisation");
	}

	// Initialise mes objets. Exécuté au lancement du jeux.
	@Override
	public void init(GameContainer container) throws SlickException {
		this.item = new ArrayList<Potion>();
		this.item.add(new Potion(Projet2a.xMax, Projet2a.yMax));
		this.monstre = new ArrayList<Monstre>();
		this.monstre.add(new Monstre(Projet2a.xMax, Projet2a.yMax));
		Projet2a.hero = new Joueur(Projet2a.xMax, Projet2a.yMax);
		this.tir = new ArrayList<Balle>();
		this.cursor = new Image("img/cible.gif");
		container.setMouseCursor(this.cursor, 10, 10);
		Projet2a.delta = 0;
		this.control = new Control(this, container);
		control.start();
		this.monstre.get(0).start();
	}

	// Méthode appelé en boucle. C'est ici que l'on fait vivre nos objets
	// "non graphique" comme la gestion des touches, de nos propriétés etc
	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		Projet2a.delta = delta;
		for (Monstre m : this.monstre) {
			if (m.isHit(this.tir)) {
				m.setLife(m.getLife() - 1);
			}
		}

		if (Projet2a.hero.getLife() < 0) {

		}

		for (Potion p : this.item) {
			if (p.isLoot(Projet2a.hero)) {
				Projet2a.hero.heal(p.value);
			}
		}

		for (Monstre m : this.monstre) {
			if ((hero.getPosX() < m.getPosX() + m.getSizeX() && hero.getPosX()
					+ hero.getSizeX() > m.getPosX())
					&& (hero.getPosY() + hero.getSizeY() > m.getPosY() && hero
							.getPosY() < m.getPosY() + m.getSizeY())) {
				Projet2a.hero.setLife(hero.getLife() - 1);
			}
		}
		for (int i = 0; i < monstre.size(); i++) {
			if (monstre.get(i).isDead()) {
				monstre.remove(i);
			}
		}

		for (int i = 0; i < item.size(); i++) {
			if (item.get(i).isUsed()) {
				item.remove(i);
			}
		}

		for (int i = 0; i < tir.size(); i++) {
			if (tir.get(i).getDistance() <= 0) {
				tir.remove(i);
			}
		}

		Projet2a.hero.verifPos(Projet2a.xMax, Projet2a.yMax);
	}

	// Méthode appelé en boucle. C'est ici qu'on gére l'affichage.
	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		g.setColor(Color.white);
		g.drawString("F1 : Monstre++", 0, 0);
		g.drawString("F2 : Potion++", 0, 12);
		for (Monstre m : this.monstre) {
			m.affich(Projet2a.xMax, Projet2a.yMax, g);
		}
		for (Balle b : this.tir) {
			b.affich(Projet2a.xMax, Projet2a.yMax, g);
		}
		for (Potion it : this.item) {
			it.affich(Projet2a.xMax, Projet2a.yMax, g);
		}
		Projet2a.hero.initVisuel();
		Projet2a.hero.affich(Projet2a.xMax, Projet2a.yMax, g);
		Projet2a.hero.showLifeBar(Projet2a.xMax, Projet2a.yMax, g);
	}

	public static void main(String[] args) {
		try {
			// Démarre un jeux à partir de ma classe

			Projet2a s = new Projet2a();
			AppGameContainer app = new AppGameContainer(s);
			String[] icons = { "img/favicon.png", "img/faviconh.png" };
			app.setIcons(icons);
			app.setTargetFrameRate(500);
			app.setShowFPS(true);
			app.setDisplayMode(Projet2a.xMax, Projet2a.yMax, false);
			app.start();

		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void addMonstre() {
		this.getMonstre().add(new Monstre(Projet2a.xMax, Projet2a.yMax));
		this.getMonstre().get(this.getMonstre().size() - 1).start();
	}

	public static int getDelta() {
		return delta;
	}

	public static void setDelta(int delta) {
		Projet2a.delta = delta;
	}

	public static Joueur getHero() {
		return hero;
	}

	public static void setHero(Joueur hero) {
		Projet2a.hero = hero;
	}

	public ArrayList<Monstre> getMonstre() {
		return monstre;
	}

	public void setMonstre(ArrayList<Monstre> monstre) {
		this.monstre = monstre;
	}

	public ArrayList<Balle> getTir() {
		return tir;
	}

	public void setTir(ArrayList<Balle> tir) {
		this.tir = tir;
	}

	public ArrayList<Potion> getItem() {
		return item;
	}

	public void setItem(ArrayList<Potion> item) {
		this.item = item;
	}

	public Image getCursor() {
		return cursor;
	}

	public void setCursor(Image cursor) {
		this.cursor = cursor;
	}

	public Control getControl() {
		return control;
	}

	public void setControl(Control control) {
		this.control = control;
	}

	public static int getXmax() {
		return xMax;
	}

	public static int getYmax() {
		return yMax;
	}
}
