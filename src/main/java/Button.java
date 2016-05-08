package main.java;

public abstract class Button {
	final private float x_origin, y_origin, width_origin, height_origin;
	public float x, y, width, height;
	private float  x_pressed, y_pressed, width_pressed, height_pressed;
	private String label;
	private MainApplet parent;
	public boolean click;
	
	public Button(MainApplet pare, int row, int col, int wid, int hei, String lab){
		this.parent=pare;
		this.x_origin=row;
		this.y_origin=col;
		this.x=this.x_origin;
		this.y=this.y_origin;
		this.x_pressed=this.x_origin+2;
		this.y_pressed=this.y_origin+2;
		this.width_origin=wid;
		this.height_origin=hei;
		this.width=this.width_origin;
		this.height=this.height_origin;
		this.width_pressed=this.width_origin-4;
		this.height_pressed=this.height_origin-4;
		this.label=lab;
		this.click=false;
	}
	
	public void dislay(){
		if(over()){
			if(click){
				this.x=this.x_pressed;
				this.y=this.y_pressed;
				this.width=this.width_pressed;
				this.height=this.height_pressed;
				this.parent.noStroke();
				this.parent.fill(255,0,255);
				this.parent.rect(x, y, width, height, 12, 12, 12, 12);
				this.parent.textSize(26);
				this.parent.fill(255);
				this.parent.text(label, x+(width-parent.textWidth(label))/2, y+(height-(parent.textDescent()-parent.textAscent()))/2);
			}
			else{
				this.x=this.x_origin;
				this.y=this.y_origin;
				this.width=this.width_origin;
				this.height=this.height_origin;
				this.parent.noStroke();
				this.parent.fill(253,250,106);
				this.parent.rect(x, y, width, height, 12, 12, 12, 12);
				this.parent.textSize(26);
				this.parent.fill(255);
				this.parent.text(label, x+(width-parent.textWidth(label))/2, y+(height-(parent.textDescent()-parent.textAscent()))/2);			
			}	
		}
		else{
			this.x=this.x_origin;
			this.y=this.y_origin;
			this.width=this.width_origin;
			this.height=this.height_origin;
			this.parent.noStroke();
			this.parent.fill(200);
			this.parent.rect(x, y, width, height, 12, 12, 12, 12);
			this.parent.textSize(26);
			this.parent.fill(255);
			this.parent.text(label, x+(width-parent.textWidth(label))/2, y+(height-(parent.textDescent()-parent.textAscent()))/2);
		}	
	}
	
	public boolean over(){
		if( parent.mouseX >= this.x_origin && parent.mouseY >= this.y_origin && parent.mouseX <= this.x_origin + this.width_origin && parent.mouseY <= this.y_origin + this.height_origin){
			return true;			
		}
		else{
			return false;
		}
	}
	
	abstract public void function();
	
}
