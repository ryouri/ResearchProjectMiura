import processing.core.PApplet;

public class Test extends PApplet {
	public void setup() {
		size(600, 800);
		background(0, 0, 0);
		smooth();
		strokeWeight(5);
	}

	public void draw() {
		
		
		
		noStroke();
		fill(0, 10);
		rect(0, 0, width, height);
		stroke(255);
		if (mousePressed) {
			line(mouseX, mouseY, pmouseX, pmouseY);
		}
	}
}
