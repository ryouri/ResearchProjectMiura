package sample;
/*
 * ループの色を格納
 */

public class RoopColor {

	public static void main(String args[]){

		int roopCount = RoopDraw.getCounter();
		int roopColorList[][] = {{1,213,132,224},{2,84,77,203},{3,82,162,197},
							 	 {4,243,213,26},{5,40,175,148},{6,51,96,69},
							 	 {7,239,143,15},{8,239,117,152},{9,204,0,0}
								};

		int roopR = roopColorList[roopCount][1];
		int roopG = roopColorList[roopCount][2];
		int roopB = roopColorList[roopCount][3];


	}

}
