package sample;
/*
 * ループの色を格納
 */

import processing.core.PApplet;

public class LoopColor extends PApplet{


		int loopCount = LoopDraw.getLoopCounter();
		int loopColorList[][] = {{1,213,132,224},{2,84,77,203},{3,82,162,197},
							 	 {4,243,213,26},{5,40,175,148},{6,51,96,69},
							 	 {7,239,143,15},{8,239,117,152},{9,204,0,0}
								};

		int loopR = loopColorList[loopCount][1];
		int loopG = loopColorList[loopCount][2];
		int loopB = loopColorList[loopCount][3];

	public void getLoopColor(){
		int r = loopR;
		int g = loopG;
		int b = loopB;
		fill(r,g,b,10);
	}

}
