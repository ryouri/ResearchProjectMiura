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

	//roop情報を受け取ってループ部分に色をつける
	void loopDraw(int i,int j,int R,int G,int B){
		fill(R,G,B,10);
		rect(Variable5_01.massW*j,Variable5_01.massH*i,Variable5_01.massW,Variable5_01.massH);
	}

	public static void main(String args[]){
		LoopDraw r = new LoopDraw();
		System.out.println("ループNo. = "+r.getLoopNumber());
		System.out.println("インスタンスカウンタ = "+LoopDraw.getLoopCounter());

		LoopDraw r2 = new LoopDraw();
		System.out.println("ループNo. = "+r2.getLoopNumber());
		System.out.println("インスタンスカウンタ = "+LoopDraw.getLoopCounter());

		System.out.println("getRoopNumber = "+getLoopNumber(r2));

	}

}
