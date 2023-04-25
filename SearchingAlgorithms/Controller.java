package SearchingAlgorithms;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Pair;

public class Controller implements Initializable{
	@FXML ChoiceBox CB_Start;
	@FXML ChoiceBox Time;
	@FXML VBox VB ;
	@FXML CheckBox c1;
	@FXML CheckBox c2;
	@FXML CheckBox c3;
	@FXML CheckBox c4;
	@FXML CheckBox c5;
	@FXML CheckBox c6;
	@FXML CheckBox c7;
	@FXML CheckBox c8;
	@FXML CheckBox c9;
	@FXML CheckBox c10;
	@FXML CheckBox c11;
	@FXML CheckBox c12;
	@FXML CheckBox c13;
	@FXML CheckBox c14;
	@FXML CheckBox c15;
	@FXML CheckBox c16;
	@FXML CheckBox c17;
	@FXML CheckBox c18;
	@FXML CheckBox c19;
	@FXML CheckBox c20;
	@FXML TextField TF;
	@FXML CheckBox []C = new CheckBox[20];
	@FXML HBox HB;
	@FXML TextArea TA ;
	@FXML ScrollBar SP;
	@FXML ScrollPane scroll;
	@FXML TextField cost;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		CB_Start.getItems().addAll("Akko","Bethlehem","Bir-sabe","Gaza","Haifa","Hebron","Jericho",
				"Jerusalem","Jenin","Karmiel","Nablus","Nahariyya","Nazareth","Qaqilia","Ramallah",
				"Salfit","Tiberias","Tubas","Tulkarm","Yafo");
		Time.getItems().addAll("08:00AM to 12:00PM","12:00PM to 04:00PM","04:00PM to 08:00PM");
		C[0]  = c1; C[1]   = c2; C[2]   = c3; C[3]   = c4; C[4]   = c5; C[5]   = c6; C[6]   = c7; C[7]   = c8; C[8]   = c9; C[9]   = c10;
		C[10] = c11; C[11] = c12; C[12] = c13; C[13] = c14; C[14] = c15; C[15] = c16; C[16] = c17; C[17] = c18; C[18] = c19; C[19] = c20;
		// TODO Auto-generated method stub
	}
	public void DFS_Search(ActionEvent e) {
		try {
			while(!HB.getChildren().isEmpty()) {
				   HB.getChildren().remove(0);
				}
				cost.setText("");
			int nGoals = 0 , j = 0;
			String start = CB_Start.getValue().toString();
			System.out.println(start);
			
			for(int i = 0 ; i < 20 ; i++) {
				if(C[i].isSelected()) {
					nGoals++;
				}
			}		
			String DFS_Goals[] = new String[nGoals];
			for(int i = 0 ; i < 20 ; i++) {
				if(C[i].isSelected()) {
					DFS_Goals[j++] = C[i].getText();
				}
			}
			ReadRequiredData RRD = new ReadRequiredData();
			RRD.RUN();
			DepthFirstSearch dfs = new DepthFirstSearch(RRD.params, RRD.adjList, nGoals, DFS_Goals, start);
			dfs.DFS(start, RRD.adjList);
			for(int i = 0 ; i < dfs.getFinalPath().size() - 1 ; i++) {
				TA = new TextArea();
				TA.setText(dfs.getFinalPath().get(i) + "\n ---> ");
				TA.setStyle("-fx-control-inner-background: #FFDAB9;  -fx-font-family: Consolas; -fx-font-weight:Bold; -fx-highlight-fill: #ffffff; -fx-highlight-text-fill: #000000; -fx-text-fill: #fffff;");
				HB.getChildren().add(TA);
			}
			TA = new TextArea();
			TA.setText(dfs.getFinalPath().get(dfs.getFinalPath().size() - 1) + "\n GOAL ");
			TA.setStyle("-fx-control-inner-background: #556B2F; -fx-font-family:  Consolas; -fx-font-weight:Bold; -fx-highlight-fill: #ffffff; -fx-highlight-text-fill: #000000; -fx-text-fill: #fffff;");
			HB.getChildren().add(TA);
			scroll.setContent(HB);
			TF.setText("Depth First Search");
		}
		catch (Exception E){
			Alert A = new Alert(AlertType.ERROR);
			A.setTitle("Input fields Error!");
			A.show();
		}
 	}
	public void BFS_Search(ActionEvent e) {
		try {
			while(!HB.getChildren().isEmpty()) {
			   HB.getChildren().remove(0);
			}
			cost.setText("");
			int nGoals = 0 , j = 0;
			String start = CB_Start.getValue().toString();
			System.out.println(start);
			
			for(int i = 0 ; i < 20 ; i++) {
				if(C[i].isSelected()) {
					nGoals++;
				}
			}		
			String DFS_Goals[] = new String[nGoals];
			for(int i = 0 ; i < 20 ; i++) {
				if(C[i].isSelected()) {
					DFS_Goals[j++] = C[i].getText();
				}
			}
			ReadRequiredData RRD = new ReadRequiredData();
			RRD.RUN();
			BreadthFirstSearch dfs = new BreadthFirstSearch(RRD.params, RRD.adjList, nGoals, DFS_Goals, start);
			dfs.BFS(start, RRD.adjList);
			for(int i = 0 ; i < dfs.getFinalPath().size() - 1 ; i++) {
				TA = new TextArea();
				TA.setText(dfs.getFinalPath().get(i) + "\n ---> ");
				TA.setStyle("-fx-control-inner-background: #FFDAB9;  -fx-font-family: Consolas; -fx-font-weight:Bold; -fx-highlight-fill: #ffffff; -fx-highlight-text-fill: #000000; -fx-text-fill: #fffff;");
				HB.getChildren().add(TA);
			}
			TA = new TextArea();
			TA.setText(dfs.getFinalPath().get(dfs.getFinalPath().size() - 1) + "\n GOAL ");
			TA.setStyle("-fx-control-inner-background: #556B2F; -fx-font-family:  Consolas; -fx-font-weight:Bold; -fx-highlight-fill: #ffffff; -fx-highlight-text-fill: #000000; -fx-text-fill: #fffff;");
			HB.getChildren().add(TA);
			scroll.setContent(HB);
	
			TF.setText("Breadth First Search");
			}
		catch(Exception E) {
			Alert A = new Alert(AlertType.ERROR);
			A.setTitle("Input fields Error!");
			A.show();
		}
	}
	public void GreedySearch(ActionEvent e) {
		try {
			while(!HB.getChildren().isEmpty()) {
				   HB.getChildren().remove(0);
				}
				cost.setText("");
			int nGoals = 0 , j = 0;
			String start = CB_Start.getValue().toString();
			System.out.println(start);
			
			for(int i = 0 ; i < 20 ; i++) {
				if(C[i].isSelected()) {
					nGoals++;
				}
			}		
			String DFS_Goals[] = new String[nGoals];
			for(int i = 0 ; i < 20 ; i++) {
				if(C[i].isSelected()) {
					DFS_Goals[j++] = C[i].getText();
				}
			}
		
			ReadRequiredData RRD = new ReadRequiredData();
			RRD.RUN();
			GreedyBestSearch A = new GreedyBestSearch(RRD.params, RRD.adjList, nGoals, DFS_Goals, start , RRD.vals);
			int x = A.bestHeuristic(nGoals,DFS_Goals, RRD.params, start);
			A.buildHeuristicTable(nGoals, RRD.params, DFS_Goals, RRD.vals);
			A.GBS(start, RRD.adjList, nGoals, DFS_Goals, RRD.params, A.getH(), 0, x);
			for(int i = A.getFinalPath().size() - 1 ; i >= 1 ; i--) {
				TA = new TextArea();
				TA.setText(A.getFinalPath().get(i) + "\n ---> ");
				TA.setStyle("-fx-control-inner-background: #FFDAB9;  -fx-font-family: Consolas; -fx-font-weight:Bold; -fx-highlight-fill: #ffffff; -fx-highlight-text-fill: #000000; -fx-text-fill: #fffff;");
				HB.getChildren().add(TA);
			}
			TA = new TextArea();
			TA.setText(A.getFinalPath().get(0) + "\n GOAL ");
			TA.setStyle("-fx-control-inner-background: #556B2F; -fx-font-family:  Consolas; -fx-font-weight:Bold; -fx-highlight-fill: #ffffff; -fx-highlight-text-fill: #000000; -fx-text-fill: #fffff;");
			HB.getChildren().add(TA);
			scroll.setContent(HB);
			cost.setText(""+A.getCost()+"");
			A.getFinalPath().clear();
			TF.setText("Greedy Best Search");
		}
		catch(Exception E) {
			Alert A = new Alert(AlertType.ERROR);
			A.setTitle("Input fields Error!");
			A.show();
		}
	}
	public void A_Search(ActionEvent e) {
		try {
			System.out.println(HB.getChildren().size());
			while(!HB.getChildren().isEmpty()) {
				   HB.getChildren().remove(0);
				}
		
				cost.setText("");
			int nGoals = 0 , j = 0;
			String start = CB_Start.getValue().toString();
			System.out.println(start);
			
			for(int i = 0 ; i < 20 ; i++) {
				if(C[i].isSelected()) {
					nGoals++;
				}
			}		
			String DFS_Goals[] = new String[nGoals];
			for(int i = 0 ; i < 20 ; i++) {
				if(C[i].isSelected()) {
					DFS_Goals[j++] = C[i].getText();
				}
			}
			/*
			AStar A = new AStar(params, adjList, numOfGoals, goals , start , vals);
			int x = A.bestHeuristic(numOfGoals, goals, params , start);
			A.buildHeuristicTable(numOfGoals, params, goals, vals);
			A.ASTAR(start, adjList, numOfGoals, goals, params,A.getH(), 0 , x);
			*/
			ReadRequiredData RRD = new ReadRequiredData();
			RRD.RUN();
			AStar A = new AStar(RRD.params, RRD.adjList, nGoals, DFS_Goals, start , RRD.vals);
			int x = A.bestHeuristic(nGoals,DFS_Goals, RRD.params, start);
			A.buildHeuristicTable(nGoals, RRD.params, DFS_Goals, RRD.vals);
			A.ASTAR(start, RRD.adjList, nGoals, DFS_Goals, RRD.params, A.getH(), 0, x);
			for(int i = A.getFinalPath().size() - 1 ; i >= 1 ; i--) {
				TA = new TextArea();
				TA.setText(A.getFinalPath().get(i) + "\n ---> ");
				TA.setStyle("-fx-control-inner-background: #FFDAB9;  -fx-font-family: Consolas; -fx-font-weight:Bold; -fx-highlight-fill: #ffffff; -fx-highlight-text-fill: #000000; -fx-text-fill: #fffff;");
				HB.getChildren().add(TA);
			}
			TA = new TextArea();
			TA.setText(A.getFinalPath().get(0) + "\n GOAL ");
			TA.setStyle("-fx-control-inner-background: #556B2F; -fx-font-family:  Consolas; -fx-font-weight:Bold; -fx-highlight-fill: #ffffff; -fx-highlight-text-fill: #000000; -fx-text-fill: #fffff;");
			HB.getChildren().add(TA);
			A.getFinalPath().clear();
			scroll.setContent(HB);
			cost.setText(""+A.getCost()+"");
			TF.setText("ASTAR");
	  }
		catch(Exception E) {
			Alert A = new Alert(AlertType.ERROR);
			A.setTitle("Input fields Error!");
			A.show();
		}
	}
	public void Optimal(ActionEvent e) {
		try {
			while(!HB.getChildren().isEmpty()) {
				   HB.getChildren().remove(0);
				}
				cost.setText("");
			int nGoals = 0 , j = 0;
			double factor;
			String time = Time.getValue().toString();
			if(time.equals("08:00AM to 12:00PM")) {
				factor = 1;
			}
			else if(time.equals("12:00PM to 04:00PM")) {
				factor = 0.5;
			}
			else if(time.equals("04:00PM to 08:00PM")) {
				factor = 0.8;
			}
			else {
				factor = 1;
			}
			String start = CB_Start.getValue().toString();
			System.out.println(start);
			for(int i = 0 ; i < 20 ; i++) {
				if(C[i].isSelected()) {
					nGoals++;
				}
			}				
			int kk = 1 / nGoals;
	
			String DFS_Goals[] = new String[nGoals];
			for(int i = 0 ; i < 20 ; i++) {
				if(C[i].isSelected()) {
					DFS_Goals[j++] = C[i].getText();
				}
			}
			System.out.println("DEBUGGING");
			for(int i = 0 ; i < nGoals ; i++) {
				System.out.println(DFS_Goals[i]);
			}
			double Cost = 0;
			ReadRequiredData RRD = new ReadRequiredData();
			RRD.RUN();
			ArrayList<String>Path = new ArrayList<String>(); 
			System.out.println("nGoals = " + nGoals);
			Path = RRD.forOptimal(nGoals, DFS_Goals,start , factor);
			System.out.println(" ---- ---- --- " + Path.size());
			for(int i = 0 ; i < Path.size() - 1  ; i++) {
				if(Path.get(i).equals(Path.get(i + 1)) == false) {
					Cost += RRD.params.get(new Pair<String , String>(Path.get(i),Path.get(i + 1))).getTravelTime();
					boolean flag = false;
					TA = new TextArea();
					for(int k = 0 ; k < nGoals ; k++) {
						if(DFS_Goals[k].equals(Path.get(i)) == true) {
							TA.setText(Path.get(i) + "\n GOAL ");
							TA.setStyle("-fx-control-inner-background:#556B2F;  -fx-font-family: Consolas; -fx-font-weight:Bold; -fx-highlight-fill: #ffffff; -fx-highlight-text-fill: #000000; -fx-text-fill: #fffff;");
							flag = true;
							break;
						}
					}
					
					if(flag == false) {
						TA.setText(Path.get(i) + "\n ---> ");
						TA.setStyle("-fx-control-inner-background:#FFDAB9;  -fx-font-family: Consolas; -fx-font-weight:Bold; -fx-highlight-fill: #ffffff; -fx-highlight-text-fill: #000000; -fx-text-fill: #fffff;");
					}
					HB.getChildren().add(TA);
				}
			}
			if(Path.get(Path.size() - 2).contentEquals(Path.get(Path.size() - 1)) == false) {
				boolean flag = false;
				TA = new TextArea();
				for(int k = 0 ; k < nGoals ; k++) {
					if(DFS_Goals[k].equals(Path.get(Path.size() - 1))) {
						TA.setStyle("-fx-control-inner-background:#556B2F;  -fx-font-family: Consolas; -fx-font-weight:Bold; -fx-highlight-fill: #ffffff; -fx-highlight-text-fill: #000000; -fx-text-fill: #fffff;");
						flag = true;
						break;
					}
				}
				if(!flag)
					TA.setStyle("-fx-control-inner-background:#FFDAB9; -fx-font-family:  Consolas; -fx-font-weight:Bold; -fx-highlight-fill: #ffffff; -fx-highlight-text-fill: #000000; -fx-text-fill: #fffff;");
				TA.setText(Path.get(Path.size() - 1) + "\n GOAL ");
				HB.getChildren().add(TA);
				scroll.setContent(HB);
			}
			
			cost.setText(""+Cost);
			TF.setText("Optimal2All");
	}
	catch(Exception E) {
		Alert A = new Alert(AlertType.ERROR);
		A.setTitle("Input fields Error!");
		A.show();
	   }
	}

}
