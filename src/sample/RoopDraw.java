package sample;

import processing.core.PApplet;

public class RoopDraw extends PApplet{

	static int roopCounter = 0;
	int roopNumber;

	RoopDraw(){
		roopNumber = roopCounter;
		roopCounter++;
	}

	static int getCounter(){
		return roopCounter;
	}

	int getRoopNumber(){
		return roopNumber;
	}

	static int getRoopNumber(RoopDraw s){
		return s.getRoopNumber();
	}

	//roop情報を受け取ってループ部分に色をつける
	void roopDraw(int i,int j,int R,int G,int B){
		fill(R,G,B,10);
		rect(Variable5_01.massW*j,Variable5_01.massH*i,Variable5_01.massW,Variable5_01.massH);
	}

	public static void main(String args[]){
		RoopDraw r = new RoopDraw();
		System.out.println("ループNo. = "+r.getRoopNumber());
		System.out.println("インスタンスカウンタ = "+RoopDraw.getCounter());

		RoopDraw r2 = new RoopDraw();
		System.out.println("ループNo. = "+r2.getRoopNumber());
		System.out.println("インスタンスカウンタ = "+RoopDraw.getCounter());

		System.out.println("getRoopNumber = "+getRoopNumber(r2));

	}

}
