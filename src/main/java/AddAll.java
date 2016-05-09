package main.java;
/**the class AddAll is extended from the abstract class Button
 * it is a "ADDALL" button
 * **/
public class AddAll extends Button{
	private Episode episode;
	
	//constructor
	public AddAll(MainApplet pare, Episode epi, int row, int col, int wid, int hei, String lab){
		super(pare, row, col, wid, hei, lab);
		this.episode=epi;
	}
	
	//button function : add every character in the episode into the big circle
	public void function(){
		for(Character chara:episode.characters){
			episode.bc.addNodes(chara);
		}
		episode.bc.in=false;
	}

}
