package slick;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Balle extends Thread implements Drawable {

	private float posX;
	private float posY;
	private float a;
	private float b;
	private String location;
	private int sizeX;
	private int sizeY;
	private Image visuel;
	private float distance;
	private boolean depl;

	public Balle(float x, float y, float dirX, float dirY) {
		this.a = (dirY - y) / (dirX - x);
		this.b = y - this.a * x;
		//System.out.println("a:" + a + ",b=" + b);
		this.posX = (int) x;
		this.posY = (int) y;
		if (x < dirX) {
			this.depl = true;
		} else {
			this.depl = false;
		}
		this.distance = 350;
		this.location = "img/balle.png";
		this.initVisuel();
	}

	public void run() {
		for (int i = (int) this.distance; i > 0; i --) {
			try {
				sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			float resx = this.posX;
			float resy = this.posY;
			float dist = 0;
			if (this.depl) {
				for (int j = 0; j < 100; j++) {
					resx = this.posX + (j / 4);
					resy = this.a * (resx) + this.b;
					dist = Calc.Distance(this.posX, this.posY, resx, resy);
					if (dist - dist / 2 <= 2 && dist + dist / 4 >= 2) {
						break;
					}
				}
			} else {
				for (int j = 0; j < 100; j++) {
					resx = this.posX - (j / 4);
					resy = this.a * (resx) + this.b;
					dist = Calc.Distance(this.posX, this.posY, resx, resy);
					if (dist - dist / 2 <= 2 && dist + dist / 4 >= 2) {
						break;
					}
				}
			}
			this.posX = (int) resx;
			this.posY = (int) resy;
		}
		this.distance = 0;
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

	public float getPosX() {
		return this.posX;
	}

	public float getPosY() {
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
