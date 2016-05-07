package main.java;


import java.util.ArrayList;

import de.looksgood.ani.Ani;


/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character{
	
	public float x, y, index, radius;
	private float x_origin, y_origin;
	private float rad_origin, rad_expand;
	public boolean click;
	public boolean inside;
	private MainApplet parent;
	private String name;
	public String colour;
	public int id;
	
	private ArrayList<Character> targets;

	public Character(MainApplet parent,String name,String colour,int index,int id_num){

		this.parent = parent;
		this.name = name;
		this.colour = colour;
		this.index = index;
		this.rad_origin=20;
		this.rad_expand=25;
		this.x_origin=50+(index/10)*60;
		this.y_origin=50+(index%10)*60;
		this.x=this.x_origin;
		this.y=this.y_origin;
		this.click=false;
		this.inside=false;
		
		this.targets = new ArrayList<Character>();
	}
	
	

	public void display(){
		int rgb;
		if(over()){
			this.radius=this.rad_expand;
			rgb=this.parent.unhex(colour.substring(1));
			this.parent.fill(rgb);
			this.parent.rect(x+5, y-20, name.length()*20, 40, 12, 12, 12, 12);
			this.parent.textSize(26);
			this.parent.fill(255);
			this.parent.text(name, x+10, y+10);			
		}
		else{
			this.radius=this.rad_origin;
		}				
	}
	
	public boolean over(){
		if( ((parent.mouseX-this.x) * (parent.mouseX-this.x)) + ((parent.mouseY-this.y) * (parent.mouseY-this.y)) <= (this.rad_origin*this.rad_origin)){
			return true;			
		}
		else{
			return false;
		}
	}
	
	public void reset(){
		Ani.to(this, (float) 0.5, "x", this.x_origin);
		Ani.to(this, (float) 0.5, "y", this.y_origin);			
	}
	
	public void addTarget(Character target){ this.targets.add(target); }
	
	public ArrayList<Character> getTargets(){ return this.targets; }

	
}
