
import java.util.Hashtable;

/**
 * Stores information about a state 
 * @author Sanket Chandorkar
 *
 */
class State {

	private String cityName;
	
	private double xCordinate;

	private double yCordinate;
	
	private Hashtable<State, Double> fringe; 
	
	public State(String name, double xCor, double yCor) {
		cityName = name;
		xCordinate = xCor;
		yCordinate = yCor;
		fringe = new Hashtable<>();
	}
	
	/**
	 * Returns straight line distance
	 * @param endState
	 * @return
	 */
	double getSLD(State endState){
		return Math.sqrt( Math.pow(Math.abs(endState.xCordinate - xCordinate),2) + 
							Math.pow(Math.abs(endState.yCordinate - yCordinate),2) );
		
	}
	
	void addStateToFringe(State state, double value ){
		fringe.put(state, new Double(value));
	}

	String getCityName() {
		return cityName;
	}
	
	double getxCordinate() {
		return xCordinate;
	}
	
	double getyCordinate() {
		return yCordinate;
	}
	
	Hashtable<State, Double> getFringe() {
		return fringe;
	}
	
}
