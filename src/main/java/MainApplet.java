package main.java;

import java.util.ArrayList;
import java.util.Random;
import de.looksgood.ani.Ani;
import processing.core.PApplet;
import processing.data.JSONObject;
import processing.data.JSONArray;
import java.util.ArrayList;




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

	private ArrayList<Episode> episodes;

	Random r=new Random();

	
	private final static int width = 1200, height = 650;
	
	public void setup() {	
		Ani.init(this);
		episodes = new ArrayList<Episode>();
		for(int i=1;i<8;i++){
			Episode epi = new Episode(this,i);
			epi.setPath(path);
			epi.setFile("starwars-episode-"+i+"-interactions.json");
			episodes.add(epi);
		}
		cur_episode=1;
		size(width, height);
		smooth();
		loadData();
		
		
	}

	public void draw() {
		background(255);
		episodes.get(this.cur_episode-1).display();
	}

	private void loadData(){
		Episode epi;
		
		for(int i=0;i<7;i++){
			epi=episodes.get(i);
			data = loadJSONObject(epi.getPath()+epi.getFile());
			epi.loadData(data);
		}
	}
	
	public void mousePressed() {
		for(Character chara: episodes.get(this.cur_episode-1).characters){
			if(chara.over()){
				chara.click=true;
			}
			else{
				chara.click=false;
			}
		}

	}

	public void mouseDragged() {
		for(Character chara: episodes.get(this.cur_episode-1).characters){
			if(chara.click){
				chara.x=this.mouseX;
				chara.y=this.mouseY;
			}
		}

	}

	public void mouseReleased() {
		for(Character chara: episodes.get(this.cur_episode-1).characters){
			chara.click=false;
			chara.reset();
		}
	}

}
