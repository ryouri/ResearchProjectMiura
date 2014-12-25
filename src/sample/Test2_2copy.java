package sample;

import processing.core.PApplet;
//import sample.Test6;

public class Test2_2copy extends PApplet {
	public void setup() {
		size(400, 400);
		background(255,255,255);
		smooth();
	}

	final int massW = 36;
	final int massH = 30;

	int[][] shinri = {{1,1,1,1},{1,1,0,0},{0,0,0,0},{1,0,1,0}};

	public void draw() {
		/*
		Test6 Test6 = new Test6();
		Test6.fadeToBlack();
		*/
		fadeToWhite();

		//座標軸の移動
		translate(width/4,height/4);

		int i = 0;//配列の行番号に対応
		int j = 0;//配列の列番号に対応

		scale(1.5f);//表全体の大きさを操作

		//表(枠)の表示
		for(i=0;i<4;i++){
			for(j=0;j<4;j++){
				drawSquare(i,j);
			//	drawText(i,j);
			//	draw2(i,j);
			}
		}

		//表を埋める
		for(int k=0; k<shinri.length; k++){
			for(int l=0;l<shinri[k].length;l++){
				if(shinri[k][l]==1){ //真理値が1だったら1を表示
					drawText(k,l);
					//draw2(k,l,10);
				}
			}
		}

		//ループ情報
		int[][] roop1 = {{1,1,1,1},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
		int[][] roop2 = {{1,1,0,0},{1,1,0,0},{0,0,0,0},{0,0,0,0}};


		for(int m = 0; m<roop1.length; m++){
			for(int n = 0; n<roop1[m].length; n++){
				if(roop1[m][n]==1){
					draw2(m,n,0,191);
				}
			}
		}

		for(int m = 0; m<roop2.length; m++){
			for(int n = 0; n<roop2[m].length; n++){
				if(roop2[m][n]==1){
					draw2(m,n,255,0);
				}
			}
		}

		drawLine(1);
		drawLetter("A","B","C","D");
		//kirakira();
		//fadeToWhite();
		//fadeToBlack();


	//textSize(10);
		//text("■",10,10);
		//rect(0,0,10,10);
	}

	//マス描画
	public void drawSquare(int i,int j){
		noFill();//塗りつぶしなし
		stroke(0,0,0);//枠線の色(黒)
		rect(massW*j,massH*i,massW,massH);
	}

	//1を書く
	public void drawText(int i,int j){
		fill(0,0,0);
		textSize(12);
		text("1",massW/3+massW*j,massH/3*2+massH*i);
	}

	//マスに色をつける
	public void draw2(int i,int j,int colorR,int colorG){
		noStroke();
		int plusX = 2;
		int plusY = 2;
		fill(colorR,colorG,255,20);
		rect(massW*j-plusX,massH*i-plusY,massW+plusX,massH+plusY);
	}

	//マスの周りの要素
	public void drawLine(int strokeSize){
		stroke(0,0,0);
		strokeWeight(strokeSize);
		line(0,0,-massW,-massH);
	}

	//マスの周りの文字
	public void drawLetter(String a,String b,String c, String d){
		textSize(14);
		fill(0,0,0);
		text(c+d,-massW+massW/3,-massH+massH/3);
		text(a+b,-massW,0);
	}

	//光部分
	public void kirakira(){
		//座標軸の移動
		translate(-width/4,-height/4);//(0,0)に戻す

		noStroke();
		fill(135,206,250);//lightSkyblue
		beginShape();//図形描画スタート
		vertex( 50, 50 );
		bezierVertex( 50,75,50,75,38,75);
		bezierVertex( 50,75,50,75,50,100 );
		bezierVertex( 50,75,50,75,62,75);
		bezierVertex( 50,75,50,75,50,50);
		endShape();//終わり

		//色
		float colorB = random( 80,255 );
		float colorR = random( 80,255 );
		float colorG = random( 80,255 );
	    fill( colorR, colorG, colorB );
		//fill(135,206,265);

	    //座標
		int x0 = floor(random(width));
		int y0 = floor(random(height));
	    //int x0 = floor(random(massW*j,massW));
	    //int y0 = floor(random(massH*i,massH));
		int halfX = floor(random(6,15));
		int halfY = halfX * 2;
		int sX = x0;
		int sY = y0+halfY;
		int x1 = x0-halfX;
		int y1 = y0+halfY;
		int x2 = x0;
		int y2 = y0+halfY*2;
		int x3 = x0+halfX;
		int y3 = y0+halfY;

		//描画
		beginShape();
		vertex(x0,y0);
		bezierVertex( sX,sY,sX,sY,x1,y1);
		bezierVertex( sX,sY,sX,sY,x2,y2);
		bezierVertex( sX,sY,sX,sY,x3,y3);
		bezierVertex( sX,sY,sX,sY,x0,y0);
		endShape();

	}

	//白にフェード
	void fadeToWhite() {
		noStroke();
		fill(255, 20);//fill(color,alpha)
		rectMode(CORNER);
		rect(0, 0, width, height);
	}

	//黒にフェード
	void fadeToBlack() {
		noStroke();
		fill(0, 25);//fill(color,alpha)
		rectMode(CORNER);
		rect(0, 0, width, height);
	}

}
