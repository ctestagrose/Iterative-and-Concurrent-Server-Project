/**
 * The PriorityQ class will provide the methods necessary to insert, remove, print, and check if priorityqs are empty or full 
 * 
 * 
 * @author Conrad Testagrose
 * @version 02/17/2021
 *
 */


public class PriorityQ {
	
	private int members;
	int vGMembers = 0;
	int gMembers = 0;
	int fMembers = 0;
	int pMembers = 0;
	State[] vGood;
	State[] good;
	State[] fair;
	State[] poor;
	int vg;
	int g;
	int f;
	int p;
	

	/**
	 * This is the PriorityQ class constructor
	 * 
	 * 
	 * @param vg holds the integer value for the size of the very good priority q
	 * @param g holds the integer value for the size of the good priority q
	 * @param f holds the integer value for the size of the fair priority q
	 * @param p holds the integer vakue for the size of the poor priority q
	 */
	
	public PriorityQ(int vg, int g, int f, int p) {
		
		this.vg = vg;
		this.g = g;
		this.f = f;
		this.p = p;
		
		vGood = new State[vg];
		good = new State[g];
		fair = new State[f];
		poor = new State[p];
		
	}
	
	/**
	 * The insert method provides the functionality to insert each state object into its appropriate priority q 
	 * 
	 * @param state holds the state object that will be inserted 
	 */
	
	public void insert(State state) {
		
		int i = 0;
		
		int j;
		int k;
		int h;
		int l;
		
		double deathRateCat;
		
			
		deathRateCat = ((state.getCovidDeaths() / state.getPopulation()) * 100000);
			
		if(deathRateCat < 50) {
				
			if(vGMembers == 0) {
					
				vGood[vGMembers++] = state;
		
			}
			else if(vGMembers > 0){
				
				for(j = vGMembers - 1; j >= 0; j--) {
						
					double state1DR = ((vGood[j].getCovidDeaths() / vGood[j].getPopulation()) * 100000);
					double state2DR = ((state.getCovidDeaths() / state.getPopulation()) * 100000);
						
					if(state2DR > state1DR) {
							
						vGood[j + 1] = vGood[j];
					}
					else {
							
						break;
					}
						
				}
				vGood[j + 1] = state;
				vGMembers++;
					
			}
				
		}
			
		else if((50 <= deathRateCat) && (deathRateCat < 100)) {
			
			if(gMembers == 0) {
					
				good[gMembers++] = state;
		
			}
			else if(gMembers > 0){
				
				for(h = gMembers - 1; h >= 0; h--) {
						
					double state1DR = ((good[h].getCovidDeaths() / good[h].getPopulation()) * 100000);
					double state2DR = ((state.getCovidDeaths() / state.getPopulation()) * 100000);
						
					if(state2DR > state1DR) {
							
						good[h + 1] = good[h];
					}
					else {
							
						break;
					}
						
				}
				good[h + 1] = state;
				gMembers++;
					
		}
			
		}
			else if((100 <= deathRateCat) && (deathRateCat < 150)) {
					
				if(fMembers == 0) {
						
					fair[fMembers++] = state;
			
				}
				else if(fMembers > 0){
					
					for(k = fMembers - 1; k >= 0; k--) {
							
						double state1DR = ((fair[k].getCovidDeaths() / fair[k].getPopulation()) * 100000);
						double state2DR = ((state.getCovidDeaths() / state.getPopulation()) * 100000);
							
						if(state2DR > state1DR) {
								
							fair[k + 1] = fair[k];
						}
						else {
								
							break;
						}
							
					}
					fair[k + 1] = state;
					fMembers++;
						
				}
			}
		
			else if(deathRateCat >= 150) {
					
				if(pMembers == 0) {
						
					poor[pMembers++] = state;
			
				}
				else if(pMembers > 0){
					
					for(l = pMembers - 1; l >= 0; l--) {
							
						double state1DR = ((poor[l].getCovidDeaths() / poor[l].getPopulation()) * 100000);
						double state2DR = ((state.getCovidDeaths() / state.getPopulation()) * 100000);
							
						if(state2DR > state1DR) {
								
							poor[l + 1] = poor[l];
						}
						else {
								
							break;
						}
							
					}
					poor[l + 1] = state;
					pMembers++;
						
				}
			}
	}
	
	/**
	 * The remove method will remove state objects from each priority q and push them on to a stack
	 * 
	 * @return this method will return a reference to the stack containing the state objects
	 */
	
	public Stack remove() {
		
		Stack ns = new Stack(50); 
		
		
		for(int i = 0; i < poor.length; i++) {
			
			ns.push(poor[i]);
			--pMembers;
		}
		
		for(int i = 0; i < fair.length; i++) {
			
			ns.push(fair[i]);
			--fMembers;
		}
		
		for(int i = 0; i < good.length; i++) {
			
			ns.push(good[i]);
			--gMembers;
		}
		
		for(int i = 0; i < vGood.length; i++) {
			
			ns.push(vGood[i]);
			--vGMembers;
		}
		
		return ns;
		
	}
	
	/**
	 * The printQueue method provides the functionality to print each of the priority queues
	 * 
	 */
	
	public void printQueue() {
		
		String name;
		double mHI;
		double vCR;
		double covidDeaths;
		double covidCases;
		double cFR;
		double caseRate; //case rate and death rate for whole project
		double deathRate;
		
		System.out.println("");
		System.out.println("Poor:");
		System.out.println("Name               MHI         VCR         CFR           Case Rate     Death Rate");
		System.out.println("-----------------------------------------------------------------------------------");
		for(int i = 0; i < p; i++) {
			
			if(poor[i] == null) {
				
				i = 50;
				break;
			}
			
			else if(poor[i] != null) {
				name = poor[i].getName();
				mHI = poor[i].getIncome();
				vCR = poor[i].getCrimeRate();
				covidDeaths = poor[i].getCovidDeaths();
				covidCases = poor[i].getCovidCases();
				cFR = covidDeaths / covidCases;
				caseRate = (covidCases / poor[i].getPopulation()) * 100000;
				deathRate = (covidDeaths / poor[i].getPopulation()) * 100000;
			
				System.out.printf("%-15s   %6.0f       %4.1f       %.5f       %.2f       %.2f",  name, mHI, vCR, cFR, caseRate, deathRate);
				System.out.println("");
		
			}
		}
		
		System.out.println("");
		System.out.println("Fair:");
		System.out.println("Name               MHI         VCR         CFR           Case Rate     Death Rate");
		System.out.println("-----------------------------------------------------------------------------------");
		for(int i = 0; i < f; i++) {

			if(fair[i] == null) {
				
				i = 50;
				break;
			}
			
			else if(fair[i] != null) {
				name = fair[i].getName();
				mHI = fair[i].getIncome();
				vCR = fair[i].getCrimeRate();
				covidDeaths = fair[i].getCovidDeaths();
				covidCases = fair[i].getCovidCases();
				cFR = covidDeaths / covidCases;
				caseRate = (covidCases / fair[i].getPopulation()) * 100000;
				deathRate = (covidDeaths / fair[i].getPopulation()) * 100000;
			
				System.out.printf("%-15s   %6.0f       %4.1f       %.5f       %.2f       %.2f",  name, mHI, vCR, cFR, caseRate, deathRate);
				System.out.println("");
		
			}
			
		}
		
		System.out.println("");
		System.out.println("Good:");
		System.out.println("Name               MHI         VCR         CFR           Case Rate     Death Rate");
		System.out.println("-----------------------------------------------------------------------------------");
		for(int i = 0; i < g; i++) {
			
			if(good[i] == null) {
				
				i = 50;
				break;
			}
			
			else if(good[i] != null) {
				name = good[i].getName();
				mHI = good[i].getIncome();
				vCR = good[i].getCrimeRate();
				covidDeaths = good[i].getCovidDeaths();
				covidCases = good[i].getCovidCases();
				cFR = covidDeaths / covidCases;
				caseRate = (covidCases / good[i].getPopulation()) * 100000;
				deathRate = (covidDeaths / good[i].getPopulation()) * 100000;
			
				System.out.printf("%-15s   %6.0f       %4.1f       %.5f       %.2f       %.2f",  name, mHI, vCR, cFR, caseRate, deathRate);
				System.out.println("");
		
			}
			
		}
		
		System.out.println("");
		System.out.println("Very Good:");
		System.out.println("Name               MHI         VCR         CFR           Case Rate     Death Rate");
		System.out.println("-----------------------------------------------------------------------------------");
		for(int i = 0; i < vg; i++) {
		
			name = vGood[i].getName();
			mHI = vGood[i].getIncome();
			vCR = vGood[i].getCrimeRate();
			covidDeaths = vGood[i].getCovidDeaths();
			covidCases = vGood[i].getCovidCases();
			cFR = covidDeaths / covidCases;
			caseRate = (covidCases / vGood[i].getPopulation()) * 100000;
			deathRate = (covidDeaths / vGood[i].getPopulation()) * 100000;
		
			System.out.printf("%-15s   %6.0f       %4.1f       %.5f       %.2f       %.2f",  name, mHI, vCR, cFR, caseRate, deathRate);
			System.out.println("");
		
		}			
		
	}
	/**
	 * This method needs more development but would allow for the checking of if a priority q is empty
	 * 
	 * @return returns if the number of members in a priority q is empty
	 */
	public boolean isEmpty() {
		
		return (members == 0);
	}
	
	/**This method needs more development but would allow for the checking if a priority q is full
	 * 
	 * 
	 * @return returns if the value of members in a priorityq is full
	 */
	
	public boolean isFull() {
		
		return (members == 0);
	}

}
