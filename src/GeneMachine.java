import java.util.LinkedList;


public class GeneMachine {
	
	private int groupNum;
	private LinkedList <GenePair> gPairs;
	private LinkedList <GenePair> CrossoverPool;
	
	public GeneMachine(int groupNum,LinkedList<GenePair> gPairs){
		this.groupNum =  groupNum;
		this.gPairs = gPairs;
	}
	
	public void reproduction(double En[]){
		double sumf = 0;
		double copy[] = new double[groupNum];
		double roulette[] = new double[groupNum];
		
		
		CrossoverPool = new LinkedList<GenePair>();
		
		for(int i = 0;i < groupNum;i++){
			sumf += En[i];
		}
		
		roulette[0] = 0;
		double bestGene = 0;
		int bestIndex = 0;
		for(int i = 0;i < groupNum;i++){
			copy[i] = En[i]/sumf;
			if(i != 0){
				roulette[i] = roulette[i-1] + copy[i];
			}
			if(bestGene < copy[i]){
				bestGene = copy[i];
				bestIndex = i;
			}
		}// for 
		
		for( int i = 0; i < 10;i++){
			GenePair g = new GenePair(gPairs.get(bestIndex).getDNA());
			CrossoverPool.add(g);
		}
		
		int k = 10;
		while(k < groupNum){
			double Ran = Math.random();
			int j = 1;
			while(Ran > roulette[j]){
				j++;
				if(j == groupNum){
					break;
				}
			}
			int copyNum = (int)Math.round(groupNum * copy[j-1]);
			for(int m = 0; m < copyNum;m++){
				CrossoverPool.add(gPairs.get(j-1));
				k++;
			}
		}
		while(CrossoverPool.size() > groupNum){
			CrossoverPool.removeLast();
		}
		
	}//  reproduction
	
	public void crossover(double crossoverPro){
		for(int i = 0; i < CrossoverPool.size();i++){
			if(Math.random() < crossoverPro){
				int crossoverPair;
				do{
					crossoverPair = (int)Math.floor(Math.random() * groupNum);
				}while(crossoverPair == i);
				
				double x1[] = CrossoverPool.get(i).getDNA();
				double x2[] = CrossoverPool.get(crossoverPair).getDNA();
				
				double phi = (Math.random() - 0.5)*2*0.1;
				
				for(int j = 0; j < x1.length ; j++){
					x1[j] = x1[j] + phi * (x1[j] - x2[j]);
					x2[j] = x2[j] - phi * (x1[j] - x2[j]);
				}
				CrossoverPool.get(i).setDNA(x1);
				CrossoverPool.get(crossoverPair).setDNA(x2);
				
			}// if 
		}
	}
	
	public void mutation(double mutationPro){
		
		for(int i = 0;i < CrossoverPool.size();i++){
			if(Math.random() < mutationPro){
				
				double s = (Math.random() - 0.5)*2*0.1;
				double x[] = CrossoverPool.get(i).getDNA();
				
				for(int j = 0;j<x.length;j++){
					if(Math.random() < mutationPro){
						x[j] = x[j] + s + Math.random()*x[j];
					}
				}
				
				CrossoverPool.get(i).setDNA(x);
			}// if 
			
		}// for 
		
	}
	
	public LinkedList<GenePair> getRestGene(){
		return CrossoverPool;
	}
	
	
}
