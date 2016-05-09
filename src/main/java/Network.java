package main.java;

import processing.core.PApplet;

/**
* This class is used for the visualization of the network.
* Depending on your implementation, you might not need to use this class or create a class on your own.
* I used the class to draw the circle and re-arrange nodes and links.
* You will need to declare other variables.
*/

/**I use the class network as the connection between two characters in the same episode.
 * the class Network contains the source character, target character and how much these characters interact 
 * **/

public class Network {
	
	private PApplet parent;//the applet
	private int value;//the value of the connection
	private Character target;//target character
	private Character source;//source character
	private BigCircle circle;//the big circle
	
	//constructor
	public Network(PApplet parent, Character so,Character ta, int val, BigCircle bc){
		this.value = val;
		this.source = so;
		this.target = ta;
		this.parent = parent;
		this.circle = bc;
	}
	
	//get target
	public Character getTarget(){
		return target;
	}
	
	//get value
	public int getValue(){
		return value;
	}
	
	//display the connection
	public void display(){
		//this method is called by source character when the source character is inside the big circle
		if(target.inside){//check the target is inside the big circle
			if(value < 10){
				parent.strokeWeight(value);
			}
			else{
				parent.strokeWeight(10);
			}
			//if there are too many characters inside the circle, the users might not be able to distinguish the line between the characters.
			//therefore, the below is to let the color of line be the same as the character when the character is hovered by users
			if(source.over()){
				parent.stroke(source.rgb);
			}
			else if(target.over()){
				parent.stroke(target.rgb);
			}
			else{
				parent.stroke(0,70);				
			}
			parent.noFill();
			parent.bezier(source.x, source.y, ((source.x+target.x)/2+circle.x)/2, ((source.y+target.y)/2+circle.y)/2,  ((source.x+target.x)/2+circle.x)/2, ((source.y+target.y)/2+circle.y)/2, target.x, target.y);	
		}
	}
	
}
