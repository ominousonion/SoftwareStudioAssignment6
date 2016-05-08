package main.java;

import java.util.ArrayList;

import main.java.Character;
import processing.core.PApplet;
import processing.data.*;


public class Episode {
	public ArrayList<Character> characters;
	public BigCircle bc;
	private String path;
	private String file;
	private MainApplet parent;
	public int id;
	JSONArray nodes, links;
	public Button addAll, clear;
	
	public Episode(MainApplet pare,int num){
		this.parent=pare;
		this.id=num;
		characters = new ArrayList<Character>();
		this.bc=new BigCircle(pare);
		this.addAll = new AddAll(pare, this, 950, 50, 200, 50, "ADD ALL");
		this.clear = new Clear(pare, this, 950, 150, 200, 50, "CLEAR");
	}
	
	public void setPath(String pa){
		this.path=pa;
	}
	
	public String getPath(){
		return this.path;
	}
	
	public void setFile(String fi){
		this.file=fi;
	}
	
	public String getFile(){
		return this.file;
	}
	
	public void display(){
		int rgb;

		bc.display();
		addAll.dislay();
		clear.dislay();
		for(Character chara:characters){
			for(Network line:chara.getTargets()){
				if(chara.inside){
					line.display();
				}
			}
		}
		for(Character chara:characters){
			this.parent.noStroke();
			rgb=parent.unhex(chara.colour.substring(1));
			this.parent.fill(rgb);
			this.parent.ellipse(chara.x, chara.y, chara.radius, chara.radius);	
		}
		
		for(Character chara:characters){
			chara.display();
		}
	}
	
	
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
