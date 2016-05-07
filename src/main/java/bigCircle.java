package main.java;

import processing.core.PApplet;
import processing.core.PConstants;

public class bigCircle {
	
	private MainApplet parent;
	public float x=650, y=325, radius=250;	
	public int nodesnum=0;
	public int nodesorder[]=new int[50];	
	float node[][] = new float[50][2]; //node[][0]=x  node[][1]=y

	public bigCircle(MainApplet pare){
		this.parent=pare;
	}

	public void display(){
		this.parent.noFill();
		this.parent.stroke(0,70);
		this.parent.strokeWeight(5);
		this.parent.ellipse(x, y, radius*2, radius*2);
	}
	
	public boolean insideCircle(float x,float y){
		if((x-this.x)*(x-this.x)+(y-this.y)*(y-this.y)<=radius*radius) return true;
		else return false;
	}
	
	public void addNodes(int index){
		nodesnum++;
		nodesorder[nodesnum]=index;
		for(int i=0;i<nodesnum;i++){
			node[i][0]=x+radius*PApplet.cos(i*2*PConstants.PI/nodesnum);
			node[i][1]=y+radius*PApplet.sin(i*2*PConstants.PI/nodesnum);
			System.out.print(node[i][0]);System.out.print(node[i][1]);System.out.println();
		}
	}
	
	public void deleteNodes(int index){
		int i;
		nodesnum--;
		for(i=0;nodesorder[i]!=index;i++)
		for(;i<nodesnum;i++) nodesorder[i]=nodesorder[i+1];
		for(i=0;i<nodesnum;i++){
			node[i][0]=x+radius*PApplet.cos(i*2*PConstants.PI/nodesnum);
			node[i][1]=y+radius*PApplet.sin(i*2*PConstants.PI/nodesnum);
			System.out.print(node[i][0]);System.out.print(node[i][1]);System.out.println();
		}
	}
}
