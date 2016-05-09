package main.java;

import processing.core.PApplet;
import processing.core.PConstants;
import java.util.HashMap;
import java.util.Map.Entry;

import de.looksgood.ani.Ani;
/**the class Bigcircle is the big circle to display the connection between characters
 * users can drag and drop characters into the circle to see the connections of characters **/
public class BigCircle {
	
	private MainApplet parent;//applet
	final public float x, y, radius;//position, size	
	public boolean in;//whether there is a dragged character in the circle
	public int rgb;//color
	public int nodesnum;//the amount of characters inside the circle
	public HashMap<String,Character> nodes;//the characters inside the circle

	//constructor
	public BigCircle(MainApplet pare){
		this.x=650;
		this.y=370;
		this.radius=250;
		this.nodesnum=0;
		this.parent=pare;
		this.in=false;
		this.nodes=new HashMap<String, Character>();
	}

	//display the circle
	public void display(){
		this.parent.noFill();
		if(in){//if there is an dragged character in the circle, the color of the circle will change to the color of the dragged character
			this.parent.stroke(rgb);
		}
		else{
			this.parent.stroke(200);
		}
		this.parent.strokeWeight(5);
		this.parent.ellipse(x, y, radius, radius);
	}
	
	//add character into the circle
	public void addNodes(Character ch){
		int i=1;
		if(!ch.inside){//if the character originally is not inside the circle
			nodesnum++;
			nodes.put(ch.getName(), ch);
			ch.inside=true;
			//reset the position of characters inside circle
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
		else{//if the character originally is inside the circle
			Ani.to(ch, (float) 0.5, "x", ch.x_Incircle);
			Ani.to(ch, (float) 0.5, "y", ch.y_Incircle);
			ch.x=ch.x_Incircle;
			ch.y=ch.y_Incircle;
		}

	}
	
	//remove character from the circle
	public void deleteNodes(Character ch){
		int i=1;
		if(ch.inside){//if the character originally is inside the circle
			if(nodes.containsKey(ch.getName())){//double check
				nodesnum--;	
				nodes.remove(ch.getName());
				ch.inside=false;
				ch.moveback();//character go back to its origin position
				//reset the position of the rest characters inside the circle
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
