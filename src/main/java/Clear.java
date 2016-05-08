package main.java;

public class Clear extends Button{
	private Episode episode;
	
	public Clear(MainApplet pare, Episode epi, int row, int col, int wid, int hei, String lab){
		super(pare, row, col, wid, hei, lab);
		this.episode=epi;
	}
	
	
	public void function(){
		for(Character chara:episode.characters){
			episode.bc.deleteNodes(chara);
		}
		episode.bc.in=false;
	}
}
