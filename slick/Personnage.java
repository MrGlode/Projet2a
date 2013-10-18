package slick;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Personnage extends Thread implements Drawable {

	protected String nom;
	protected Image visuel;
	protected String location;
	protected float posX;
	protected float posY;
	protected int sizeX;
	protected int sizeY;
	protected int life;
	protected int lifemax;
	protected Boolean showMiniLB;

	public Personnage(String nom, String location) {
		super();
		this.nom = nom;
		this.posX = 0;
		this.posY = 0;
		this.life = 3000;
		this.lifemax = 3000;
		this.showMiniLB = true;
		this.location = location;
		this.initVisuel();
	}

	public Personnage(String nom) {
		this.nom = nom;
		this.posX = 0;
		this.posY = 0;
		this.life = 3000;
		this.lifemax = 3000;
		this.showMiniLB = true;
		this.location = "img/persobas.png";
		this.initVisuel();
	}

	public Personnage(String nom, int xmax, int ymax) {
		this.nom = nom;
		this.posX = xmax / 2 - this.sizeX / 2;
		this.posY = ymax / 2 - this.sizeY / 2;
		this.life = 3000;
		this.lifemax = 3000;
		this.showMiniLB = false;
		this.location = "img/persobas.png";
		initVisuel();
	}

	@Override
	public void initVisuel() {
		try {
			this.visuel = new Image(this.location);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.sizeX = this.visuel.getWidth();
		this.sizeY = this.visuel.getHeight();
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void verifPos(int xmax, int ymax) {
		if (this.posX + this.sizeX > xmax) {
			this.posX = xmax - this.sizeX;
			System.out.println(this.sizeX);
		}
		if (this.posX < 0) {
			this.posX = 0;
		}
		if (this.posY + this.sizeY > ymax) {
			this.posY = ymax - this.sizeY;
		}
		if (this.posY < 0) {
			this.posY = 0;
		}
	}

	@Override
	public void affich(int xmax, int ymax, Graphics g) {
		if (life < lifemax && life >= 0 && showMiniLB) {
			g.setColor(Color.darkGray);
			g.fillRect(this.getPosX(), this.getPosY() - 15, this.getSizeX(), 10);
			g.setColor(Color.lightGray);
			g.fillRect(this.getPosX(), this.getPosY() - 15, this.getSizeX(), 10);
			g.setColor(Color.red);
			g.fillRect(this.getPosX() + 1, this.getPosY() - 14,
					((this.getSizeX() - 2) * this.life) / this.lifemax, 8);
		}
		g.drawImage(this.getVisuel(), this.posX, this.posY);
	}

	public Boolean isDead() {
		if (this.life <= 0) {
			return true;
		} else {
			return false;
		}
	}

	public void heal(int montant) {
		if (this.life + montant > this.lifemax) {
			this.life = this.lifemax;
		} else {
			this.life += montant;
		}
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setVisuel(Image visuel) {
		this.visuel = visuel;
	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}

	public String getNom() {
		return nom;
	}

	@Override
	public Image getVisuel() {
		return visuel;
	}

	public float getPosX() {
		return posX;
	}

	public float getPosY() {
		return posY;
	}

	@Override
	public String getLocation() {
		return location;
	}

	@Override
	public int getSizeX() {
		return sizeX;
	}

	@Override
	public int getSizeY() {
		return sizeY;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getLifemax() {
		return lifemax;
	}
}
