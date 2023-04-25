package SearchingAlgorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.util.Pair;

public class OptimalAllTime extends AStar {
	private static Map<String , Boolean> visited = new HashMap<String , Boolean>();
	private static ArrayList<Pair<Pair<String,String> , Double> > f = new ArrayList<Pair<Pair<String,String>, Double> >();
	private static  Map<String,ArrayList<Double> > h = new HashMap<String , ArrayList<Double> >();
	private static ArrayList<Pair<Pair<String,String>,Double>> path = new ArrayList<Pair<Pair<String, String>, Double> >();
	private static ArrayList<String> finalPath = new ArrayList<String>();
	private static ArrayList<String> allGoals = new ArrayList<String>();
	private static ArrayList<String> finalAnswer = new ArrayList<String>();
	private double factor;
	private static double cost ;


	public static ArrayList<String> getFinalAnswer() {
		return finalAnswer;
	}
	public static void setFinalAnswer(ArrayList<String> finalAnswer) {
		OptimalAllTime.finalAnswer = finalAnswer;
	}
	public static ArrayList<String> getFinalPath() {
		return finalPath;
	}
	public static void setFinalPath(ArrayList<String> finalPath) {
		OptimalAllTime.finalPath = finalPath;
	}
	public static ArrayList<String> getAllGoals() {
		return allGoals;
	}
	public static void setAllGoals(ArrayList<String> allGoals) {
		OptimalAllTime.allGoals = allGoals;
	}
	private int idx;
	private static boolean isFound ;

	public static Map<String, Boolean> getVisited() {
		return visited;
	}
	public static void setVisited(Map<String, Boolean> visited) {
		OptimalAllTime.visited = visited;
	}
	public static ArrayList<Pair<Pair<String, String>, Double>> getF() {
		return f;
	}
	public static void setF(ArrayList<Pair<Pair<String, String>, Double>> f) {
		OptimalAllTime.f = f;
	}
	public static Map<String, ArrayList<Double>> getH() {
		return h;
	}
	public static void setH(Map<String, ArrayList<Double>> h) {
		OptimalAllTime.h = h;
	}
	public static ArrayList<Pair<Pair<String, String>, Double>> getPath() {
		return path;
	}
	public static void setPath(ArrayList<Pair<Pair<String, String>, Double>> path) {
		OptimalAllTime.path = path;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public static boolean isFound() {
		return isFound;
	}
	public static void setFound(boolean isFound) {
		OptimalAllTime.isFound = isFound;
	}
	public double getPrev() {
		return prev;
	}
	public void setPrev(double prev) {
		this.prev = prev;
	}
	public OptimalAllTime(Map<Pair<String, String>, InputParameters> params, Map<String, ArrayList<String>> adj,
			int numOfGoals, String[] goals, String start, String[][] vals, double factor) {
		super(params, adj, numOfGoals, goals, start, vals);
		this.factor = factor;
		for (Map.Entry<String,ArrayList<String>> mp : adj.entrySet()) {
			visited.put(mp.getKey(), false);
		}
		isFound = false;
		cost = 0 ;
		// TODO Auto-generated constructor stub
	}
	public void updateTime(Map<Pair<String, String>, InputParameters> params , String[][] vals) {
		for(int i = 0 ; i < 20 ; i++) {
			for(int j = 0 ; j < 20 ; j++) {
				double speed = 100.0 * factor;  
				double H = params.get(new Pair<String , String>(vals[i][0] , vals[j][0])).getArealDistance();
			    double G = params.get(new Pair<String , String>(vals[i][0] , vals[j][0])).getStreetDistance();
			    params.get(new Pair<String , String>(vals[i][0] , vals[j][0])).setTravelTime(1.0 * (G / speed) * 60);
			    params.get(new Pair<String , String>(vals[i][0] , vals[j][0])).setBestTravelTime(1.0 * (H / speed) * 60);  
			}
		}
   }
	public static void buildHeuristicTable(int numOfGoals , Map<Pair<String,String>, InputParameters > params ,String[]goals , String[][]vals) {
		for(int i = 0 ; i < numOfGoals ; i++) {
			ArrayList<Double> temp = new ArrayList<Double>();
			for(int j = 0 ; j < 20 ; j++) {
				temp.add(params.get(new Pair<String,String>(goals[i],vals[j][0])).getBestTravelTime());
			}
			h.put(goals[i], temp);
		}
	}
	double prev = 0;
	public static double Optimal (String start, Map<String,ArrayList<String> > adj , int numOfGoals , Map<Pair<String,String>,InputParameters> params , Map<String,ArrayList<Double> > h, 
			double prev , String goal) {
		if(start.equals(goal)) {// we reach the goal here
			double totalCost = 0;
			finalPath.add(goal);
			String temp = goal;
			for(int i = path.size() - 1 ; i >= 0 ; i--) {
				if(path.get(i).getKey().getKey().equals(temp)) {
					finalPath.add(path.get(i).getKey().getValue());
					temp = path.get(i).getKey().getValue();
				}
			}
			for(int i = 0 ; i < finalPath.size() - 1 ; i++) {
				totalCost += params.get(new Pair<String, String>(finalPath.get(i),finalPath.get(i + 1))).getTravelTime();
			}
			for(int i = finalPath.size() - 1 ; i >= 0 ; i--) {
				allGoals.add(finalPath.get(i));
			}
			path.clear();
			finalPath.clear();
		    for (Map.Entry<String,ArrayList<String>> mp : adj.entrySet()) {
				visited.put(mp.getKey(), false);
			}
			isFound = false;
			f.clear();
			return totalCost;
		}
			if(visited.get(start) == false && isFound == false) {
				visited.put(start, true);
				for(int i = 0 ; i < adj.get(start).size() ; i++) {
					if(visited.get(adj.get(start).get(i)) == false) {
						double H = params.get(new Pair<String,String>(goal,adj.get(start).get(i))).getBestTravelTime();
						double G = params.get(new Pair<String,String>(start,adj.get(start).get(i))).getTravelTime();
						for(int j = 0 ; j < f.size() ; j++) {
							if(f.get(j).getKey().equals(adj.get(start).get(i))) {
								f.set(j, new Pair<Pair<String,String>,Double>(new Pair<String,String>(adj.get(start).get(i),start), -1.0));
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
			    	Optimal(nextCity, adj, numOfGoals , params , h , prev + params.get(new Pair<String,String>(nextCity,start)).getTravelTime() , goal);
			}
		return 0;
	}
	public static int bestHeuristic(int numOfGoals, String []goals, Map<Pair<String,String>,InputParameters> params, String start) {
		double min = 1e7;
		int idx = 0;
		for(int j = 0 ; j < numOfGoals ; j++) {
			if(goals[j].equals("-1") == false && params.get(new Pair<String,String>(start,goals[j])).getBestTravelTime() <= min ) {
				min = params.get(new Pair<String,String>(start,goals[j])).getBestTravelTime();
				idx = j;
			}			
		}
		return idx;
	}
	public double printPath() {
		cost = 0;
		for(int i = 0 ; i < allGoals.size() - 1; i++) {
			if(allGoals.get(i).equals(allGoals.get(i + 1)) == false) {
				cost += params.get(new Pair<String,String>(allGoals.get(i),allGoals.get(i + 1))).getTravelTime();
			}
		}
		allGoals.clear();
		return cost;
	}
	public void printFinalPath() {
		double cost = 0;
		System.out.println(" <----- final result ------>");
		for(int i = 0 ; i < allGoals.size() - 1; i++) {
			if(allGoals.get(i).equals(allGoals.get(i + 1)) == false) {
				System.out.println(allGoals.get(i));
				cost += params.get(new Pair<String,String>(allGoals.get(i),allGoals.get(i + 1))).getTravelTime();
			}
		}
		System.out.println(allGoals.get(allGoals.size() - 1));
		System.out.println("cost = " + cost);
		finalAnswer.clear();
		for(int l = 0 ; l < allGoals.size() ; l++) {
			finalAnswer.add(allGoals.get(l));
		}
		allGoals.clear();
 
	}

}
