package main.java;

/**the class AddTrivial is extended from the abstract class Button
 * it is a "ADD TRIVIAL" button
 * **/
public class AddTrivial extends Button{
	private Episode episode;
	
	//constructor
	public AddTrivial(MainApplet pare, Episode epi, int row, int col, int wid, int hei, String lab){
		super(pare, row, col, wid, hei, lab);
		this.episode=epi;
	}
	
	//button function : add trivial character in the episode into the big circle
	public void function(){
		for(Character chara:episode.characters){
			if(chara.colour.equals("#FF808080")){
				episode.bc.addNodes(chara);
			}	
		}
		episode.bc.in=false;
	}
}
