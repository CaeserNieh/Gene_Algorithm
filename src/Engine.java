
public class Engine {
	Car car;
	private double x, y, phi;
	private double radphi, radtheta;

	public Engine(Car car) {
		// TODO Auto-generated constructor stub
		this.car = car;
	}

	public void newlocation(double theta) {
		radphi = Math.toRadians(car.getPhi());
		radtheta = Math.toRadians(theta);

		x = car.getX() + Math.cos(radphi + radtheta)
				+ Math.sin(radtheta) * Math.sin(radphi);
		y = car.getY() + Math.sin(radphi + radtheta)
				- Math.sin(radtheta) * Math.cos(radphi);
		phi = car.getPhi() - (Math.asin((2 * Math.sin(radtheta)) / (2 * car.getRadius()))) * 180 / Math.PI;

		MainClass.xaxis.setText(String.valueOf(x));
		MainClass.yaxis.setText(String.valueOf(600-y));
		MainClass.angle.setText(String.valueOf(phi*(-1)));
		
		System.out.printf("X: %f , Y : %f\n",x,y);
		
		car.setPhi(phi);
		car.setX(x);
		car.setY(y);
	}
}