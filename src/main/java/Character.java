package main.java;


import java.util.ArrayList;

import de.looksgood.ani.Ani;


/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character{
	
	public float x, y, index, radius;//position, size, index number 
	public float x_Incircle, y_Incircle;//the position of the character if the character is inside the big circle
	private float x_origin, y_origin;//default position, size
	private float rad_origin, rad_expand;//origin size, expend size
	public boolean click;//whether the character is clicked
	public boolean inside;//whether the character is inside the circle
	private MainApplet parent;//the applet
	private BigCircle circle;//the big circle
	private String name;//the name of the character
	public String colour;//the color of the character(in hex)
	public int rgb;//the color of the character(in RGB)
	
	private ArrayList<Network> targets;//the connections of the character and other characters

	//constructor
	public Character(MainApplet parent, BigCircle bc, String name, String colour, int index){

		this.parent = parent;
		this.circle = bc;
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
		this.rgb=this.parent.unhex(colour.substring(1));
		this.targets = new ArrayList<Network>();
	}
	
	
	//display the name tag of character if the character is hovered
	public void display(){
		if(over()){
			this.radius=this.rad_expand;//the character will be bigger if it got hovered
			//display the name tag
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
	
	//check if the mouse is hovering above the character
	public boolean over(){
		if( ((parent.mouseX-this.x) * (parent.mouseX-this.x)) + ((parent.mouseY-this.y) * (parent.mouseY-this.y)) <= (this.rad_origin*this.rad_origin)){
			return true;			
		}
		else{
			return false;
		}
	}
	
	//check if the character's position is inside the circle
	public boolean inside_cir(){
		if( ((circle.x-this.x) * (circle.x-this.x)) + ((circle.y-this.y) * (circle.y-this.y)) <= (circle.radius*circle.radius) ){
			return true;
		}
		else return false;
	}
	
	//character go back to its original position
	public void reset(){
		this.x=this.x_origin;
		this.y=this.y_origin;				
	}
	
	//character go back to its original position with animation
	public void moveback(){
		Ani.to(this, (float) 0.5, "x", this.x_origin);
		Ani.to(this, (float) 0.5, "y", this.y_origin);
		reset();
	}
	
	//add target character
	public void addTarget(Character target,int value){ 
		Network line = new Network(parent, this, target, value, circle);
		this.targets.add(line); 
	}
	
	//get target characters
	public ArrayList<Network> getTargets(){ 
		return this.targets; 
	}

	//get the character's name
	public String getName(){
		return this.name;
	}
}
