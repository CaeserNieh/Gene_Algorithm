import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class newPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Car car;

	public newPanel(Car car) {
		// TODO Auto-generated constructor stub
		this.car = car;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2f));

		g2.drawOval((int) car.getX() - car.getRadius(), (int) car.getY() - car.getRadius(), 2 * car.getRadius(), 2 * car.getRadius());

		g2.drawLine(MainClass.x[0], MainClass.y[0], MainClass.x[1], MainClass.y[1]);
		g2.drawLine(MainClass.x[1], MainClass.y[1], MainClass.x[2], MainClass.y[2]);
		g2.drawLine(MainClass.x[2], MainClass.y[2], MainClass.x[3], MainClass.y[3]);
		
		
		g2.drawLine(MainClass.x[4], MainClass.y[4], MainClass.x[5], MainClass.y[5]);
		g2.drawLine(MainClass.x[5], MainClass.y[5], MainClass.x[6], MainClass.y[6]);
		g2.drawLine(MainClass.x[6], MainClass.y[6], MainClass.x[7], MainClass.y[7]);
	
		
		g2.setColor(Color.RED);
		g2.drawLine(MainClass.x[3], MainClass.y[3], MainClass.x[4], MainClass.y[4]);
		
		
		g2.setColor(Color.GREEN);
		g2.drawLine((int) car.getX(), (int) car.getY(),
				(int) CarStart.min_InterPointR.getX(), (int) CarStart.min_InterPointR.getY());
		
		g2.drawLine((int) car.getX(), (int) car.getY(),
				(int) CarStart.min_InterPointL.getX(), (int) CarStart.min_InterPointL.getY());
		
		g2.drawLine((int) car.getX(), (int) car.getY(),
				(int) CarStart.min_InterPointT.getX(), (int) CarStart.min_InterPointT.getY());
		
	}
}