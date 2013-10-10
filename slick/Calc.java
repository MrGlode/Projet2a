package slick;

public abstract class Calc {

	static public double sqr(double a) {
		return a * a;
	}

	static public double Distance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(sqr(y2 - y1) + sqr(x2 - x1));
	}

	public static int[] getNewXY(int x, int y, int dirx, int diry, int oriX,int oriY, int dist) {

		//gestion des cas ou l'on clique au dessus ou au dessous du personnage
		if (oriX == dirx) {
			if (oriY < diry) {
				return new int[] { oriX, y + 2 };
			}
			if (oriY == diry) {
				return new int[] { oriX, oriY };
			}
			if (oriY > diry) {
				return new int[] { oriX, y - 2 };
			}
		}
		
		float a = (diry - oriY) / (dirx - oriX);
		float b = y - a * x;
		float resx=10;
		float resy=10;
		
		if (x > dirx) {
			for (int i = x; i > x - 20; i--) {
				resx = i;
				resy = a * i + b;
				if (Distance(x, y, resx, resy) <= dist + ((dist * 50) / 100)
						&& Distance(x, y, resx, resy) >= dist
								- ((dist * 50) / 100)) {
					return new int[] { (int) Math.round(resx),
							(int) Math.round(resy) };
				}
			}
		} else {
			for (int i = x; i < x + 20; i++) {
				resx = i;
				resy = a * i + b;
				if ((Distance(x, y, resx, resy) <= dist + ((dist * 50) / 100) && Distance(
						x, y, resx, resy) >= dist - ((dist * 50) / 100))) {
					return new int[] { (int) Math.round(resx),
							(int) Math.round(resy) };
				}
			}
		}
		 return new int[] {(int)Math.round(resx), (int) Math.round(resy)};
	}

}
