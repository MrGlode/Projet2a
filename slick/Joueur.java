package slick;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Joueur extends Personnage {

	private int direction;
	private boolean onMove;

	public Joueur(int xmax, int ymax) {
		super("", xmax, ymax);
		this.direction = 0;
		this.setOnMove(false);
	}

	public Balle tirer(int xclick,int yclick) {
		return new Balle(this.posX+sizeX/2, this.posY+sizeY/2, xclick, yclick);
	}
	
	

	public void showLifeBar(int xmax, int ymax, Graphics g){
		g.setColor(Color.darkGray);
		g.fillRect(xmax/4, 5, xmax/2, 20);
		g.setColor(Color.lightGray);
		g.drawRect(xmax/4, 5, xmax/2, 20);
		g.setColor(Color.red);
		g.fillRect(xmax/4 +1, 6,
				((xmax/2-1) * this.life) / this.lifemax, 19);
		g.setColor(Color.white);
		g.drawString(this.life+"/"+this.lifemax,xmax/4+2 , 10);
	}
	
	public int getDirection() {
		return direction;
	}

	public void setDirection(int d) {
		this.direction = d;
	}

	public boolean isOnMove() {
		return onMove;
	}

	public void setOnMove(boolean onMove) {
		this.onMove = onMove;
	}
}
