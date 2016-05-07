package main.java;

import processing.core.PApplet;
import processing.data.JSONObject;
import processing.data.JSONArray;
import java.util.ArrayList;

/**
* This class is for sketching outcome using Processing
* You can do major UI control and some visualization in this class.  
*/
@SuppressWarnings("serial")
public class MainApplet extends PApplet{
	private String path = "main/resources/";
	private String file = "starwars-episode-1-interactions.json";
	JSONObject data;
	JSONArray nodes, links;
	private ArrayList<Episode> episode;
	
	private final static int width = 1200, height = 650;
	
	public void setup() {
		Episode epi; 
		
		for(int i=1;i<8;i++){
			epi = new Episode();
			epi.setPath(path);
			epi.setFile("starwars-episode-"+i+"-interactions.json");
		}
		
		size(width, height);
		smooth();
		loadData();
		
		
	}

	public void draw() {

	}

	private void loadData(){
		
	}

}
