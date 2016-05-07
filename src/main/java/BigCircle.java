package main.java;

import processing.core.PApplet;
import processing.core.PConstants;

public class BigCircle {
	
	private MainApplet parent;
	public float x, y, radius;	
	public int nodesnum;
	public int nodesorder[]=new int[50];	
	float node[][] = new float[50][2]; //node[][0]=x  node[][1]=y

	public BigCircle(MainApplet pare){
		this.x=650;
		this.y=325;
		this.radius=250;
		this.nodesnum=0;
		this.parent=pare;
	}

	public void display(){
		this.parent.noFill();
		this.parent.stroke(0,70);
		this.parent.strokeWeight(5);
		this.parent.ellipse(x, y, radius, radius);
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
