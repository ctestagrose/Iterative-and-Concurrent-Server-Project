/**
 * The Stack class will provide the functionality to create a stack that will hold state objects 
 * 
 * @author Conrad Testagrose
 * @version 02/18/2021
 *
 */

public class Stack {
	
	private static int stackTop;
	private static int stackSize;
	private static State[] stateStack;
	private State[] stateTemp;
	
	/**
	 * This is the constructor for the Stack class
	 * 
	 * @param stackSize this parameter will provide the size of the stack
	 */

	public Stack(int stackSize) {
		
		this.stackSize = stackSize;
		stateStack = new State[stackSize];
		stackTop = -1;
	}

	/**
	 * This method will push a state object on to the stack and increment stackTop
	 * 
	 * @param pushedState is the passed state object
	 */
	
	void push(State pushedState) {
		
		stateStack[++stackTop] = pushedState;
		
	}

	/**
	 * This method will pop a state off the stack and decrement the stackTop
	 * 
	 * @return the state object from the top of the stack 
	 */
	
	static State pop() {
		
		return stateStack[stackTop--];
		
	}
	
	/**
	 * This method will print the contents of the stack
	 * 
	 */
	static void printStack() {
		
		String name;
		double mHI;
		double vCR;
		double covidDeaths;
		double covidCases;
		double cFR;
		double caseRate; //case rate and death rate for whole project
		double deathRate;
		
		System.out.println("Name               MHI         VCR         CFR           Case Rate     Death Rate");
		System.out.println("-----------------------------------------------------------------------------------");
		
		for(int i = stackTop; i >= 0; i--) {
			
			name = stateStack[i].getName();
			mHI = stateStack[i].getIncome();
			vCR = stateStack[i].getCrimeRate();
			covidDeaths = stateStack[i].getCovidDeaths();
			covidCases = stateStack[i].getCovidCases();
			cFR = covidDeaths / covidCases;
			caseRate = (covidCases / stateStack[i].getPopulation()) * 100000;
			deathRate = (covidDeaths / stateStack[i].getPopulation()) * 100000;
			
			System.out.printf("%-15s   %6.0f       %4.1f       %.5f       %.2f       %.2f",  name, mHI, vCR, cFR, caseRate, deathRate);
			System.out.println("");
			
	
			
		}
		
	}
	
	/**
	 * This method will determine if the stack is empty
	 * 
	 * @return a boolean value if the stack is empty or not
	 */
	static boolean isEmpty() {
		
		return (stackTop == - 1);
	}
	
	/**
	 * This method will determine if the stack is full
	 * 
	 * @return a boolean value is the stack is full or not
	 */
	static boolean isFull() {
		
		return (stackTop == stackSize - 1);
	}


}
