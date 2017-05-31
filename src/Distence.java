

public class Distence {

	public static Point Intersec(Point a1, Point b1, Point a2, Point b2) {
		Point InterPoint;
		double denom = ((b2.getY() - a2.getY()) * (b1.getX() - a1.getX())) - ((b2.getX() - a2.getX()) * (b1.getY() - a1.getY()));
		double nume_a = ((b2.getX() - a2.getX()) * (a1.getY() - a2.getY())) - ((b2.getY() - a2.getY()) * (a1.getX() - a2.getX()));
		double nume_b = ((b1.getX() - a1.getX()) * (a1.getY() - a2.getY())) - ((b1.getY() - a1.getY()) * (a1.getX() - a2.getX()));

		if (denom == 0) {
			if (nume_a == 0 && nume_b == 0) {
				InterPoint = new Point(MainClass.car.getX(), MainClass.car.getY());
				return InterPoint;
			}
			InterPoint = new Point(400, 400);
			return InterPoint;
		}

		double ua = nume_a / denom;
		double ub = nume_b / denom;

		if (ua >= 0 && ua <= 1 && ub >= 0 && ub <= 1) {
			InterPoint = new Point(a1.getX() + ua * (b1.getX() - a1.getX()), a1.getY() + ua * (b1.getY() - a1.getY()));
			return InterPoint;
		}
		InterPoint = new Point(400, 400);
		return InterPoint;
	}

	}