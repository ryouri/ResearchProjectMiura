package sample;

import processing.core.PApplet;

public class LoopDraw extends PApplet{

	static int loopCounter = 0;
	int loopNumber;

	LoopDraw(){
		loopNumber = loopCounter;
		loopCounter++;
	}

	static int getLoopCounter(){
		return loopCounter;
	}

	int getLoopNumber(){
		return loopNumber;
	}

	static int getLoopNumber(LoopDraw s){
		return s.getLoopNumber();
	}

	static int loopCount = 0;//LoopDraw.getLoopCounter();

	//色情報
	static int loopColorList[][] = {{1,213,132,224},{2,84,77,203},{3,82,162,197},
						 	 {4,243,213,26},{5,40,175,148},{6,51,96,69},
						 	 {7,239,143,15},{8,239,117,152},{9,204,0,0}
							};

	static int loopR = loopColorList[loopCount][1];
	static int loopG = loopColorList[loopCount][2];
	static int loopB = loopColorList[loopCount][3];

	//色情報取得
	public static int[] getLoopColor(){
		//int r = loopR;
		//int g = loopG;
		//int b = loopB;
		//fill(r,g,b,10);
		int data[] = new int[3];
		data[0] = loopR;
		data[1] = loopG;
		data[2] = loopB;
		return data;
	}

	//roop情報を受け取ってループ部分に色をつける
	void loopDraw(int i,int j,int R,int G,int B){
		fill(R,G,B,10);
		rect(Variable5_01.massW*j,Variable5_01.massH*i,Variable5_01.massW,Variable5_01.massH);
	}

	public static void main(String args[]){
		int loopCount = 0;
		LoopDraw r = new LoopDraw();
		System.out.println("ループNo. = "+r.getLoopNumber());
		System.out.println("インスタンスカウンタ = "+LoopDraw.getLoopCounter());

		LoopDraw r2 = new LoopDraw();
		System.out.println("ループNo. = "+r2.getLoopNumber());
		System.out.println("インスタンスカウンタ = "+LoopDraw.getLoopCounter());

		//System.out.println("getRoopNumber = "+getLoopNumber(r2));
		new LoopDraw();
		System.out.println("現在のループの個数は"+LoopDraw.getLoopCounter());
		int data[];
		data = getLoopColor();
		loopCount = getLoopCounter();
		System.out.println(loopCount);
		System.out.println(data[0]);

		for(int i = 0; i<3; i++){
			new LoopDraw();
			loopCount = LoopDraw.getLoopCounter();
			System.out.println("loopcount = "+loopCount);
			System.out.println(LoopDraw.getLoopCounter());
		}

	}

}
