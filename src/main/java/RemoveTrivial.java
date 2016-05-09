package main.java;

/**the class RemoveTrivial is extended from the abstract class Button
 * it is a "REMOVE TRIVIAL" button
 * **/
public class RemoveTrivial extends Button{
	private Episode episode;
	
	//constructor
	public RemoveTrivial(MainApplet pare, Episode epi, int row, int col, int wid, int hei, String lab){
		super(pare, row, col, wid, hei, lab);
		this.episode=epi;
	}
	
	//button function : remove trivial characters in the episode inside the big circle
	public void function(){
		for(Character chara:episode.characters){
			if(chara.colour.equals("#FF808080")){
				episode.bc.deleteNodes(chara);
			}	
		}
		episode.bc.in=false;
	}
}
