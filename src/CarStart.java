import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;


public class CarStart extends Thread {
	private Car car;
	private Engine engine;
	private double[] bestDNA;
	private double[] bestLocation;
	private int sysFlag;// 0 for Fuzzy 1 for GA
	public static Point carT, carL, carR, min_InterPointT, min_InterPointL, min_InterPointR;
	public static Point CarLocate, CarT_Locate, CarL_Locate, CarR_Locate;
	
	public Distence dis;

	private boolean isTerminated = false;

	public CarStart(Car car, Engine engine) {
		// TODO Auto-generated constructor stub
		this.car = car;
		this.engine = engine;
		CountInterPoint_TRL();
	}

	public void terminator() {
		isTerminated = true;
		interrupt();
	}

	public Point CountMinInterPoint(Point o, Point p, Point Sensor) {
		double min_dist = 1000;
		double dist;
		Point min_InterPoint = new Point(0, 0);

		for (int i = 0; i < 7; i++) {
			Point InterPoint;
			InterPoint = Distence.Intersec(o, p, MainClass.BoundPoint.get(i), MainClass.BoundPoint.get(i + 1));
			dist = CountDist(Sensor, InterPoint);
			if (dist < min_dist) {
				min_dist = dist;
				min_InterPoint = InterPoint;
			}
		}
		return min_InterPoint;
	}

	public double CountDist(Point p, Point q) {
		double distX = p.getX() - q.getX();
		double distY = p.getY() - q.getY();
		return Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2));
	}

	public void CountInterPoint_TRL() {
		CarLocate = new Point(car.getX(), car.getY());
		carT = new Point(car.getX() + car.getRadius() * Math.cos(Math.toRadians(car.getPhi())),
				car.getY() + car.getRadius() * Math.sin(Math.toRadians(car.getPhi())));

		carR = new Point(car.getX() + car.getRadius() * Math.cos(Math.toRadians(car.getPhi() + 45)),
				car.getY() + car.getRadius() * Math.sin(Math.toRadians(car.getPhi() + 45)));

		carL = new Point(car.getX() + car.getRadius() * Math.cos(Math.toRadians(car.getPhi() - 45)),
				car.getY() + car.getRadius() * Math.sin(Math.toRadians(car.getPhi() - 45)));

		
		CarT_Locate = new Point(car.getX() + 300 * Math.cos(Math.toRadians(car.getPhi())),
				car.getY() + 300 * Math.sin(Math.toRadians(car.getPhi())));
		min_InterPointT = CountMinInterPoint(CarLocate, CarT_Locate, carT);

		CarR_Locate = new Point(car.getX() + 300 * Math.cos(Math.toRadians(car.getPhi() + 45)),
				car.getY() + 300 * Math.sin(Math.toRadians(car.getPhi() + 45)));
		min_InterPointR = CountMinInterPoint(CarLocate, CarR_Locate, carR);

		CarL_Locate = new Point(car.getX() + 300 * Math.cos(Math.toRadians(car.getPhi() - 45)),
				car.getY() + 300 * Math.sin(Math.toRadians(car.getPhi() - 45)));
		min_InterPointL = CountMinInterPoint(CarLocate, CarL_Locate, carL);
	}

	public void run() {
		while (!isTerminated) {

			double distT, distL, distR;
			double theta = car.getTheta();
			engine.newlocation(theta);

			CountInterPoint_TRL();
			distR = CountDist(carR, min_InterPointR);
			distL = CountDist(carL, min_InterPointL);
			distT = CountDist(carT, min_InterPointT);

			
				FuzzySystem fSystem = new FuzzySystem();
				fSystem.Right_Left(distR, distL);
				fSystem.Front(distT);

				
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter("TrainingData.txt", true));
					bw.write(distT + " " + distR + " " + distL + " " + theta);
					bw.newLine();
					bw.close();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(distT + " " + distR + " " + distL + " " + theta);

				car.setTheta(fSystem.Defuzzification());

				MainClass.frame.revalidate();
				MainClass.frame.repaint();

			if (car.getY() - car.getRadius() <= 215) {
				terminator();
			}

			try {
				sleep(50);
			} catch (InterruptedException e) {
			}
		}
	}
}