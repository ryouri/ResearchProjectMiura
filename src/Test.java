import processing.core.PApplet;

public class Test extends PApplet {
	public void setup() {
		size(400, 400);
		background(255, 255, 0);
		smooth();
		strokeWeight(5);

		System.out.println("Hello World");
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
