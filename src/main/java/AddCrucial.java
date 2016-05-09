package main.java;

/**the class AddCrucial is extended from the abstract class Button
 * it is a "ADD CRUCIAL" button
 * **/

public class AddCrucial extends Button{
	private Episode episode;
	
	//constructor
	public AddCrucial(MainApplet pare, Episode epi, int row, int col, int wid, int hei, String lab){
		super(pare, row, col, wid, hei, lab);
		this.episode=epi;
	}
	
	//button function : add crucial character in the episode into the big circle
	public void function(){
		for(Character chara:episode.characters){
			if(!chara.colour.equals("#FF808080")){
				episode.bc.addNodes(chara);
			}	
		}
		episode.bc.in=false;
	}

}
