
/***
 * Keeps path information for each state
 * @author Sancho
 *
 */
public class PathInfo {

	private State state;
	
	/* Represents the f(n) value computed */
	private double fnValue;
	
	private String pathFromStart;
	
	private double costFromStart;
	
	public PathInfo(State s, double fnval, String path, double cost) {
		state = s;
		fnValue = fnval;
		pathFromStart = new String(path);
		costFromStart = cost;
	}

	State getState() {
		return state;
	}
	
	double getFnValue() {
		return fnValue;
	}
	
	String getPathFromStart() {
		return pathFromStart;
	}
	
	double getCostFromStart() {
		return costFromStart;
	}
}

