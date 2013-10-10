package slick;

public abstract class Calc {

	static public double Distance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow((y2 - y1), 2) + Math.pow((x2 - x1),2));
	}

	public static float[] getNewXY(float x, float y, float dirx, float diry, float oriX,
			float oriY, float dist) {

		// gestion des cas ou l'on clique au dessus ou au dessous du personnage
		if (oriX == dirx) {
			if (oriY < diry) {
				return new float[] { oriX, y + 2 };
			}
			if (oriY == diry) {
				return new float[] { oriX, oriY };
			}
			if (oriY > diry) {
				return new float[] { oriX, y - 2 };
			}
		}

		float a = (diry - oriY) / (dirx - oriX);
		float b = oriY - a * oriX;
		float resx = 10;
		float resy = 10;

		System.out.println("a = "+a+", b= "+b);
		
		if (x > dirx) {
			for (float i = x; i > x - 4; i--) {
				resx = i;
				resy = a * i + b;
				if (Distance(x, y, resx, resy) ==dist) {
					return new float[] { (float) Math.round(resx),
							(float) Math.round(resy) };
				}
			}
		} else {
			for (float i = x; i < x + 4; i++) {
				resx = i;
				resy = a * i + b;
				if ((Distance(x, y, resx, resy) >=dist-dist/4)) {
					return new float[] { (float) Math.round(resx),
							(float) Math.round(resy) };
				}
			}
		}
		return new float[] { (float) Math.round(resx), (float) Math.round(resy) };
	}

}
