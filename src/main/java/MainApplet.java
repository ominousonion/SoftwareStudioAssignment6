package main.java;

import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

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
	private ArrayList<Character> characters;
	Random r=new Random();
	
	private final static int width = 1200, height = 650;
	
	public void setup() {

		size(width, height);
		characters = new ArrayList<Character>();
		smooth();
		loadData();
		
	}

	public void draw() {
		background(255);
		
		stroke(60, 119, 119);
		strokeWeight(1);
		for(Character ch: this.characters){
			for(Character tar: ch.getTargets())
				line(ch.x, ch.y, tar.x, tar.y);
		}
		
		for(Character character: this.characters)
			character.display();
	}

	private void loadData(){
		data = loadJSONObject(path+file);
		nodes = data.getJSONArray("nodes");
		links = data.getJSONArray("links");

		System.out.println("Number of nodes: " + nodes.size());
		System.out.println("Number of links: " + links.size());

		for(int i=0; i<nodes.size(); i++){
			JSONObject node = nodes.getJSONObject(i);
			Character c=new Character(this, node.getString("name"),node.getString("colour"), r.nextInt(1100), r.nextInt(600));
			characters.add(c);
		}

		for(int i=0; i<links.size(); i++){
			JSONObject link = links.getJSONObject(i);
			characters.get(link.getInt("source")).addTarget(characters.get(link.getInt("target")));
		}
	}

}
