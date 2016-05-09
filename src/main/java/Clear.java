package main.java;
/**the class Clear is extended from the abstract class Button
 * it is a "CLEAR" button
 * **/
public class Clear extends Button{
	private Episode episode;
	
	public Clear(MainApplet pare, Episode epi, int row, int col, int wid, int hei, String lab){
		super(pare, row, col, wid, hei, lab);
		this.episode=epi;
	}
	
	//button function : clear every character in the episode inside the big circle
	public void function(){
		for(Character chara:episode.characters){
			episode.bc.deleteNodes(chara);
		}
		episode.bc.in=false;
	}
}
