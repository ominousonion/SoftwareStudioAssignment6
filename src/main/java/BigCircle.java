package main.java;

import processing.core.PApplet;
import processing.core.PConstants;
import java.util.HashMap;
import java.util.Map.Entry;

import de.looksgood.ani.Ani;

public class BigCircle {
	
	private MainApplet parent;
	public float x, y, radius;	
	public boolean in;
	public int nodesnum;
	public HashMap<String,Character> nodes;

	public BigCircle(MainApplet pare){
		this.x=650;
		this.y=325;
		this.radius=250;
		this.nodesnum=0;
		this.parent=pare;
		this.in=false;
		this.nodes=new HashMap<String, Character>();
	}

	public void display(){
		this.parent.noFill();
		if(in){
			this.parent.stroke(255, 255, 0);
		}
		else{
			this.parent.stroke(0,70);
		}
		this.parent.strokeWeight(5);
		this.parent.ellipse(x, y, radius, radius);
	}
	
	public void addNodes(Character ch){
		int i=1;
		if(!ch.inside){
			nodesnum++;
			nodes.put(ch.getName(), ch);
			ch.inside=true;
			for(Entry<String, Character> entry : nodes.entrySet()){
				Character chara=entry.getValue();
				chara.x_Incircle = this.x + this.radius*parent.cos(i*2*PConstants.PI/(float)nodesnum);
				chara.y_Incircle = this.y + this.radius*parent.sin(i*2*PConstants.PI/(float)nodesnum);
				Ani.to(chara, (float) 0.5, "x", chara.x_Incircle);
				Ani.to(chara, (float) 0.5, "y", chara.y_Incircle);
				chara.x=chara.x_Incircle;
				chara.y=chara.y_Incircle;
				i++;
			}			
		}
		else{
			Ani.to(ch, (float) 0.5, "x", ch.x_Incircle);
			Ani.to(ch, (float) 0.5, "y", ch.y_Incircle);
			ch.x=ch.x_Incircle;
			ch.y=ch.y_Incircle;
		}

	}
	
	public void deleteNodes(Character ch){
		int i=1;
		if(ch.inside){
			if(nodes.containsKey(ch.getName())){
				nodesnum--;	
				nodes.remove(ch.getName());
				ch.inside=false;
				for(Entry<String, Character> entry : nodes.entrySet()){
					Character chara=entry.getValue();
					chara.x_Incircle = this.x + this.radius*parent.cos(i*2*PConstants.PI/(float)nodesnum);
					chara.y_Incircle = this.y + this.radius*parent.sin(i*2*PConstants.PI/(float)nodesnum);
					Ani.to(chara, (float) 0.5, "x", chara.x_Incircle);
					Ani.to(chara, (float) 0.5, "y", chara.y_Incircle);
					chara.x=chara.x_Incircle;
					chara.y=chara.y_Incircle;
					i++;
				}
			}			
		}


	}
}
