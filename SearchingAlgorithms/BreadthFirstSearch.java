package SearchingAlgorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import javafx.util.Pair;

public class BreadthFirstSearch {
	private Map<Pair<String,String>,InputParameters> params;
	private Map<String,ArrayList<String>> adj;
	private int numOfGoals;
	private String[]goals;
	private String start;
	private  static Map<String , Boolean> visited = new HashMap<String , Boolean>();
	private  ArrayList<String>finalPath = new ArrayList<String>();
	private boolean isFound;
	
	public BreadthFirstSearch(Map<Pair<String, String>, InputParameters> params, Map<String, ArrayList<String>> adj,
			int numOfGoals, String[] goals, String start) {
		super();
		this.params = params;
		this.adj = adj;
		this.numOfGoals = numOfGoals;
		this.goals = goals;
		this.start = start;
		for (Map.Entry<String,ArrayList<String>> mp : adj.entrySet()) {
			visited.put(mp.getKey(), false);
		}
		isFound = false;
	}



	public ArrayList<String> getFinalPath() {
		return finalPath;
	}



	public void setFinalPath(ArrayList<String> finalPath) {
		this.finalPath = finalPath;
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


	public static Map<String, Boolean> getVisited() {
		return visited;
	}


	public static void setVisited(Map<String, Boolean> visited) {
		BreadthFirstSearch.visited = visited;
	}


	void BFS(String start , Map<String, ArrayList<String> > adj) {
		Queue<String> q = new LinkedList<String>();
		String temp = start;
		if(visited.get(start) == false && !isFound) {
			finalPath.add(start);
			for(int j = 0 ; j < numOfGoals ; j++) {
				if(start.equals(goals[j]) == true) {
					isFound = true;
				}
			}
			visited.put(start, true);
			q.add(temp);
			while(!q.isEmpty() && !isFound) {
				temp = q.poll();
				for(int i = 0 ; i < adj.get(temp).size() && !isFound; i++) {
					if(visited.get(adj.get(temp).get(i)) == false) {
						finalPath.add(adj.get(temp).get(i));
						for(int j = 0 ; j < numOfGoals ; j++) {
							if(adj.get(temp).get(i).equals(goals[j]) == true) {
								isFound = true;
							}
						}
						visited.put(adj.get(temp).get(i) , true);
						q.add(adj.get(temp).get(i));
					}
			      }
			   }
			}	
		}
	}