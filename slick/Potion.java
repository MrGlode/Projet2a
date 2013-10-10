package slick;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Potion {

	public String nom;
	public Image visuel;
	public String location;
	public int posX;
	public int posY;
	public int sizeX;
	public int sizeY;
	public int value;
	private boolean isUsed;

	public Potion(int xmax, int ymax) {
		super();
		this.nom = "Potion";
		this.location = "img/popo.png";
		this.initVisuel();
		this.posX = (int) (Math.random() * xmax) % (xmax - (this.sizeX))
				+ 1;
		this.posY = (int) (Math.random() * ymax) % (ymax - (this.sizeY))
				+ 1;
		this.value = 500;
		this.setUsed(false);
	}

	public void initVisuel() {
		try {
			this.visuel = new Image(this.location);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.sizeX = this.visuel.getWidth();
		this.sizeY = this.visuel.getHeight();
	}

	public void affich(int xmax, int ymax, Graphics g) {
		g.drawImage(this.getVisuel(), this.posX, this.posY);
	}

	public boolean isLoot(Joueur j) {
		if (this.posX + this.sizeX > j.getPosX()
				&& this.posX < j.getPosX() + j.getSizeX()
				&& this.posY + this.sizeY > j.getPosY()
				&& this.posY < j.getPosY() + j.getSizeY()) {
			this.setUsed(true);
			return true;
		}
		return false;
	}

	public String getNom() {
		return nom;
	}

	public Image getVisuel() {
		return visuel;
	}

	public String getLocation() {
		return location;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public int getValue() {
		return value;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

}
