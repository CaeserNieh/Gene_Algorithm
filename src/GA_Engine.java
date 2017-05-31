import java.util.ArrayList;
import java.util.LinkedList;


public class GA_Engine {
	
	private static int iterNum;
	private static int groupNum;
	private static double crossover;
	private static double mutation;
	private double error;
	private static ArrayList<double[]> training_data;
	
	
	public GA_Engine(int iterNum,int groupNum,double crossover,double mutation, double error,ArrayList<double[]> training_data){
		this.iterNum = iterNum;
		this.groupNum = groupNum;
		this.crossover = crossover;
		this.mutation = mutation;
		this.error = error;
		this.training_data = training_data;
	}
	
	public static void ga_method(){
		RBFN rbfn = new RBFN(groupNum, training_data);
		
		double[][] dataset;
		LinkedList<GenePair> gpairs = null;
		dataset = rbfn.randomgroup();
		
		for(int times = 0;times< iterNum;times++){
			double En[] = new double[groupNum];
			gpairs = new LinkedList<>();
			
			for(int i = 0;i < groupNum;i++){
				En[i] = 1/rbfn.computeEofN(dataset[i]);
				gpairs.add(new GenePair(dataset[i]));
			}
			
			GeneMachine genemach = new GeneMachine(groupNum,gpairs);
			
			genemach.reproduction(En);
			genemach.crossover(crossover);
			genemach.mutation(mutation);
			
			gpairs = genemach.getRestGene();
			for(int m = 0; m < gpairs.size();m++){
				double DNA[] = gpairs.get(m).getDNA();
				for(int n = 0;n < DNA.length;n++){
					dataset[m][n] = DNA[n];
				}
			}
		}// for times
		//System.out.println();
		
		//  Error function
		System.out.println("----------------------------------");
		double Error[] = new double[groupNum];
		
		double min = 100000;
		int best_index = 0;
		for(int i = 0;i < groupNum;i++){
			Error[i] = rbfn.computeError(gpairs.get(i).getDNA());
			if(Error[i] < min){
				min = Error[i];
				best_index = i;
			}
		}
		
		
		
		
		
	}
}
