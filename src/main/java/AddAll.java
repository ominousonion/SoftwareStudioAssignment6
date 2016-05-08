package main.java;

public class AddAll extends Button{
	private Episode episode;
	
	public AddAll(MainApplet pare, Episode epi, int row, int col, int wid, int hei, String lab){
		super(pare, row, col, wid, hei, lab);
		this.episode=epi;
	}
	
	
	public void function(){
		for(Character chara:episode.characters){
			episode.bc.addNodes(chara);
		}
		episode.bc.in=false;
	}

}
