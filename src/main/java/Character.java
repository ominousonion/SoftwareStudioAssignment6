package main.java;

import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	
	public int x, y;
	private MainApplet parent;
	private String name;
	private String colour;
	
	private ArrayList<Character> targets;

	public Character(MainApplet parent,String name,String colour,int x,int y){

		this.parent = parent;
		this.name = name;
		this.colour = colour;
		this.x = x;
		this.y = y;
		
		this.targets = new ArrayList<Character>();
	}
	
	

	public void display(){
		this.parent.noStroke();
		this.parent.fill(50, 50, 150);
		this.parent.rect(x-name.length()*10, y-20, name.length()*20, 40, 12, 12, 12, 12);
		
		this.parent.textSize(26);
		this.parent.fill(255);
		this.parent.text(name, x-name.length()*10+5, y+10);
	}
	
	public void addTarget(Character target){ this.targets.add(target); }
	
	public ArrayList<Character> getTargets(){ return this.targets; }
	
}
