import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class GA_system implements ActionListener {

	
	public static JFrame frame;
	public static JLabel label_iteration_times,label_group,label_crossover,label_mutation,label_error;
	public JLabel label_file;
	public static JTextField text_iteration_times,text_group,text_crossover,text_mutation,text_error;
	public static JButton button_choose,button_run;
	
	public ArrayList<double[]> Training_data = new ArrayList<double[]>();
	
	
	public GA_system(){
		frame = new JFrame("GA");
		//label setup
		label_iteration_times = new JLabel("疊代次數: ");
		label_group = new JLabel("基因池大小: ");
		label_crossover = new JLabel("交配機率: ");
		label_mutation = new JLabel("突變機率: ");
		label_error = new JLabel("誤差值: ");
		
		label_file = new JLabel("None");
		
		//TextFile setup
		text_iteration_times = new JTextField("512");
		text_group = new JTextField("200");
		text_crossover = new JTextField("0.5");
		text_mutation = new JTextField("0.5");
		text_error = new JTextField("2.5");
		
		//Button setup
		button_choose = new JButton("Choose File");
		button_run = new JButton("Run GA");
		
		//label location
		label_iteration_times.setBounds(0, 0, 100, 30);
		label_group.setBounds(0, 30, 100, 30);
		label_crossover.setBounds(0, 60, 100, 30);
		label_mutation.setBounds(0, 90, 100, 30);
		label_error.setBounds(0, 120, 100, 30);
		
		//textfield location
		text_iteration_times.setBounds(100, 0, 60, 30);
		text_group.setBounds(100, 30, 60, 30);
		text_crossover.setBounds(100, 60, 60, 30);
		text_mutation.setBounds(100, 90, 60, 30);
		text_error.setBounds(100, 120, 60, 30);
		
		//Button location
		button_choose.setBounds(0, 150, 100, 30);
		button_run.setBounds(0, 180, 80, 30);
		
		label_file.setBounds(150, 150, 200, 30);
		
		
		button_choose.addActionListener(this);
		button_run.addActionListener(this);
		

		//frame add label
		frame.add(label_iteration_times);
		frame.add(label_group);
		frame.add(label_crossover);
		frame.add(label_mutation);
		frame.add(label_error);
		//frame add textfield
		frame.add(text_iteration_times);
		frame.add(text_group);
		frame.add(text_crossover);
		frame.add(text_mutation);
		frame.add(text_error);
		//
		frame.add(button_choose);
		frame.add(button_run);
		
		frame.add(label_file);
		
		
		frame.setLayout(null);
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(button_choose)){
			JFileChooser filechooser = new JFileChooser();
			int returnvalue = filechooser.showOpenDialog(null);
			
			if(returnvalue == JFileChooser.APPROVE_OPTION){
				File selected_file = filechooser.getSelectedFile();
				//System.out.println(selected_file.getName());
				label_file.setText(selected_file.getName());
				try{
					BufferedReader bf = new BufferedReader(new FileReader(selected_file.getPath()));
					String str;
					String temp[];
					int temp_length;
					double temp_ar[];
					while((str = bf.readLine())!= null ){
						//System.out.println(str.length());
						temp = str.split(" ");
						temp_length = temp.length;
						temp_ar = new double[temp_length];
						for(int j = 0;j < temp_length;j++){
							temp_ar[j] = Double.valueOf(temp[j]);
						}
						System.out.println(temp_ar[0]);
						Training_data.add(temp_ar);
						//System.out.println(temp_length);
						
					}
					bf.close();
					
					
				}catch(Exception ex){
					System.out.println(ex);
				}
			}
		}//if
		
		if(e.getSource().equals(button_run)){
			int iterationNum = Integer.valueOf(text_iteration_times.getText());
			int groupNum = Integer.valueOf(text_group.getText());
			double crossover = Double.valueOf(text_crossover.getText());
			double mutation = Double.valueOf(text_mutation.getText());
			double error = Double.valueOf(text_error.getText());
			
			GA_Engine ga_engine = new GA_Engine(iterationNum,groupNum,crossover,mutation,error,Training_data);
			ga_engine.ga_method();
			
		}
		
	}

}
