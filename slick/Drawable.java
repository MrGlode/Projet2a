package slick;

import org.newdawn.slick.Graphics;

public interface Drawable {

	public void affich(int xmax, int ymax, Graphics g);
	
	public void initVisuel();

	public Object getVisuel();

	//public int getPosX();

//	public int getPosY();

	public String getLocation();

	public int getSizeX();

	public int getSizeY();

}
