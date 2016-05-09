package main.java;

import de.looksgood.ani.Ani;
import processing.core.*;
import processing.data.JSONObject;
import processing.data.JSONArray;
import java.util.ArrayList;
import java.awt.event.KeyEvent;



/**
* This class is for sketching outcome using Processing
* You can do major UI control and some visualization in this class.  
*/
@SuppressWarnings("serial")
public class MainApplet extends PApplet{	
	private Ani ani;//animation
	private String path = "main/resources/";//data path
	JSONObject data;//data
	JSONArray nodes, links;//used for loading data
	private int cur_episode;//the episode displayed currently
	private Character chara_drag;//the character dragged by mouse
	private ArrayList<Episode> episodes;//episodes, there are total 7 episodes
	String title;//the title of the episode

	
	private final static int width = 1200, height = 650;

	public void setup() {	
		Ani.init(this);
		this.ellipseMode(this.RADIUS);//set the ellipse mode
		//set the text font and size
		PFont f = createFont("Georgia",32);
		this.textFont(f);
		episodes = new ArrayList<Episode>();
		//create 7 episodes and put them into array list
		for(int i=1;i<8;i++){
			Episode epi = new Episode(this,i);
			epi.setPath(path);
			epi.setFile("starwars-episode-"+i+"-interactions.json");
			episodes.add(epi);
		}
		//default episode to display
		cur_episode=1;
		//set the title
		title="Starwars Episode "+cur_episode;
	
		size(width, height);
		smooth();
		//load data
		loadData();
		
	}			
	
	//put every character in all episode back to their origin position and initialize the big circles in every episode
	public void initialize(){
		for(int i=0;i<7;i++){
			for(Character chara:episodes.get(i).characters){
				chara.moveback();
				episodes.get(i).bc.deleteNodes(chara);//clear the character in big circle
				episodes.get(i).bc.in=false;//there is no dragged character in the circle
				chara.click=false;//character is not clicked
				chara.inside=false;//character is not inside the circle
			}
		}
	}

	//this method let users can switch to different episode with the number keys on keyboard
	public void keyPressed(){
		if(keyCode==KeyEvent.VK_NUMPAD1){			
			if(cur_episode!=1){
				initialize();
			}
			cur_episode=1;
		}else if(keyCode==KeyEvent.VK_NUMPAD2){
			if(cur_episode!=2){
				initialize();
			}
			cur_episode=2;
		}else if(keyCode==KeyEvent.VK_NUMPAD3){			
			if(cur_episode!=3){
				initialize();
			}
			cur_episode=3;
		}else if(keyCode==KeyEvent.VK_NUMPAD4){
			if(cur_episode!=4){
				initialize();
			}
			cur_episode=4;
		}else if(keyCode==KeyEvent.VK_NUMPAD5){
			if(cur_episode!=5){
				initialize();
			}
			cur_episode=5;
		}else if(keyCode==KeyEvent.VK_NUMPAD6){
			if(cur_episode!=6){
				initialize();
			}
			cur_episode=6;
		}else if(keyCode==KeyEvent.VK_NUMPAD7){
			if(cur_episode!=7){
				initialize();
			}
			cur_episode=7;
		}
		title="Starwars Episode "+cur_episode;
	}


	//display current episode
	public void draw() {
		clear();
		background(255);	
		episodes.get(this.cur_episode-1).display();
		textSize(50);
		fill(0,0,0);
		text(title, 440, 70, 100);
	}

	//load data from JSON file
	private void loadData(){
		Episode epi;
		
		for(int i=0;i<7;i++){
			epi=episodes.get(i);
			data = loadJSONObject(epi.getPath()+epi.getFile());
			epi.loadData(data);
		}
	}
	
	//check the mouse is inside the window
	public boolean inside_Win(){
		if(this.mouseX >= 0 && this.mouseX <= this.width && this.mouseY >= 0 && this.mouseY <= this.height){
			return true;
		}
		else{
			return false;
		}
	}
	
	//when users click the mouse...
	public void mousePressed() {
		for(Character chara: episodes.get(this.cur_episode-1).characters){
			if(chara.over()){//if the mouse hover above the character
				chara.click=true;//the character is clicked
				this.chara_drag=chara;//store the clicked character
			}
			else{
				chara.click=false;
			}
		}
		episodes.get(this.cur_episode-1).mousePressed_button();//check button
	}

	//when users drag the mouse
	public void mouseDragged() {
		if(this.inside_Win()){//if the mouse is inside window
			if(this.chara_drag!=null){//prevent dragged character is null pointer
				if(this.chara_drag.click){//if the dragged character is clicked
					//the position of the character will be determined by mouse's position
					this.chara_drag.x=this.mouseX;
					this.chara_drag.y=this.mouseY;
					if(this.chara_drag.inside_cir()){//if the character is dragged inside the big circle
						episodes.get(this.cur_episode-1).bc.in=true;//there is a dragged character in the circle
						episodes.get(this.cur_episode-1).bc.rgb=this.chara_drag.rgb;//let big circle know the color of dragged character
					}
					else{
						episodes.get(this.cur_episode-1).bc.in=false;
					}
				}				
			}
		}
		episodes.get(this.cur_episode-1).mouseDragged_button();//check button
	}

	//when users release mouse
	public void mouseReleased() {
		Episode epi=episodes.get(this.cur_episode-1);
		if(this.inside_Win()){//if the mouse inside window
			if(this.chara_drag!=null){//prevent dragged character from being a null pointer
				if(this.chara_drag.click){//if the dragged character is clicked
					if(this.chara_drag.inside_cir()){//if the dragged is inside the circle
						epi.bc.addNodes(this.chara_drag);//add the character into the circle
					}
					else{
						if(this.chara_drag.inside){//if dragged character is dragged out the circle
							epi.bc.deleteNodes(this.chara_drag);//remove character from the circle
						}
						else{
							this.chara_drag.moveback();//character move to its origin position
						}
						
					}
				this.chara_drag.click=false;//the dragged is not clicked
				epi.bc.in=false;//no dragged, no dragged character in the circle				
				}				
			}
			epi.mouseReleased_button();//check button
		}
		else{//the mouse is outside the window
			if(this.chara_drag!=null){//prevent dragged character from being null pointer
				if(this.chara_drag.click){
					epi.bc.deleteNodes(this.chara_drag);
					this.chara_drag.moveback();
					this.chara_drag.click=false;
					epi.bc.in=false;				
				}				
			}
		}
	
	}

}
