package SearchingAlgorithms;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.swing.text.html.HTMLDocument.Iterator;
import javafx.util.Pair;

public class ReadRequiredData {
	public static Map<Pair<String,String>,InputParameters> params;
	public static Map<String,ArrayList<String> > adjList;
	private static int numOfGoals;
	private static String[] goals;
	private static String wholeData[];
	public static String vals[][];
	private static String start;
	public static void main(String [] args) {
		RUN();
	}
	public static void RUN() {
		int numOfLines = 0, j = 0;
		 wholeData  = new String[1000];
		 vals     = new String[100][100];
		 goals     = new String[100];
		// our adjacency matrix
		Map<Pair<String,String>,String> mp = new HashMap<Pair<String , String>,String>();
		adjList = new HashMap<String,ArrayList<String>>();
	    params = new HashMap<Pair<String , String>,InputParameters>();

		try {
			File myFile = new File("dataInfo.txt");
			Scanner myReader = new Scanner(myFile);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				wholeData[numOfLines++] = data;
			  }
		   }
		catch(FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		start = wholeData[numOfLines - 2];
		StringTokenizer st1 = new StringTokenizer(wholeData[numOfLines - 1],",");
		numOfGoals = 0;
		while(st1.hasMoreTokens()) {
			goals[numOfGoals++] = st1.nextToken();
		}
		System.out.println("start = " + start);
		System.out.println("Goals : ");
		for(int i = 0 ; i < numOfGoals ; i++) {
			System.out.println(goals[i]);
		}
		for(int i = 0 ; i < numOfLines - 2 ; i++) {
			StringTokenizer st = new StringTokenizer(wholeData[i]);
			while(st.hasMoreTokens()) {
				vals[i][j++] = st.nextToken();
			}
			j = 0;
		}
		System.out.println("num of lines = " + numOfLines);
		double stDist , aDist , tTime , btTime;
		for(int i = 0 ; i < numOfLines - 2 ; i++) {
		   for(int l = 0 ; l < 20 ; l++) {
			   mp.put(new Pair<String,String>(vals[i][0],vals[l][0]), vals[i][l + 1]);//1,2,3,4
			   StringTokenizer st = new StringTokenizer(vals[i][l + 1] , ",");
			   stDist = Double.parseDouble(st.nextToken());
			   aDist  = Double.parseDouble(st.nextToken());
			   tTime  = 0;
			   btTime = 0;
			   InputParameters IP = new InputParameters(stDist , aDist , tTime , btTime);
			   params.put(new Pair<String,String>(vals[i][0],vals[l][0]), IP);
		   }
		}
		for(int i = 0 ; i < 20 ; i++) {
			for(int c = 0 ; c < 20 ; c++) {
				params.get(new Pair<String,String>(vals[i][0],vals[c][0])).setStreetDistance(0);
			}
		}
		adjList.put("Akko",     new ArrayList(Arrays.asList("Haifa","Nahariyya","Nazareth","Karmiel")));
		setDist(new ArrayList(Arrays.asList(25.0,12.0,39.0,22.0)), "Akko");
		adjList.put("Bethlehem",new ArrayList(Arrays.asList("Jerusalem","Hebron")));
		setDist(new ArrayList(Arrays.asList(13.0,24.0)), "Bethlehem");
		adjList.put("Bir-sabe", new ArrayList(Arrays.asList("Gaza","Hebron")));
		setDist(new ArrayList(Arrays.asList(45.0,60.0)), "Bir-sabe");
		adjList.put("Gaza",     new ArrayList(Arrays.asList("Yafo","Hebron","Bir-sabe")));
		setDist(new ArrayList(Arrays.asList(70.0,95.0,45.0)), "Gaza");
		adjList.put("Haifa",    new ArrayList(Arrays.asList("Yafo","Akko")));
		setDist(new ArrayList(Arrays.asList(92.0,25.0)), "Haifa");
		adjList.put("Hebron",   new ArrayList(Arrays.asList("Bethlehem","Gaza","Bir-sabe")));
		setDist(new ArrayList(Arrays.asList(24.0,95.0,60.0)), "Hebron");
		adjList.put("Jericho",  new ArrayList(Arrays.asList("Tiberias","Nablus","Ramallah","Jerusalem")));
		setDist(new ArrayList(Arrays.asList(122.0,70.0,37.0,38.0)), "Jericho");
		adjList.put("Jerusalem",new ArrayList(Arrays.asList("Jericho","Ramallah","Bethlehem","Yafo")));
		setDist(new ArrayList(Arrays.asList(38.0,19.0,13.0,67.0)), "Jerusalem");
		adjList.put("Jenin",    new ArrayList(Arrays.asList("Nablus","Tiberias","Nazareth","Tulkarm")));
		setDist(new ArrayList(Arrays.asList(43.0,46.0,33.0,52.0)), "Jenin");
		adjList.put("Karmiel",  new ArrayList(Arrays.asList("Nahariyya","Akko","Nazareth","Tiberias")));
		setDist(new ArrayList(Arrays.asList(30.0,22.0,41.0,40.0)), "Karmiel");
		adjList.put("Nablus",   new ArrayList(Arrays.asList("Tubas","Jenin","Tulkarm","Qaqilia","Salfit","Ramallah","Jericho")));
		setDist(new ArrayList(Arrays.asList(21.0,43.0,29.0,32.0,29.0,50.0,70.0)), "Nablus");
		adjList.put("Nahariyya",new ArrayList(Arrays.asList("Akko","Karmiel")));
		setDist(new ArrayList(Arrays.asList(12.0,30.0)), "Nahariyya");
		adjList.put("Nazareth", new ArrayList(Arrays.asList("Tiberias","Akko","Jenin","Karmiel")));
		setDist(new ArrayList(Arrays.asList(30.0,39.0,33.0,41.0)), "Nazareth");
		adjList.put("Qaqilia",  new ArrayList(Arrays.asList("Salfit","Yafo","Tulkarm","Nablus")));
		setDist(new ArrayList(Arrays.asList(37.0,39.0,34.0,32.0)), "Qaqilia");
		adjList.put("Ramallah", new ArrayList(Arrays.asList("Salfit","Nablus","Jericho","Jerusalem")));
		setDist(new ArrayList(Arrays.asList(54.0,50.0,37.0,19.0)), "Ramallah");
		adjList.put("Salfit",   new ArrayList(Arrays.asList("Qaqilia","Nablus","Yafo","Ramallah")));
		setDist(new ArrayList(Arrays.asList(37.0,29.0,60.0,54.0)), "Salfit");
		adjList.put("Tiberias", new ArrayList(Arrays.asList("Karmiel","Nazareth","Jenin","Jericho")));
		setDist(new ArrayList(Arrays.asList(40.0,30.0,46.0,122.0)), "Tiberias");
		adjList.put("Tubas",    new ArrayList(Arrays.asList("Nablus")));
		setDist(new ArrayList(Arrays.asList(21.0)), "Tubas");
		adjList.put("Tulkarm",  new ArrayList(Arrays.asList("Nablus","Jenin","Qaqilia","Yafo")));
		setDist(new ArrayList(Arrays.asList(29.0,52.0,34.0,46.0)), "Tulkarm");
		adjList.put("Yafo",     new ArrayList(Arrays.asList("Haifa","Gaza","Tulkarm","Qaqilia","Salfit","Jerusalem")));
		setDist(new ArrayList(Arrays.asList(92.0,70.0,46.0,39.0,60.0,67.0)), "Yafo");
		//forOptimal(numOfGoals,goals,start);
		/*
		DepthFirstSearch dfs = new DepthFirstSearch(params, adjList ,numOfGoals,goals,start);
		dfs.DFS(start, adjList);
		BreadthFirstSearch bfs = new BreadthFirstSearch(params, adjList ,numOfGoals,goals,start);
		bfs.BFS(start, adjList);
		System.out.println("<---- BFS ---->");
		for(int i = 0 ; i < bfs.getFinalPath().size() ; i++) {
			System.out.println(bfs.getFinalPath().get(i));
		}
		
		GreedyBestSearch A = new GreedyBestSearch(params, adjList, numOfGoals, goals , start , vals);
		int x = A.bestHeuristic(numOfGoals, goals, params , start);
		A.buildHeuristicTable(numOfGoals, params, goals, vals);
		A.GBS(start, adjList, numOfGoals, goals, params,A.getH(), 0 , x);
	*/
	}
	public static  ArrayList<String> forOptimal(int numOfGoals , String goals[] , String start , double factor) {
		int permute[] = new int[numOfGoals];
		ArrayList<String>FF = new ArrayList<String>();
		ArrayList<String>res = new ArrayList<String>();
        String fixed[] = new String[numOfGoals];
		for(int i = 0 ; i < numOfGoals ; i++) {
			fixed[i] = goals[i];
			permute[i] = i;
		}
		ArrayList<int[]> permutations = heap(permute);
        ArrayList<Pair<Double , String[]>> allPerms = new ArrayList<Pair<Double , String[]>>();
		String temp = start;
		double prev = 0;
		double cost = 0;
		double min = 1e7;
		for(int i = 0 ; i < permutations.size() ; i++) {
			OptimalAllTime OAT = new OptimalAllTime(params, adjList, numOfGoals, goals , start , vals , factor);
			OAT.updateTime(params, vals);
			OAT.buildHeuristicTable(numOfGoals, params, goals, vals);
        	temp = start;
        	for(int k = 0 ; k < numOfGoals ; k++) {
        		goals[k] = fixed[permutations.get(i)[k]];
        		prev += OAT.Optimal(temp, adjList, numOfGoals, params, OAT.getH(), prev, goals[k]);
        		temp = goals[k];
        	}
        	cost = OAT.printPath();
        	if(cost <= min) {
        		min = cost;
        	}
        	String g[] = new String[numOfGoals];
        	for(int y = 0 ; y < numOfGoals ; y++) {
        		g[y] = goals[y];
        	}
        	allPerms.add(new Pair<Double ,  String[]>(cost , g));
        }
		for(int i = 0 ; i < allPerms.size() ; i++) {
			if(allPerms.get(i).getKey() == min) {
				OptimalAllTime OAT1 = new OptimalAllTime(params, adjList, numOfGoals, allPerms.get(i).getValue() , start , vals, factor);
				OAT1.updateTime(params, vals);
				OAT1.buildHeuristicTable(numOfGoals, params, allPerms.get(i).getValue(), vals);
				String temp1 = start;
				for(int k = 0 ; k < numOfGoals ; k++) {
					prev += OAT1.Optimal(temp1, adjList, numOfGoals, params, OAT1.getH(), prev, allPerms.get(i).getValue()[k]);
					temp1 = allPerms.get(i).getValue()[k];
				}
				OAT1.printFinalPath();		
				double finalCost = OAT1.printPath();
				FF =  OAT1.getFinalAnswer();
				for(int j = 0 ; j < FF.size() ; j++) {
					res.add(FF.get(j));
				}
			}
		 }
			System.out.println("<><><><> " + res.size());
			for(int t = 0 ; t < res.size() ; t++) {
				System.out.println("<<--- " + res.get(t));
			}
		return res;
		
	}
	public static void setDist(ArrayList<Double> dist , String s1) {
		for(int i = 0 ; i < adjList.get(s1).size() ; i++) {
			int random = (int) (Math.random() * 1000) % 7 + 4;
			params.get(new Pair<String , String>(s1 , adjList.get(s1).get(i))).setStreetDistance(dist.get(i));
			params.get(new Pair<String , String>(s1 , adjList.get(s1).get(i))).setArealDistance(dist.get(i) - 8); 
		}
	}
	static ArrayList<int []> heap(int [] input) {
		  ArrayList<int []> ret = new ArrayList<int []> ();
		  ret = generate(input.length, input, ret);
		  return ret;
	}
																				//     0      ,   1    ,  2
	static ArrayList<int []> generate(int k, int [] a, ArrayList<int []> output) { // Jericho , Nablus , Jenin
		if (k == 1) {                                                             
		    output.add(a.clone());
	 } else {
	   output = generate(k-1, a, output);
	   for (int i=0; i<k-1; i++) {
		  if (k%2 == 0) {
		        int temp = a[i];
		        a[i] = a[k-1];
		        a[k-1] = temp;
		   } else {
		        int temp = a[0];
		        a[0]   = a[k-1];
		        a[k-1] = temp;
		      }
		      generate(k-1, a, output);
		    }
		  }
		  return output;
		}	
      }

/*
Akko 0,0,0,0 1,180,3,4 4,215,4,3 1,178,2,1 0,0,0,0 1,200,3,4 4,174,4,3 1,170,2,1 0,60,0,0 1,2,3,4 4,99,4,3 1,2,2,1 0,0,0,0 1,144,3,4 4,135,4,3 1,136,2,1 1,55,2,1 1,115,2,1 1,106,2,1 1,103,2,1 
Bethlehem 0,180,0,0 0,0,0,0 4,76,4,3 1,107,2,1 0,165,0,0 1,2,3,4 4,38,4,3 1,2,2,1 0,110,0,0 1,155,3,4 4,70,4,3 1,175,2,1 0,140,0,0 1,101,3,4 4,25,4,3 1,69,2,1 1,157,2,1 1,90,2,1 1,115,2,1 1,66,2,1  
Bir-sabe 0,215,0,0 1,76,3,4 0,0,0,0 1,2,2,1 0,190,0,0 1,2,3,4 4,119,4,3 1,80,2,1 0,203,0,0 1,210,3,4 4,140,4,3 1,190,2,1 0,114,0,0 1,134,3,4 4,110,4,3 1,139,1 1,151,2,1 1,210,2,1 1,121,2,1 1,112,2,1
Gaza 0,178,0,0 1,107,3,4 4,4,4,3 0,0,0,0 0,140,0,0 1,80,3,4 4,60,4,3 1,112,2,1 0,155,0,0 1,199,3,4 4,135,4,3 1,188,2,1 0,219,0,0 1,87,3,4 4,130,4,3 1,119,2,1 1,244,2,1 1,150,2,1 1,108,2,1 1,49,2,1 
Haifa 0,0,0,0 1,165,3,4 4,190,4,3 1,140,2,1 0,0,0,0 1,178,3,4 4,188,4,3 1,148,2,1 0,88,0,0 1,30,3,4 4,122,4,3 1,23,2,1 0,40,0,0 1,129,3,4 4,166,4,3 1,149,2,1 1,77,2,1 1,155,2,1 1,121,2,1 1,79,2,1
Hebron 0,200,0,0 1,2,3,4 4,4,4,3 1,80,2,1 0,178,0,0 0,0,0,0 4,65,4,3 1,29,2,1 0,139,0,0 1,210,3,4 4,100,4,3 1,203,2,1 0,162,0,0 1,137,3,4 4,46,4,3 1,101,2,1 1,188,2,1 1,114,2,1 1,123,2,1 1,96,2,1
Jericho 0,174,0,0 1,38,3,4 4,119,4,3 1,60,2,1 0,188,0,0 1,65,3,4 0,0,0,0 1,30,2,1 0,101,0,0 1,154,3,4 4,49,4,3 1,180,2,1 0,133,0,0 1,90,3,4 4,34,4,3 1,79,2,1 1,100,2,1 1,77,2,1 1,86,2,1 1,99,2,1
Jerusalem 0,170,0,0 1,1,3,4 4,80,4,3 1,112,2,1 0,148,0,0 1,29,3,4 4,30,4,3 0,0,0,0 0,102,0,0 1,176,3,4 4,59,4,3 1,187,2,1 0,139,0,0 1,87,3,4 4,11,4,3 1,63,2,1 1,145,2,1 1,77,2,1 1,85,2,1 1,58,2,1
Jenin 0,60,0,0 1,110,3,4 4,203,4,3 1,155,2,1 0,88,0,0 1,139,3,4 4,101,4,3 1,102,2,1 0,0,0,0 1,70,3,4 4,36,4,3 1,80,2,1 0,22,0,0 1,70,3,4 4,88,4,3 1,68,2,1 1,40,2,1 1,58,2,1 1,44,2,1 1,87,2,1
Karmiel 0,0,0,0 1,155,3,4 4,210,4,3 1,199,2,1 0,30,0,0 1,210,3,4 4,154,4,3 1,176,2,1 0,70,0,0 0,0,0,0 4,102,4,3 1,22,2,1 0,35,0,0 1,139,3,4 4,155,4,3 1,141,2,1 1,32,2,1 1,129,2,1 1,119,2,1 1,128,2,1
Nablus 0,99,0,0 1,70,3,4 4,140,4,3 1,135,2,1 0,0,120,0 1,100,3,4 4,49,4,3 1,59,2,1 0,36,0,0 1,102,3,4 0,0,0,0 1,120,2,1 0,70,0,0 1,27,3,4 4,42,4,3 1,18,2,1 1,77,2,1 1,18,2,1 1,17,2,1 1,65,2,1
Nahariyya 0,0,0,0 1,175,3,4 4,190,4,3 1,188,2,1 0,23,0,0 1,203,3,4 4,180,4,3 1,187,2,1 0,80,0,0 1,22,3,4 4,120,4,3 0,0,0,0 0,46,0,0 1,159,3,4 4,170,4,3 1,179,2,1 1,66,2,1 1,139,2,1 1,120,2,1 1,118,2,1
Nazareth 0,0,0,0 1,140,3,4 4,114,4,3 1,219,2,1 0,40,0,0 1,162,3,4 4,133,4,3 1,139,2,1 0,22,0,0 1,35,3,4 4,70,4,3 1,46,2,1 0,0,0,0 1,100,3,4 4,112,4,3 1,99,2,1 1,21,2,1 1,80,2,1 1,79,2,1 1,122,2,1
Qaqilia 0,144,0,0 1,101,3,4 4,134,4,3 1,87,2,1 0,129,0,0 1,137,3,4 4,90,4,3 1,87,2,1 0,70,0,0 1,139,3,4 4,27,4,3 1,159,2,1 0,100,0,0 0,0,0,0 4,76,4,3 1,30,2,1 1,115,2,1 1,47,2,1 1,19,2,1 1,30,2,1
Ramallah 0,135,0,0 1,25,3,4 4,110,4,3 1,130,2,1 0,166,0,0 1,46,3,4 4,34,4,3 1,11,2,1 0,88,0,0 1,155,3,4 4,42,4,3 1,170,2,1 0,112,0,0 1,76,3,4 0,0,0,0 1,50,2,1 1,129,2,1 1,67,2,1 1,70,2,1 1,81,2,1
Salfit 0,136,0,0 1,69,3,4 4,139,4,3 1,119,2,1 0,149,0,0 1,101,3,4 4,79,4,3 1,63,2,1 0,68,0,0 1,141,3,4 4,18,4,3 1,179,2,1 0,99,0,0 1,30,3,4 4,50,4,3 0,0,0,0 1,109,2,1 1,40,2,1 1,48,2,1 1,44,2,1
Tiberias 0,55,0,0 1,157,3,4 4,151,4,3 1,244,2,1 0,77,0,0 1,188,3,4 4,100,4,3 1,145,2,1 0,40,0,0 1,32,3,4 4,77,4,3 1,66,2,1 0,21,0,0 1,115,3,4 4,129,4,3 1,109,2,1 0,0,0,0 1,100,2,1 1,87,2,1 1,130,2,1
Tubas 0,115,0,0 1,90,3,4 4,210,4,3 1,150,2,1 0,155,0,0 1,114,3,4 4,77,4,3 1,77,2,1 0,58,0,0 1,129,3,4 4,18,4,3 1,139,2,1 0,80,0,0 1,47,3,4 4,67,4,3 1,40,2,1 1,100,2,1 0,0,0,0 1,44,2,1 1,88,2,1
Tulkarm 0,106,0,0 1,115,3,4 4,121,4,3 1,108,2,1 0,121,0,0 1,123,3,4 4,86,4,3 1,85,2,1 0,44,0,0 1,119,3,4 4,17,4,3 1,120,2,1 0,79,0,0 1,19,3,4 4,70,4,3 1,48,2,1 1,87,2,1 1,44,2,1 0,0,0,0 1,39,2,1
Yafo 0,103,0,0 1,66,3,4 4,112,4,3 1,49,2,1 0,79,0,0 1,96,3,4 4,99,4,3 1,58,2,1 0,87,0,0 1,128,3,4 4,65,4,3 1,118,2,1 0,122,0,0 1,30,3,4 4,81,4,3 1,44,2,1 1,130,2,1 1,88,2,1 1,39,2,1 0,0,0,0
Ramallah
Akko,Bethlehem,Bir-sabe
*/
