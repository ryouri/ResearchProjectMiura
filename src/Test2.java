import processing.core.PApplet;

public class Test2 extends PApplet {
	public void setup() {
		size(400, 400);
		background(255,255,255);
	}


	final int massW = 20;
	final int massH = 20;

	public void draw() {
	/*	pushMatrix(); //(0,0)を原点とする座標軸をスタックに格納

		translate(40,30); //右に40，下に30移動
		rect(0,0,30,20); //(x,y,幅,高さ)

		popMatrix();//座標軸の位置をスタックから取出し設定する(0,0)
		rect(0,0,30,20);

		translate(100,100);
		rect(0,0,30,20);
	*//*
		pushMatrix();//(0,0)格納

		translate(30,0);
		rect(10,40,20,20); //真ん中

		pushMatrix();//(30,0)を原点とする座標軸をスタックに格納
		translate(30,0);
		rect(10,40,20,20);//右
		popMatrix();//(30,0)をpop
		popMatrix();//(0,0)をpop
		rect(10,40,20,20);//左

		translate(100,10);
		rect(40,0,30,20);
		rotate(PI/6);//座標軸を30°回転
		rect(40,0,30,20);
		rotate(PI/6);
		rect(40,0,30,20);
	*/
		//translate(200,10);
	/*	smooth();
		ellipse(20,20,30,30);
		scale(2.0F);
		ellipse(20,20,30,30);
		scale(0.2F);
		ellipse(20,20,30,30);
	*/
		int i = 0;//配列の行番号
		int j = 0;//配列の列番号

		for(i=0;i<4;i++){
			for(j=0;j<4;j++){
				//Mass[0][0]
				//noFill();
				//stroke(0,0,0);
				//rect(10+massW*j,10,massW,massH);
				drawSquare(j);
				textSize(12);
				//fill(0,0,0);
				//text("1",15,5+massH);
				drawText(i,j);
				draw2(i,j);
				//j++;
/*
				//Mass[0][1]
				//noFill();
				//rect(10+massW*j,10,massW,massH);
				//text("1",15+massW,5+massH);
				drawSquare(j);
				drawText(i,j);
				j++;

				//Mass[0][2]
				drawSquare(j);
				drawText(i,j);
				draw2(i,j);
				j++;

				//Mass[0][3]
				drawSquare(j);
				drawText(i,j);
				j++;
*/
			}
		}


		textSize(10);
		text("■",10,10);
	}

	public void drawSquare(int j){
		noFill();
		stroke(0,0,0);
		rect(10+massW*j,10,massW,massH);
	}

	public void drawText(int i,int j){
		fill(0,0,0);
		text("1",15+massW*j,25+massH*i);
	}

	public void draw2(int i,int j){
		fill(135,206,250,10);
		rect(10+massW*j,10+massH*i,massW,massH);
	}
}
