
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * Implementing GBFS and A*
 * @author Sanket Chandorkar
 *
 */
public class SearchAlgo {

	private State startState = null;
	
	private State endState = null;
	
	public static void main(String[] args) {
	
		SearchAlgo sl = new SearchAlgo();
		sl.populateStateSpace();
		sl.aStarSimulate();
		sl.greadyBFSSimulate();
	}
	
	private void aStarSimulate() {
		double fnValue, gnValue, hnValue;
		PathInfo pInfo, currPath;
		
		currPath = new PathInfo(startState,0,startState.getCityName(),0);
		java.util.PriorityQueue<PathInfo> openList = new java.util.PriorityQueue<PathInfo>(20,new PriorityQueueComparator());
		HashSet<State> closedSet = new HashSet<State>(); 
		
		System.out.println("******************* SIMULATING A-STAR *******************");
		System.out.println();
		while(currPath.getState() != endState){
			
			System.out.println("Current State :" + currPath.getState().getCityName());
			
			closedSet.add(currPath.getState());
			
			// Add fringe to open list with fnvalue updated
			Hashtable<State, Double> fringe = currPath.getState().getFringe(); 
			for( State s : fringe.keySet() ){
				if(closedSet.contains(s)){
					System.out.println("	SKIPPING CLOSED STATE: " + s.getCityName());
					continue;
				}
				gnValue = (Double)fringe.get(s) + currPath.getCostFromStart();
				hnValue = s.getSLD(endState);
				fnValue = gnValue + hnValue; 
				pInfo = new PathInfo(s,fnValue,currPath.getPathFromStart() + " | " + s.getCityName() + " | ", gnValue);
				
				PathInfo p = null;
				// Check if node is already in the list
				Iterator ir = openList.iterator();
				while(ir.hasNext()){
					p = (PathInfo) ir.next();
					if(pInfo.getState().getCityName().equals(p.getState().getCityName())){
						if(pInfo.getFnValue() < p.getFnValue()){
							break;
						}
					}
					p = null;
				}
				if(p!=null){
					openList.remove(p);
					System.out.println("	REMOVING FROM Fringe (City, SLD): ( "
							+ p.getState().getCityName() + " , "
							+ format(p.getFnValue()) + " )");
				}
				
				openList.add(pInfo);
				System.out.println("	ADDING TO Fringe (City, SLD): ( " + s.getCityName() + " , " + format(gnValue)  + " + " + format(hnValue) + " = " + format(fnValue) + " )");
			}

			// Print fringe
			System.out.println("\n	=> Fringe");
			Iterator ir = openList.iterator();
			while(ir.hasNext()){
				PathInfo p = (PathInfo) ir.next();
				System.out.println("	- Fringe (City, SLD): ( " + p.getState().getCityName() + " , " + format(p.getFnValue()) + " )");
			}
			
			// Find next least element
			currPath = openList.poll();
			if(currPath == null ){
				System.err.println("Path List Empty !! Soln not found !!");
				System.exit(-1);
			}
			
			// update path cost and path
			System.out.println("	Next Node: " + currPath.getState().getCityName() + " | Cost : " + 
											currPath.getCostFromStart() + " | PATH :  " + 
											currPath.getPathFromStart() );
			
		}
		
		// Print End state
		System.out.println("Current State :" + currPath.getState().getCityName());
		
		 
	}
	
	private String format(double d){
		String s = Double.toString(d);
		return s.substring(0, s.indexOf('.') + 2);
	}

	private void greadyBFSSimulate() {
		double fnValue, gnValue, hnValue;
		PathInfo pInfo, currPath;
		
		currPath = new PathInfo(startState,0,startState.getCityName(),0);
		java.util.PriorityQueue<PathInfo> openList = new java.util.PriorityQueue<PathInfo>(20,new PriorityQueueComparator());
		HashSet<State> closedSet = new HashSet<State>(); 
		
		System.out.println("******************* GREADY BFS SIMULATE *******************");
		System.out.println();
		while(currPath.getState() != endState){
			
			System.out.println("Current State :" + currPath.getState().getCityName());
			
			closedSet.add(currPath.getState());
			
			// Add fringe to open list with fnvalue updated
			Hashtable<State, Double> fringe = currPath.getState().getFringe(); 
			for( State s : fringe.keySet() ){
				if(closedSet.contains(s)){
					System.out.println("	SKIPPING CLOSED STATE: " + s.getCityName());
					continue;
				}
				gnValue = (Double)fringe.get(s) + currPath.getCostFromStart();
				fnValue = s.getSLD(endState);
				pInfo = new PathInfo(s,fnValue,currPath.getPathFromStart() + " | " + s.getCityName() + " | ", gnValue);
				
				// Check if node is already in the list
				Iterator ir = openList.iterator();
				while(ir.hasNext()){
					PathInfo p = (PathInfo) ir.next();
					if(pInfo.getState().getCityName().equals(p.getState().getCityName())){
						if(pInfo.getFnValue() < p.getFnValue()){
							openList.remove(p);
							System.out.println("	REMOVING FROM Fringe (City, SLD): ( " + p.getState().getCityName() + " , " + format(p.getFnValue()) + " )");
						}
					}
				}
				
				openList.add(pInfo);
				System.out.println("	ADDING TO Fringe (City, SLD): ( " + s.getCityName() + " , " + format(fnValue) + " )");
			}

			// Print fringe
			System.out.println("\n	=> Fringe");
			Iterator ir = openList.iterator();
			while(ir.hasNext()){
				PathInfo p = (PathInfo) ir.next();
				System.out.println("	- Fringe (City, SLD): ( " + p.getState().getCityName() + " , " + format(p.getFnValue()) + " )");
			}
			
			// Find next least element
			currPath = openList.poll();
			if(currPath == null ){
				System.err.println("Path List Empty !! Soln not found !!");
				System.exit(-1);
			}
			
			// update path cost and path
			System.out.println("	Next Node: " + currPath.getState().getCityName() + " | Cost : " + 
											currPath.getCostFromStart() + " | PATH :  " + 
											currPath.getPathFromStart() );
			
		}
		
		// Print End state
		System.out.println("Current State :" + currPath.getState().getCityName());
	}
		 
	

	/**
	 * Populate state space
	 */
	private void populateStateSpace(){
		
		State arad 		= new State("Arad",91, 492);
		State bucharest = new State("Bucharest",400, 327);
		State craiova 	= new State("Craiova",253, 288);
		State eforie 	= new State("Eforie",562, 293);
		State fagaras 	= new State("Fagaras",305 ,449);
		State giurgiu 	= new State("Giurgiu",375, 270);
		State hirsova 	= new State("Hirsova",534, 350);
		State iasi 		= new State("Iasi",473, 506);
		State lugoj 	= new State("Lugoj",165, 379);
		State mehadia 	= new State("Mehadia",168, 339);
		State neamt 	= new State("Neamt",406,537);
		State oradea 	= new State("Oradea",131,571);
		State pitesti 	= new State("Pitesti",320,368);
		State rimnicu 	= new State("Rimnicu",233,410);
		State sibiu 	= new State("Sibiu",207,457);
		State timisoara = new State("Timisoara",94,410);
		State urziceni 	= new State("Urziceni",456,350);
		State vaslui 	= new State("Vaslui",509,444);
		State zerind 	= new State("Zerind",108,531);
		State drobeta 	= new State("Drobeta",165,299);
		
		// set start and end state;
		
		startState = zerind;
		endState = iasi;
		
		// Add connections
		arad.addStateToFringe(zerind, 75);
		arad.addStateToFringe(sibiu, 140);
		arad.addStateToFringe(timisoara, 118);
		
		zerind.addStateToFringe(arad, 75);
		zerind.addStateToFringe(oradea, 71);
		
		oradea.addStateToFringe(zerind, 71);
		oradea.addStateToFringe(sibiu, 151);
		
		timisoara.addStateToFringe(arad, 118);
		timisoara.addStateToFringe(lugoj, 111);
		
		lugoj.addStateToFringe(timisoara, 111);
		lugoj.addStateToFringe(mehadia, 70);
		
		mehadia.addStateToFringe(lugoj, 70);
		mehadia.addStateToFringe(drobeta, 75);
		
		drobeta.addStateToFringe(mehadia, 75);
		drobeta.addStateToFringe(craiova, 120);
		
		craiova.addStateToFringe(drobeta, 120);
		craiova.addStateToFringe(rimnicu, 146);
		craiova.addStateToFringe(pitesti, 138);
		
		sibiu.addStateToFringe(oradea, 151);
		sibiu.addStateToFringe(arad, 140);
		sibiu.addStateToFringe(fagaras, 99);
		sibiu.addStateToFringe(rimnicu, 80);
		
		rimnicu.addStateToFringe(sibiu, 80);
		rimnicu.addStateToFringe(pitesti, 97);
		rimnicu.addStateToFringe(craiova, 146);
		
		fagaras.addStateToFringe(sibiu, 99);
		fagaras.addStateToFringe(bucharest, 211);
		
		pitesti.addStateToFringe(rimnicu, 97);
		pitesti.addStateToFringe(craiova, 138);
		pitesti.addStateToFringe(bucharest, 101);
		
		bucharest.addStateToFringe(fagaras, 211);
		bucharest.addStateToFringe(pitesti, 101);
		bucharest.addStateToFringe(giurgiu, 90);
		bucharest.addStateToFringe(urziceni, 85);
		
		giurgiu.addStateToFringe(bucharest, 90);
		
		urziceni.addStateToFringe(bucharest, 85);
		urziceni.addStateToFringe(hirsova, 98);
		urziceni.addStateToFringe(vaslui, 142);
		
		hirsova.addStateToFringe(eforie, 86);
		hirsova.addStateToFringe(urziceni, 98);
		
		eforie.addStateToFringe(hirsova, 86);
		
		vaslui.addStateToFringe(urziceni, 142);
		vaslui.addStateToFringe(iasi, 92);
		
		iasi.addStateToFringe(vaslui, 92);
		iasi.addStateToFringe(neamt, 87);

		neamt.addStateToFringe(iasi, 87);
		
	}

}
