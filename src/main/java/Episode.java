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
	public Button addAll, clear, crucial, remove_crucial, trivial, remove_trivial;//buttons
	
	//constructor 
	public Episode(MainApplet pare,int num){
		this.parent=pare;
		this.id=num;
		characters = new ArrayList<Character>();
		//create big circle
		this.bc=new BigCircle(pare);
		//create buttons
		this.addAll = new AddAll(pare, this, 925, 50, 250, 50, "ADD ALL");
		this.clear = new Clear(pare, this, 925, 150, 250, 50, "CLEAR");
		this.crucial = new AddCrucial(pare, this, 925, 250, 250, 50, "ADD CRUCIAL");
		this.remove_crucial = new RemoveCrucial(pare, this, 925, 350, 250, 50, "REMOVE CRUCIAL");
		this.trivial = new AddTrivial(pare, this, 925, 450, 250, 50, "ADD TRIVIAL");
		this.remove_trivial = new RemoveTrivial(pare, this, 925, 550, 250, 50, "REMOVE TRIVIAL");
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
		crucial.dislay();
		remove_crucial.dislay();
		trivial.dislay();
		remove_trivial.dislay();
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
	
	public void mousePressed_button(){
		if(addAll.over()){//if the mouse hover above the "ADDALL" button
			addAll.click=true;//the button is clicked (button's function will be triggered only when the mouse is release)
		}
		else if(clear.over()){// the same as the "ADDALL" button
			clear.click=true;
		}
		else if(crucial.over()){// the same as the "ADDALL" button
			crucial.click=true;
		}
		else if(remove_crucial.over()){// the same as the "ADDALL" button
			remove_crucial.click=true;
		}
		else if(trivial.over()){// the same as the "ADDALL" button
			trivial.click=true;
		}
		else if(remove_trivial.over()){// the same as the "ADDALL" button
			remove_trivial.click=true;
		}
		else{//no button is clicked
			addAll.click=false;
			clear.click=false;
			crucial.click=false;
			remove_crucial.click=false;
			trivial.click=false;
			remove_trivial.click=false;
		}
	}
	
	public void mouseDragged_button(){
		if(addAll.click){//when users dragging on the button...
			if(!addAll.over()){//if the mouse is not hovering above the button 
				addAll.click=false;
			}
		}
		else if(clear.click){//the same as "ADDALL" button
			if(!clear.over()){
				clear.click=false;
			}
		}
		else if(crucial.click){//the same as "ADDALL" button
			if(!crucial.over()){
				crucial.click=false;
			}
		}
		else if(remove_crucial.click){//the same as "ADDALL" button
			if(!remove_crucial.over()){
				remove_crucial.click=false;
			}
		}
		else if(trivial.click){//the same as "ADDALL" button
			if(!trivial.over()){
				trivial.click=false;
			}
		}
		else if(remove_trivial.click){//the same as "ADDALL" button
			if(!remove_trivial.over()){
				remove_trivial.click=false;
			}
		}
	}
	
	public void mouseReleased_button(){
		if(addAll.click){//if the button is clicked and released
			addAll.function();//activate button function
			addAll.click=false;//button no longer being clicked
		}
		else if(clear.click){//the same as "ADDALL" button
			clear.function();
			clear.click=false;
		}
		else if(crucial.click){//the same as "ADDALL" button
			crucial.function();
			crucial.click=false;
		}
		else if(remove_crucial.click){//the same as "ADDALL" button
			remove_crucial.function();
			remove_crucial.click=false;
		}
		else if(trivial.click){//the same as "ADDALL" button
			trivial.function();
			trivial.click=false;
		}
		else if(remove_trivial.click){//the same as "ADDALL" button
			remove_trivial.function();
			remove_trivial.click=false;
		}
	}
	
}
