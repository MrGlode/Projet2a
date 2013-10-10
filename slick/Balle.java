package slick;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Balle extends Thread implements Drawable {

	private int posX;
	private int posY;
	private int dirX;
	private int dirY;
	private int oriX;
	private int oriY;
	private String location;
	private int sizeX;
	private int sizeY;
	private Image visuel;
	private float distance;

	public Balle(int x, int y, int dirX, int dirY) {
		this.dirX = dirX;
		this.dirY = dirY;
		this.posX = x;
		this.posY = y;
		this.oriX = x;
		this.oriY = y;
		this.sizeX = 6;
		this.sizeY = 6;
		this.distance = 350;
		this.location = "img/balle.png";
		try {
			visuel = new Image(this.location);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deplacerBalle() {
		int[] a = Calc.getNewXY(posX, posY, dirX, dirY,oriX,oriY, 2);
		this.posX = a[0];
		this.posY = a[1];
		this.distance = this.distance - 2;
	}

	public float getDistance() {
		return distance;
	}

	@Override
	public void affich(int xmax, int ymax, Graphics g) {
		g.drawImage(this.getVisuel(), posX - sizeX / 2, posY - sizeY / 2);
	}

	@Override
	public Image getVisuel() {
		return this.visuel;
	}

	@Override
	public int getPosX() {
		return this.posX;
	}

	@Override
	public int getPosY() {
		return this.posY;
	}

	@Override
	public String getLocation() {
		return this.location;
	}

	@Override
	public int getSizeX() {
		return this.sizeX;
	}

	public int getDirX() {
		return dirX;
	}

	public int getDirY() {
		return dirY;
	}

	@Override
	public int getSizeY() {
		return this.sizeY;
	}
}
