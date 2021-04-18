/**
 * This class will store the information about a state and provide methods which allow the get and set of the data along with the comparison by several fields
 * 
 * @author Conrad Testagrose
 * @version 02/05/2021
 *
 */

class State {

	private String name;
	private String capitol;
	private String region;
	private double house;
	private double population;
	private double covidCases;
	private double covidDeaths;
	private double income;
	private double crimeRate;
	private int caseRateRank;
	private int deathRateRank;
	private int vCRRank;
	private int mHIRank;

	/**
	 * Constructor for class State
	 * 
	 * @param name holds the value for the states name before assignment to the object
	 * @param capitol holds the value for the state's capitol before assignment to the object
	 * @param region holds the value for the state's region before assignment to the object
	 * @param house holds the value for the state's house before assignment to the object
	 * @param population holds the value for the state's population before assignment to the object
	 * @param covidCases holds the value for the states's covid cases before assignment to the object
	 * @param covidDeaths holds the value for the states covid deaths before assignment to the object
	 * @param income holds the value for the state's median household income before assignment to the object
	 * @param crimeRate holds the value for the state's crime rate before assignment to the object
	 */
	public State(String name, String capitol, String region, double house, double population,
		double covidCases, double covidDeaths, double income, double crimeRate) {
			
			  
		this.name = name;
		this.capitol = capitol;
		this.region = region;
		this.house = house;
		this.population = population;
		this.covidCases = covidCases;
		this.covidDeaths = covidDeaths;
		this.income = income;
		this.crimeRate = crimeRate;
	}
	
	/**
	 * Gets the string contained in the string variable name 
	 * 
	 * @return name of the state in a String variable
	 */	
	public String getName() {
			  
		return name;
	}

	/**
	 * Sets the name of the state to the String variable name
	 * 
	 * @param name holds the name of the state passed to the contructor 
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the string contained in the string variable capitol 
	 * 
	 * @return capitol of the state in a String variable
	 */	
	public String getCapitol() {
			  
		return capitol;
	}
		
	/**
	 * Sets the capitol of the state to the String variable capitol
	 * 
	 * @param capitol holds the name of the capitol passed to the contructor 
	 */
	public void setCapitol(String capitol) {
		this.capitol = capitol;
	}
	
	/**
	 * Gets the string contained in the string variable region 
	 * 
	 * @return region of the state in a String variable
	 */		  
	public String getRegion() {
			  
		return region;
	}
	
	/**
	 * Sets the region of the state to the String variable region
	 * 
	 * @param region holds the name of the region passed to the contructor 
	 */	  
	public void setRegion(String region) {
		this.region = region;
	}
	
	/**
	 * Gets the double contained in the double variable house 
	 * 
	 * @return house of the state in a double variable
	 */	  
	public double getHouse() {
			  
		return house;
	}
	
	/**
	 * Sets the house of the state to the double variable house
	 * 
	 * @param house holds the number of house members passed to the contructor 
	 */
	public void setHouse(double house) {
		this.house = house;
	}
	
	/**
	 * Gets the double contained in the double variable population 
	 * 
	 * @return population of the state in a double variable
	 */
	public double getPopulation() {
			  
		return population;
	}
	
	/**
	 * Sets the population of the state to the double variable population
	 * 
	 * @param population holds the population of the state passed to the contructor 
	 */
	public void setPopulation(double population) {
		this.population = population;
	}
	
	/**
	 * Gets the double contained in the double variable covidCases 
	 * 
	 * @return covidCases of the state in a double variable
	 */
	public double getCovidCases() {
			  
		return covidCases;
	}
	
	/**
	 * Sets the covid cases of the state to the double variable covidCases
	 * 
	 * @param covidCases holds the number of covid cases of the state passed to the contructor 
	 */
	public void setCovidCases(double covidCases) {
		this.covidCases = covidCases;
	}
	
	/**
	 * Gets the double contained in the double variable covidDeaths 
	 * 
	 * @return covidDeaths of the state in a double variable
	 */
	public double getCovidDeaths() {
			  
		return covidDeaths;
	}
	
	/**
	 * Sets the covid deaths of the state to the double variable covidDeaths
	 * 
	 * @param covidDeaths holds the number of covid deaths of the state passed to the contructor 
	 */
	public void setCovidDeaths(double covidDeaths) {
		this.covidDeaths = covidDeaths;
	}
	
	/**
	 * Gets the double contained in the double variable income 
	 * 
	 * @return income of the state in a double variable
	 */
	public double getIncome() {
			  
		return income;
	}
	
	/**
	 * Sets the median household income of the state to the double variable income
	 * 
	 * @param income holds the median household income of the state passed to the contructor 
	 */
	public void setIncome(double income) {
		this.income = income;
	}
	
	/**
	 * Gets the double contained in the double variable crimeRate 
	 * 
	 * @return crimeRate of the state in a double variable
	 */ 
	double getCrimeRate() {
			  
		return crimeRate;
	}
	
	/**
	 * Sets the violent crime rate of the state to the double variable crimeRate
	 * 
	 * @param crimeRate holds the violent crime rate of the state passed to the contructor 
	 */
	public void setCrimeRate(double crimeRate) {
			  
		this.crimeRate = crimeRate;
	}
	
	/**
	 * Gets the rank of the case rate 
	 * 
	 * @return caseRateRank which hold the rank of the state's case rate
	 */
	public int getCaseRateRank() {
		
		return caseRateRank;
		
	}
	
	/**
	 * Sets the case rate rank
	 * 
	 * @param caseRateRank integer variable that hold the rank of the case rate and is passed to the specific state object
	 */
	public void setCaseRateRank(int caseRateRank) {
		
		this.caseRateRank = caseRateRank;
	}
	
	/**
	 * Gets the rank of the death rate 
	 * 
	 * @return deathRateRank which hold the rank of the state's death rate
	 */	
	public int getDeathRateRank() {
		
		return deathRateRank;
		
	}
	
	/**
	 * Sets the death rate rank
	 * 
	 * @param deathRateRank integer variable that hold the rank of the death rate and is passed to the specific state object
	 */
	public void setDeathRateRank(int deathRateRank) {
		
		this.deathRateRank = deathRateRank;
		
	}
	
	/**
	 * Gets the rank of the violent crime rate 
	 * 
	 * @return vCRRank which hold the rank of the state's violent crime rate
	 */
	public int getVCRRank() {
		
		return vCRRank;
		
	}
	
	/**
	 * Sets the violent crime rate rank
	 * 
	 * @param vCRRank integer variable that hold the rank of the violent crime rate and is passed to the specific state object
	 */
	public void setVCRRank(int vCRRank) {
		
		this.vCRRank = vCRRank;
	}
	
	/**
	 * Gets the rank of the median household income rate 
	 * 
	 * @return MHIRank which hold the rank of the state's median household income
	 */
	public int getMHIRank() {
		
		return mHIRank;
		
	}
	
	/**
	 * Sets the median household income rank
	 * 
	 * @param mHIRank integer variable that hold the rank of the median household income and is passed to the specific state object
	 */
	public void setMHIRank(int mHIRank) {
		
		this.mHIRank = mHIRank;
	}
	
	/**
	 * display() will allow the main function to call print the data contained within each state object
	 * 
	 */
	public void display() {
			  
		System.out.println(""+this.name+" "+this.capitol+" "+this.region+" "+this.house+" "+this.population+" "+this.covidCases+" "+this.covidDeaths+" "+this.income+" "+this.crimeRate+"");
			  
	}
}