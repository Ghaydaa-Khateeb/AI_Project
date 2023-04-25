package SearchingAlgorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.util.Pair;

public class AStar {
	protected  Map<Pair<String,String>,InputParameters> params;
	protected  Map<String,ArrayList<String> > adj;
	protected  int numOfGoals;
	protected  String[]goals;
	protected  String start;
	protected  String[][]vals;
	protected int bestHeuristic;
	private static Map<String , Boolean> visited = new HashMap<String , Boolean>();
	private static ArrayList<Pair<Pair<String,String> , Double> > f = new ArrayList<Pair<Pair<String,String>, Double> >();
	private static  Map<String,ArrayList<Double> > h = new HashMap<String , ArrayList<Double> >();
	private static ArrayList<Pair<Pair<String,String>,Double>> path = new ArrayList<Pair<Pair<String, String>, Double> >();
	private static ArrayList<String> finalPath = new ArrayList<String>();
	private static double cost;

	private int idx;
	private static boolean isFound;
	public AStar(Map<Pair<String, String>, InputParameters> params, Map<String, ArrayList<String>> adj, int numOfGoals,String[] goals, String start, String[][] vals) {
		super();
		this.params = params;
		this.adj = adj;
		this.numOfGoals = numOfGoals;
		this.goals = goals;
		this.start = start;
		this.vals = vals;
		for (Map.Entry<String,ArrayList<String>> mp : adj.entrySet()) {
			visited.put(mp.getKey(), false);
		}
		isFound = false;
	}
	
	public static ArrayList<String> getFinalPath() {
		return finalPath;
	}

	public static void setFinalPath(ArrayList<String> finalPath) {
		AStar.finalPath = finalPath;
	}

	public Map<Pair<String, String>, InputParameters> getParams() {
		return params;
	}
	public void setParams(Map<Pair<String, String>, InputParameters> params) {
		this.params = params;
	}
	public Map<String, ArrayList<String>> getAdj() {
		return adj;
	}
	public void setAdj(Map<String, ArrayList<String>> adj) {
		this.adj = adj;
	}
	public int getNumOfGoals() {
		return numOfGoals;
	}
	public void setNumOfGoals(int numOfGoals) {
		this.numOfGoals = numOfGoals;
	}
	public String[] getGoals() {
		return goals;
	}
	public void setGoals(String[] goals) {
		this.goals = goals;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
    public String[][] getVals() {
		return vals;
	}
	public void setVals(String[][] vals) {
		this.vals = vals;
	}
	public int getBestHeuristic() {
		return bestHeuristic;
	}
	public void setBestHeuristic(int bestHeuristic) {
		this.bestHeuristic = bestHeuristic;
	}
	public static Map<String, Boolean> getVisited() {
		return visited;
	}
	public static void setVisited(Map<String, Boolean> visited) {
		AStar.visited = visited;
	}
	public static ArrayList<Pair<Pair<String, String>, Double>> getF() {
		return f;
	}
	public static void setF(ArrayList<Pair<Pair<String, String>, Double>> f) {
		AStar.f = f;
	}
	public static Map<String, ArrayList<Double>> getH() {
		return h;
	}
	public static void setH(Map<String, ArrayList<Double>> h) {
		AStar.h = h;
	}
	public static ArrayList<Pair<Pair<String, String>, Double>> getPath() {
		return path;
	}
	public static void setPath(ArrayList<Pair<Pair<String, String>, Double>> path) {
		AStar.path = path;
	}
	public static boolean isFound() {
		return isFound;
	}
	public static void setFound(boolean isFound) {
		AStar.isFound = isFound;
	}
	public double getPrev() {
		return prev;
	}
	public void setPrev(double prev) {
		this.prev = prev;
	}
	
	public static double getCost() {
		return cost;
	}

	public static void setCost(double cost) {
		AStar.cost = cost;
	}

	public static void buildHeuristicTable(int numOfGoals , Map<Pair<String,String>, InputParameters > params ,String[]goals , String[][]vals) {
		for(int i = 0 ; i < numOfGoals ; i++) {
			ArrayList<Double> temp = new ArrayList<Double>();
			for(int j = 0 ; j < 20 ; j++) {
				temp.add(params.get(new Pair<String,String>(goals[i],vals[j][0])).getArealDistance());
			}
			h.put(goals[i], temp);
		}
	}
	double prev = 0;
	public static void ASTAR (String start, Map<String,ArrayList<String> > adj , int numOfGoals, String[]goals , Map<Pair<String,String>,InputParameters> params , Map<String,ArrayList<Double> > h, 
			double prev , int idx) {
		if(start.equals(goals[idx])) {
			double totalCost = 0;
			finalPath.add(goals[idx]);
			String temp = goals[idx];
			for(int i = path.size() - 1 ; i >= 0 ; i--) {
				if(path.get(i).getKey().getKey().equals(temp)) {
					finalPath.add(path.get(i).getKey().getValue());
					temp = path.get(i).getKey().getValue();
				}
			}
			for(int i = 0 ; i < finalPath.size() - 1 ; i++) {
				totalCost += params.get(new Pair<String, String>(finalPath.get(i),finalPath.get(i + 1))).getStreetDistance();
			}
			for(int i = finalPath.size() - 1 ; i >= 0 ; i--) {
				System.out.println(" --> " + finalPath.get(i));
			}
			System.out.println(" cost = " + totalCost);
			cost = totalCost;
			isFound = true;
		}
			if(visited.get(start) == false && isFound == false) {
				visited.put(start, true);
				for(int i = 0 ; i < adj.get(start).size() ; i++) {
					if(visited.get(adj.get(start).get(i)) == false) {
						double H = params.get(new Pair<String,String>(goals[idx],adj.get(start).get(i))).getArealDistance();
						double G = params.get(new Pair<String,String>(start,adj.get(start).get(i))).getStreetDistance();
						for(int j = 0 ; j < f.size() ; j++) {
							if(f.get(j).getKey().equals(adj.get(start).get(i))) {
								f.set(j, new Pair<Pair<String,String>,Double>(new Pair<String,String>(adj.get(start).get(i),start), -1.0));
								System.out.println("dodo");
							}
						}
						f.add(new Pair<Pair<String,String>,Double>(new Pair<String,String>(adj.get(start).get(i),start),H + G + prev));
					}
				}
			 }
			double mn = 1e7;
			String nextCity = null;
			for(int i = 0 ; i < f.size() ; i++) {
				if(f.get(i).getValue() <= mn && visited.get(f.get(i).getKey().getKey()) == false && f.get(i).getValue() != -1.0) {
					mn = f.get(i).getValue();
					nextCity = f.get(i).getKey().getKey();
				}
			}
			if (nextCity != null) {
				String realParent = null;
				for(int i = 0 ; i < f.size() ; i++) {
					if(f.get(i).getKey().getKey().equals(nextCity) && f.get(i).getValue() == mn ) {
						realParent = f.get(i).getKey().getValue();
					}
				}
				path.add(new Pair<Pair<String , String>,Double>(new Pair <String,String>(nextCity,realParent),prev + params.get(new Pair<String,String>(nextCity,realParent)).getStreetDistance()));
			    if(isFound == false)
			    	ASTAR(nextCity, adj, numOfGoals , goals, params , h , prev + params.get(new Pair<String,String>(nextCity,start)).getStreetDistance() , idx);
			}
		
	}
	public static int bestHeuristic(int numOfGoals, String []goals, Map<Pair<String,String>,InputParameters> params, String start) {
		double min = 1e7;
		int idx = 0;
		for(int j = 0 ; j < numOfGoals ; j++) {
			if(params.get(new Pair<String,String>(start,goals[j])).getArealDistance() <= min) {
				min = params.get(new Pair<String,String>(start,goals[j])).getArealDistance();
				idx = j;
			}			
		}
		System.out.println("-----");
		return idx;
	}
}

