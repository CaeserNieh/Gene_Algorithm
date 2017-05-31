public class Car {
	private double x, y, phi, theta;
	private int r;

	Car() {
		x = 90;
		y = 400;
		theta = 0;
		phi = -90;
		r = 15;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getPhi() {
		return phi;
	}

	public double getTheta() {
		return theta;
	}

	public int getRadius() {
		return r;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setPhi(double phi) {
		if (phi >= 270) {
			this.phi = phi - 360;
		} else if (phi >= -90) {
			this.phi = phi;
		} else {
			this.phi = 360 + phi;
		}
	}

	public void setTheta(double theta) {
		this.theta = theta;
	}
}