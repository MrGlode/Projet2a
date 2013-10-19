package slick;

import java.util.ArrayList;

public class Monstre extends Personnage {

	public Monstre(String nom) {
		super(nom, "img/monstre.png");
		this.life = 200;
		this.lifemax = 2000;
	}

	public Monstre(int xmax, int ymax) {
		super("", "img/monstre.png");
		this.setPosX((int) (Math.random() * xmax) % (xmax - 30) + 1);
		this.setPosY((int) (Math.random() * ymax) % (ymax - 30) + 1);
		this.life = 200;
		this.lifemax = 200;
	}

	public void run(){
		int t;
		while(this.life > 0){
			t=Projet2a.delta*2;
			try {
				sleep(t);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//TODO
		}
	}
	
	public void deplacerMonstre(Joueur j, int delta) {

		if (this.posX + this.getSizeX() / 2 < j.getPosX() + j.getSizeX() / 2) {
			this.posX = this.posX + 0.25f * delta;
		}

		if (this.posX + this.getSizeX() / 2 > j.getPosX() + j.getSizeX() / 2) {
			this.posX = this.posX - 0.25f * delta;
		}

		if (this.posY + this.getSizeY() / 2 < j.getPosY() + j.getSizeY() / 2) {
			this.posY = this.posY + 0.25f * delta;
		}

		if (this.posY + this.getSizeY() / 2 > j.getPosY() + j.getSizeY() / 2) {
			this.posY = this.posY - 0.25f * delta;
		}

	}

	public boolean isHit(ArrayList<Balle> array) {
		boolean bool = false;
		for (Balle b : array) {
			if ((b.getPosX() + b.getSizeX() / 2 > this.getPosX()
					- this.getSizeX() / 2 && b.getPosX() - b.getSizeX() / 2 < this
					.getPosX() + this.getSizeX() / 2)
					&& (b.getPosY() + b.getSizeY() / 2 > this.getPosY()
							- this.getSizeY() / 2 && b.getPosY() - b.getSizeY()
							/ 2 < this.getPosY() + this.getSizeY() / 2)) {
				bool = true;
			}
			if (bool) {
				break;
			}
		}
		return bool;
	}

}
