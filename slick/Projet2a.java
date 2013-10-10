package slick;

import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.Log;

//Il faut étendre la classe BasicGame
public class Projet2a extends BasicGame {

	// L'image tourne, il faut un petit angle non ?
	// En fait il en faut un pour se souvenir d'un FPS à l'autre de combien il
	// faut tourner l'image.
	private int xMax = 800;
	private int yMax = 600;
	private Joueur hero;
	private ArrayList<Monstre> monstre;
	private ArrayList<Balle> tir;
	private ArrayList<Potion> item;
	private int tour;

	// Il faut un constructeur de base.
	// Vous noterez que toutes les erreurs seront catchés au moment de
	// l'exécution. (c'est à dire, dans le main)
	public Projet2a() throws SlickException {
		// Initialise le nom de la fenetre ..
		super("Projet2a");
		// Très pratique pour débuger l'application. Remplace le
		// System.out.println .. et plus si affinié
		Log.info("fin d'initialisation");
	}

	// Initialise mes objets. Exécuté au lancement du jeux.
	@Override
	public void init(GameContainer container) throws SlickException {
		this.item = new ArrayList<Potion>();
		this.item.add(new Potion(this.xMax,this.yMax));
		this.monstre = new ArrayList<Monstre>();
		this.monstre.add(new Monstre(this.xMax, this.yMax));
		this.hero = new Joueur(this.xMax, this.yMax);
		this.tir = new ArrayList<Balle>();
		this.tour = 1;

	}

	// Méthode appelé en boucle. C'est ici que l'on fait vivre nos objets
	// "non graphique" comme la gestion des touches, de nos propriétés (comme
	// angle) etc
	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		this.updateTour();
		for (Monstre m : this.monstre) {
			if (m.isHit(this.tir)) {
				m.setLife(m.getLife() - 1);
			}
		}
		
		if (this.hero.getLife() < 0) {
			this.hero.setLife(hero.getLifemax());
		}
		
		for(Potion p : this.item){
			if(p.isLoot(this.hero)){
				this.hero.heal(p.value);
			}
		}
		
		for (Monstre m : this.monstre) {
			if ((hero.getPosX() < m.getPosX() + m.getSizeX() && hero.getPosX()
					+ hero.getSizeX() > m.getPosX())
					&& (hero.getPosY() + hero.getSizeY() > m.getPosY() && hero
							.getPosY() < m.getPosY() + m.getSizeY())) {
				this.hero.setLife(hero.getLife() - 1);
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

		for (Balle b : this.tir) {
			b.deplacerBalle();
		}
		for (int i = 0; i < tir.size(); i++) {
			if (tir.get(i).getDistance() == 0) {
				tir.remove(i);
			}
		}

		if (container.getInput().isKeyPressed((Input.KEY_F1))) {
			this.monstre.add(new Monstre(this.xMax, this.yMax));
		}

		if (container.getInput().isKeyPressed((Input.KEY_F2))) {
			this.item.add(new Potion(this.xMax, this.yMax));
		}
		
		// Si on appuie sur la fleche Haut
		if (container.getInput().isKeyDown((Input.KEY_DOWN))) {
			if (this.tour % 2 == 0) {
				this.hero.setPosY(this.hero.getPosY() + 1);
				this.hero.setDirection(2);
				this.hero.setLocation("img/persobas.png");
				this.hero.setOnMove(true);
			}
		}
		// Si on appuie sur la fleche Bas
		if (container.getInput().isKeyDown(Input.KEY_UP)) {
			if (this.tour % 2 == 0) {
				this.hero.setPosY(this.hero.getPosY() - 1);
				this.hero.setDirection(0);
				this.hero.setLocation("img/persohaut.png");
				this.hero.setOnMove(true);
			}
		}

		if (container.getInput().isKeyDown(Input.KEY_RIGHT)) {
			if (this.tour % 2 == 0) {
				this.hero.setPosX(this.hero.getPosX() + 1);
				this.hero.setDirection(1);
				this.hero.setLocation("img/persodroite.png");
				this.hero.setOnMove(true);
			}
		}

		if (container.getInput().isKeyDown(Input.KEY_LEFT)) {
			if (this.tour % 2 == 0) {
				this.hero.setPosX(this.hero.getPosX() - 1);
				this.hero.setDirection(3);
				this.hero.setLocation("img/persogauche.png");
				this.hero.setOnMove(true);
			}
		}
		
		if (container.getInput().isKeyPressed(Input.KEY_SPACE)) {
			
		}

		if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			System.out.println("CLICK ! x:" + container.getInput().getMouseX()
					+ " , y:" + container.getInput().getMouseY());
			this.tir.add(this.hero.tirer(container.getInput().getMouseX(),container.getInput().getMouseY()));
		}
		this.hero.initVisuel();
		this.hero.verifPos(this.xMax, this.yMax);
		if (this.tour % 6 == 0)
			for (Monstre m : this.monstre) {
				m.deplacerMonstre(this.hero);
			}
	}

	// Méthode appelé en boucle. C'est ici qu'on gére l'affichage.
	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		g.setColor(Color.white);
		g.drawString("F1 : Monstre++", 0, 0);
		g.drawString("F2 : Potion++", 0, 12);
		for (Monstre m : this.monstre) {
			m.affich(this.xMax, this.yMax, g);
		}
		for (Balle b : this.tir) {
			b.affich(this.xMax, this.yMax, g);
		}
		for (Potion it : this.item) {
			it.affich(this.xMax, this.yMax, g);
		}
		this.hero.affich(this.xMax, this.yMax, g);
		this.hero.showLifeBar(this.xMax, this.yMax, g);
	}

	public void updateTour() {
		tour++;
		if (tour > 1000) {
			tour = 1;
		}
	}

	public static void main(String[] args) {
		try {
			// Démarre un jeux à partir de ma classe
			Projet2a s = new Projet2a();
			AppGameContainer app = new AppGameContainer(s);
			String[] icons = { "img/favicon.png", "img/faviconh.png" };
			app.setIcons(icons);
			app.setTargetFrameRate(600);
			app.setShowFPS(false);
			app.setDisplayMode(800, 600, false);
			app.start();

		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
