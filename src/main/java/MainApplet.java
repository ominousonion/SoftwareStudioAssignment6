package main.java;

import java.util.ArrayList;
import java.util.Random;

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
	JSONObject data;
	JSONArray nodes, links;

	private ArrayList<Episode> episodes;

	Random r=new Random();

	
	private final static int width = 1200, height = 650;
	
	public void setup() {	
		episodes = new ArrayList<Episode>();
		for(int i=1;i<8;i++){
			Episode epi = new Episode(this,i);
			epi.setPath(path);
			epi.setFile("starwars-episode-"+i+"-interactions.json");
			episodes.add(epi);
		}
		
		size(width, height);
		smooth();
		loadData();
		
		
	}

	public void draw() {
		background(255);
		
		
	}

	private void loadData(){
		Episode epi;
		
		for(int i=0;i<7;i++){
			epi=episodes.get(i);
			data = loadJSONObject(epi.getPath()+epi.getFile());
			epi.loadData(data);
		}
	}

}
