
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class MainClass extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JButton start,stop;
	public static JButton button_GA,GA_go;
	public static newPanel RP;
	public static Engine engine;
	public static Car car;
	public static CarStart car_start;
	public static MainClass frame = new MainClass();
	public static double distT, distL, distR;
	public static ArrayList<Point> BoundPoint = new ArrayList<>();
	
	public static int[] x = { 60, 60, 180, 180, 240, 240, 120, 120 };
	public static int[] y = { 400, 290, 290, 215, 215, 350, 350, 400 };
	
	public static JTextField angle;
	public static JTextField xaxis;
	public static JTextField yaxis;
	public static JLabel angleInfor;
	public static JLabel xaxisInfor;
	public static JLabel yaxisInfor;
	
	
	public MainClass() {
		start = new JButton("Start");
		stop = new JButton("End");
		button_GA = new JButton("Build GA");
		GA_go = new JButton("Run GA");
		
		car = new Car();
		engine = new Engine(car);
		RP = new newPanel(car);
		
		angle = new JTextField(20);
		xaxis = new JTextField(20);
		yaxis = new JTextField(20);
		
		angleInfor = new JLabel("Angle : ");
		xaxisInfor = new JLabel("X: ");
		yaxisInfor = new JLabel("Y: ");

		RP.setLayout(null);
		
		angle.setText("90");
		xaxis.setText("0");
		yaxis.setText("0");
		
		
		angleInfor.setBounds(360, 0, 100, 100);
		xaxisInfor.setBounds(360, 100, 100, 100);
		yaxisInfor.setBounds(360, 200, 100, 100);

		RP.add(angleInfor);
		RP.add(xaxisInfor);
		RP.add(yaxisInfor);
		
		angle.setBounds(400, 0, 100, 100);
		xaxis.setBounds(400, 100, 100, 100);
		yaxis.setBounds(400, 200, 100, 100);
		
		RP.add(angle);
		RP.add(xaxis);
		RP.add(yaxis);
	
		start.setBounds(500, 0, 100, 100);
		//stop.setBounds(500, 100, 100, 100);
		RP.add(start);
		//RP.add(stop);
		
		
		button_GA.setBounds(500, 100, 100, 100);
		GA_go.setBounds(500,200,100,100);
		RP.add(button_GA);
		RP.add(GA_go);
		
		add(RP);

		start.addActionListener(this);
		button_GA.addActionListener(this);
		GA_go.addActionListener(this);
		
		setFocusable(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(550, 550);
		setLocation(400, 100);
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		frame.setLocation(400, 100);
		frame.setVisible(true);

		BoundPoint.add(new Point(60, 400));
		BoundPoint.add(new Point(60, 290));
		
		BoundPoint.add(new Point(180, 290));
		BoundPoint.add(new Point(180, 215));

		BoundPoint.add(new Point(240, 215));
		BoundPoint.add(new Point(240, 350));
		
		BoundPoint.add(new Point(120, 350));
		BoundPoint.add(new Point(120, 400));

		car_start = new CarStart(car, engine);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(start)) {
			//car_start = new CarStart(car, engine);
			car_start.start();
		}
		if(e.getSource().equals(button_GA)){
			new GA_system();
		}
		
	}//actionPerformed
}