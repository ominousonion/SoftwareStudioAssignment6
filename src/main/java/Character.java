package main.java;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character implements MouseListener{
	
	public int x, y, index, radius;
	public boolean click;
	private MainApplet parent;
	private String name;
	private String colour;
	
	private ArrayList<Character> targets;

	public Character(MainApplet parent,String name,String colour,int index){

		this.parent = parent;
		this.name = name;
		this.colour = colour;
		this.index = index;
		
		this.x=50+(index/10-1)*60;
		this.y=50+(index%10-1)*60;
		
		this.targets = new ArrayList<Character>();
	}
	
	

	public void display(){
		
		if(click) radius=15;
		else radius=12;
		this.parent.noStroke();
		this.parent.fill(50, 50, 150);
		this.parent.ellipse(x, y, radius, radius);
		
		if(click){
			this.parent.rect(x+5, y-20, name.length()*20, 40, 12, 12, 12, 12);
		
			this.parent.textSize(26);
			this.parent.fill(255);
			this.parent.text(name, x+10, y+10);
		}
		
	}
	
	
	public void addTarget(Character target){ this.targets.add(target); }
	
	public ArrayList<Character> getTargets(){ return this.targets; }



	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(click==true&&((x-e.getX())*(x-e.getX())+(y-e.getY())*(y-e.getY())<=radius*radius)) click=false;
		else if(click==false&&((x-e.getX())*(x-e.getX())+(y-e.getY())*(y-e.getY())<=radius*radius)) click=true;
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(click){
			x=e.getX();
			y=e.getY();
		}
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		click=false;
	}
	
}
