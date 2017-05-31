import java.util.ArrayList;


public class RBFN {
	
	private int groupNum;
	private static ArrayList<double[]> training_data;
	private static int J = 3;
	private static int p = 3;
	private static int DNA_length = (p+2)*J+1;
	
	
	
	
	public RBFN(int groupNum, ArrayList<double[]> training_data){
		this.groupNum = groupNum;
		this.training_data = training_data;
	}
	public double[][] randomgroup(){
		double dataset[][] = new double[groupNum][DNA_length];
		
		for(int i = 0;i < groupNum;i++){
			// random theta 
			dataset[i][0] = Math.random();
			
			for(int k = 1;k < J+1;k++){
				double random_num = Math.random();
				if(random_num >= 0.45){
					dataset[i][k] = 40*Math.random();
				}
				else{
					dataset[i][k] = (-40)*Math.random();
				}
			}//for k
			
			//  generate 0~150
			for(int k = J+1 ;k < J+1+p*J;k++){
				dataset[i][k] = 150*Math.random();
			}// for k
			
			// generate 
			for(int k = J+1+p*J;k<DNA_length;k++){
				dataset[i][k] = 10*Math.random();
			}
			
		}//for i
		
		return dataset;
	}// randomgroup
	
	
	public double computeEofN(double[] DNA){
		double En = 0;
		double expect_theta;
		double try_theta;
		for(int i = 0;i < training_data.size();i++){
			expect_theta = training_data.get(i)[3];
			try_theta = computeTheta(DNA,training_data.get(i));
			En += Math.pow(expect_theta - try_theta, 2);
		}
		En = En/2;
		return En;
	}
	private double computeTheta(double[] DNA, double[] training_DNA ) {
		// TODO Auto-generated method stub
		
		// weight
		double W[] = new double[J];
		//
		double M[][] = new double[J][p];
		//
		double X[] = new double[p];
		//
		double sigma[] = new double[J];
		
		for(int i = 0;i<J;i++){
			W[i] = DNA[i+1];
		}
		
		for(int i = 0;i<J;i++){
			for(int k = 0;k<p;k++){
				M[i][k] = DNA[i*p+k+J+1];
			}
		}
		
		for(int i = 0;i<J;i++){
			sigma[i] = DNA[i+J+1+p*J];
		}
		
		for(int i = 0;i<p;i++){
			X[i] = training_DNA[i];
		}
		
		double theta = 0;
		double value;
		for(int i = 0;i < J;i++){
			value = W[i]*Math.exp(-distence(X,M[i]) / (2*sigma[i]*sigma[i]));
			theta = theta + value;
		}
		theta = theta + DNA[0];
		return theta;
	}
	
	private double distence(double[] x, double[] M) {
		// TODO Auto-generated method stub
		double dist = 0;
		for(int i = 0;i < x.length;i++){
			dist = dist + Math.pow(x[i] - M[i], 2);
		}
		
		return dist;
	}
	
	public double computeError(double DNA[]){
		double expect_theta;
		double try_theta;
		double value = 0;
		for(int i = 0;i < training_data.size();i++){
			expect_theta = training_data.get(i)[3];
			try_theta = computeTheta(DNA,training_data.get(i));
			value = value + Math.abs(expect_theta - try_theta);
		}
		value = value / training_data.size();
		return value;
	}
	
	public void getTheta(double DNA[]){
		double try_theta;
		double expect_theta;
		for(int i = 0;i < training_data.size();i++){
			expect_theta = training_data.get(i)[3];
			try_theta = computeTheta(DNA,training_data.get(i));
			System.out.println(expect_theta+" "+try_theta);
		}
	}
	
	
	
	
}
