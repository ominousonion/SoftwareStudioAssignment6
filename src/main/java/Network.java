package main.java;

import processing.core.PApplet;

/**
* This class is used for the visualization of the network.
* Depending on your implementation, you might not need to use this class or create a class on your own.
* I used the class to draw the circle and re-arrange nodes and links.
* You will need to declare other variables.
*/
public class Network {
	
	private PApplet parent;
	private int value;
	private Character target;
	private Character source;
	private BigCircle circle;
	
	public Network(PApplet parent, Character so,Character ta, int val, BigCircle bc){
		this.value = val;
		this.source = so;
		this.target = ta;
		this.parent = parent;
		this.circle = bc;
	}
	
	public Character getTarget(){
		return target;
	}
	
	public int getValue(){
		return value;
	}
	
	public void display(){
		if(target.inside){
			parent.strokeWeight(value/2);
			parent.stroke(0,70);
			parent.bezier(source.x, source.y, circle.x, circle.y,  circle.x, circle.y, target.x, target.y);	
		}
	}
	
}
