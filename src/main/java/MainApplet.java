package main.java;

import java.util.ArrayList;
import java.util.Random;

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
	private Ani ani;
	private String path = "main/resources/";
	JSONObject data;
	JSONArray nodes, links;
	private int cur_episode;
	private Character chara_drag;
	private ArrayList<Episode> episodes;
	String title;

	Random r=new Random();
	
	private final static int width = 1200, height = 650;

	public void setup() {	
		Ani.init(this);
		this.ellipseMode(this.RADIUS);
		PFont f = createFont("Georgia",32);
		this.textFont(f);
		episodes = new ArrayList<Episode>();
		for(int i=1;i<8;i++){
			Episode epi = new Episode(this,i);
			epi.setPath(path);
			epi.setFile("starwars-episode-"+i+"-interactions.json");
			episodes.add(epi);
		}

		cur_episode=1;
		title="Starwars Episode "+cur_episode;
		


		
		size(width, height);
		smooth();
		loadData();
		
	}			
	

	public void keyPressed(){
		if(keyCode==KeyEvent.VK_NUMPAD1){
			cur_episode=1;
		}else if(keyCode==KeyEvent.VK_NUMPAD2){
			cur_episode=2;
		}else if(keyCode==KeyEvent.VK_NUMPAD3){
			cur_episode=3;
		}else if(keyCode==KeyEvent.VK_NUMPAD4){
			cur_episode=4;
		}else if(keyCode==KeyEvent.VK_NUMPAD5){
			cur_episode=5;
		}else if(keyCode==KeyEvent.VK_NUMPAD6){
			cur_episode=6;
		}else if(keyCode==KeyEvent.VK_NUMPAD7){
			cur_episode=7;
		}
		title="Starwars Episode "+cur_episode;
	}

	public void CLEAR(){
		Episode epi=episodes.get(this.cur_episode-1);
		
		for(Character chara: epi.characters){
			epi.bc.deleteNodes(chara);
			chara.reset();
			chara.click=false;
		}		
		epi.bc.in=false;	

	}

	public void draw() {
		background(255);
		episodes.get(this.cur_episode-1).display();
		textSize(50);
		fill(0,0,0);
		text(title, 440, 70, 100);
	}

	private void loadData(){
		Episode epi;
		
		for(int i=0;i<7;i++){
			epi=episodes.get(i);
			data = loadJSONObject(epi.getPath()+epi.getFile());
			epi.loadData(data);
		}
	}
	
	public boolean inside_Win(){
		if(this.mouseX >= 0 && this.mouseX <= this.width && this.mouseY >= 0 && this.mouseY <= this.height){
			return true;
		}
		else{
			return true;
		}
	}
	
	public void mousePressed() {
		for(Character chara: episodes.get(this.cur_episode-1).characters){
			if(chara.over()){
				chara.click=true;
				this.chara_drag=chara;
			}
			else{
				chara.click=false;
			}
		}

	}

	public void mouseDragged() {
		if(this.inside_Win()){
			if(this.chara_drag!=null){
				if(this.chara_drag.click){
					this.chara_drag.x=this.mouseX;
					this.chara_drag.y=this.mouseY;
					if(this.chara_drag.inside_cir()){
						episodes.get(this.cur_episode-1).bc.in=true;
						episodes.get(this.cur_episode-1).bc.rgb=this.chara_drag.rgb;
					}
					else{
						episodes.get(this.cur_episode-1).bc.in=false;
					}
				}				
			}
		}
	}

	public void mouseReleased() {
		Episode epi=episodes.get(this.cur_episode-1);
		if(this.inside_Win()){
			if(this.chara_drag!=null){
				if(this.chara_drag.click){
					if(this.chara_drag.inside_cir()){
						epi.bc.addNodes(this.chara_drag);
					}
					else{
						epi.bc.deleteNodes(this.chara_drag);
						this.chara_drag.reset();
					}
				this.chara_drag.click=false;
				epi.bc.in=false;				
				}				
			}
		}
		else{
			if(this.chara_drag!=null){
				if(this.chara_drag.click){
					epi.bc.deleteNodes(this.chara_drag);
					this.chara_drag.reset();
					this.chara_drag.click=false;
					epi.bc.in=false;				
				}				
			}
		}
	
	}

}
