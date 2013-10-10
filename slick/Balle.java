package slick;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Balle extends Thread implements Drawable {

	private int posX;
	private int posY;
	private float a;
	private float b;
	private String location;
	private int sizeX;
	private int sizeY;
	private Image visuel;
	private float distance;

	public Balle(float x, float y, float dirX, float dirY) {
		this.a = (dirY - y) / (dirX - x);
		this.b = y - this.a * x;
		System.out.println("a:" + a + ",b=" + b);
		this.posX = (int) x;
		this.posY = (int) y;
		this.distance = 350;
		this.location = "img/balle.png";
		this.initVisuel();
	}

	public void run() {
		for (int i = (int) this.distance; i > 0; i = i - 1) {
			try {
				sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int resx = this.posX;
			int resy = this.posY;

			for (int j = 0; j < 16; j++) {
				resx = this.posX + (j / 4);
				resy = (int) (this.a * (resx) + this.b);
				if (2 == Calc.Distance(this.posX, this.posY, resx, resy)) {
					j = 16;
				}
			}
			this.posX = resx;
			this.posY = resy;
		}
	}

	public void deplacerBalle() {

		// this.posX = (int) a[0];
		// this.posY = (int) a[1];
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

	@Override
	public int getSizeY() {
		return this.sizeY;
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
}
