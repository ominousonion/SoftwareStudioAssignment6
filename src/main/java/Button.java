package main.java;

/**this abstract class Button is the button 
 * which contains the attributions about the button **/

public abstract class Button {
	final private float x_origin, y_origin, width_origin, height_origin;//button's default and origin position, size
	public float x, y, width, height;//button's position position and size
	private float  x_pressed, y_pressed, width_pressed, height_pressed;//button's position size and position when the button is pressed
	private String label;//button's name
	private MainApplet parent;//the applet
	public boolean click;//whether the button is clicked
	
	//constructor
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
		//when the button is pressed, it will get a little smaller so it will look like it is pressed 
	}
	
	//display the button
	public void dislay(){
		if(over()){//if mouse is hovering above the button
			if(click){//if the button is clicked, its color will be pink and its size will smaller
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
			else{//otherwise the color of the button is yellow
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
		else{//if the mouse isn't hovering above the button, display default form of the button
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
	
	//to check if the mouse is hovering above the button
	public boolean over(){
		if( parent.mouseX >= this.x_origin && parent.mouseY >= this.y_origin && parent.mouseX <= this.x_origin + this.width_origin && parent.mouseY <= this.y_origin + this.height_origin){
			return true;			
		}
		else{
			return false;
		}
	}
	
	abstract public void function();//abstract class : the function of a button depend on what button it is
	
}
