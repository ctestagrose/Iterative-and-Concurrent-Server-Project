/**
 * This is the Link class and provides the functionality to create and navigate the linked listed created in this project
 * 
 * 
 * @author Conrad Testagrose
 * @version 03/04/2021
 *
 */
class Link{
	
	public State state;
	public int iData;
	public double dData;
	public Link next;
	public Link previous;
	public Link last;
	public Link first;
	public int priority;
	
	/**
	 * This is the Link class constructor
	 * 
	 * @param state this parameter hold the state object in the link
	 */
	public Link(State state) {
		
		this.state = state;
	}
	
	/**
	 * The displayLink method allows for the printing of the state object contained in the link in the linked list.
	 * 
	 */
	public void displayLink() {
		
		String name;
		double mHI;
		double vCR;
		double covidDeaths;
		double covidCases;
		double cFR;
		double caseRate; //case rate and death rate for whole project
		double deathRate;
		
		name = state.getName();
		mHI = state.getIncome();
		vCR = state.getCrimeRate();
		covidDeaths = state.getCovidDeaths();
		covidCases = state.getCovidCases();
		cFR = covidDeaths / covidCases;
		caseRate = (covidCases / state.getPopulation()) * 100000;
		deathRate = (covidDeaths / state.getPopulation()) * 100000;
	
		System.out.printf("%-15s   %6.0f       %4.1f       %.5f       %.2f       %.2f",  name, mHI, vCR, cFR, caseRate, deathRate);
		System.out.println("");
		
	}
}