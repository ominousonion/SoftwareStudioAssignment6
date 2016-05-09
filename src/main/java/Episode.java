package main.java;

import java.util.ArrayList;

import main.java.Character;
import processing.core.PApplet;
import processing.data.*;

/**
 * the class Episode is the episode of star war, there are different characters in different episode.
 * in this class, there is an array list to contain the characters in the episode.
 * Also, every episode has its own big circle,"ADDALL","CLEAR" button.
 *  **/
public class Episode {
	public ArrayList<Character> characters;//characters appear in the episode
	public BigCircle bc;// big circle
	//data path
	private String path;
	private String file;
	private MainApplet parent;//the applet
	public int id;//the episode number
	JSONArray nodes, links;
	public Button addAll, clear;//buttons
	
	//constructor 
	public Episode(MainApplet pare,int num){
		this.parent=pare;
		this.id=num;
		characters = new ArrayList<Character>();
		//create big circle
		this.bc=new BigCircle(pare);
		//create buttons
		this.addAll = new AddAll(pare, this, 950, 50, 200, 50, "ADD ALL");
		this.clear = new Clear(pare, this, 950, 150, 200, 50, "CLEAR");
	}
	
	//set data path
	public void setPath(String pa){
		this.path=pa;
	}
	
	//get data path
	public String getPath(){
		return this.path;
	}
	
	//set file name
	public void setFile(String fi){
		this.file=fi;
	}
	
	//get file name
	public String getFile(){
		return this.file;
	}
	
	//display the object in the episode
	public void display(){
		int rgb;
		
		//display big circle
		bc.display();
		//display buttons
		addAll.dislay();
		clear.dislay();
		//display the connection between characters if the characters are inside the big circle
		for(Character chara:characters){
			for(Network line:chara.getTargets()){
				if(chara.inside){
					line.display();
				}
			}
		}
		//display characters
		for(Character chara:characters){
			this.parent.noStroke();
			rgb=parent.unhex(chara.colour.substring(1));
			this.parent.fill(rgb);
			this.parent.ellipse(chara.x, chara.y, chara.radius, chara.radius);	
		}
		//display character name tag if the mouse hovering above it
		for(Character chara:characters){
			chara.display();
		}
	}
	
	//load data from JSON file
	public void loadData(JSONObject data){
		nodes = data.getJSONArray("nodes");
		links = data.getJSONArray("links");

		for(int i=0; i<nodes.size(); i++){
			JSONObject node = nodes.getJSONObject(i);
			Character c = new Character(parent, bc, node.getString("name"), node.getString("colour"), i);
			characters.add(c);
		}

		for(int i=0; i<links.size(); i++){
			JSONObject link = links.getJSONObject(i);
			int source = link.getInt("source");
			int target = link.getInt("target");
			int value = link.getInt("value");
			characters.get(source).addTarget(characters.get(target), value);	
		}
		
	}
	
}
