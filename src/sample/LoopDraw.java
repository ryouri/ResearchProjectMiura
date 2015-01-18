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

	//色情報リスト
	static int loopColorList[][] = {{1,213,132,224},{2,84,77,203},{3,82,162,197},
						 	 {4,243,213,26},{5,40,175,148},{6,51,96,69},
						 	 {7,239,143,15},{8,239,117,152},{9,204,0,0}
							};

	//ループ決定時の色情報取得
	public static int[] getLoopColor(){
		int loopR = loopColorList[LoopDraw.loopCount][1];
		int loopG = loopColorList[LoopDraw.loopCount][2];
		int loopB = loopColorList[LoopDraw.loopCount][3];

		int data[] = new int[3];
		data[0] = loopR;
		data[1] = loopG;
		data[2] = loopB;
		return data;
	}

	//計算中のループ描画
	public void CaluculatingLoopDraw(int i, int j){

		fill(175,238,238);
		rect(Variable5_01.massW * j, Variable5_01.massH * i, Variable5_01.massW, Variable5_01.massH);
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

		//System.out.println("getRoopNumber = "+getLoopNumber(r2));
		new LoopDraw();
		System.out.println("現在のループの個数は"+LoopDraw.getLoopCounter());

		System.out.println("----------------------------");
		loopCount = getLoopCounter();
		int data[] = getLoopColor();
		System.out.println( loopColorList[loopCount][0] );
		System.out.println( data[0]+"\t"+data[1]+"\t"+data[2] );

		System.out.println("----------------------------");
		for(int i = 0; i<3; i++){
			new LoopDraw();
			loopCount = LoopDraw.getLoopCounter();
			System.out.println("loopcount = "+loopCount);
			System.out.println( LoopDraw.getLoopCounter() );
		}

		new LoopDraw();
		loopCount = LoopDraw.getLoopCounter();
		System.out.println("----------------------------");
		System.out.println( LoopDraw.loopCount );
		System.out.println( loopColorList[loopCount][0] );
		int d[] = getLoopColor();
		System.out.println( d[0]+"\t"+d[1]+"\t"+d[2] );

		int a = d[0];
		int b = d[1];
		int c = d[2];
		System.out.println(a+"\t"+b+"\t"+c);
	}


}
