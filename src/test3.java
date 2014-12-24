import processing.core.PApplet;
/*
キラキラ or 泡
ランダムに表示
*/
public class test3 extends PApplet {

	public void setup(){
		size(400,400);
		//colorMode(HSB,100);
		background(99);
		noStroke();
	}

	public void draw(){
		//グラデーション
		/*
		for(int y=0;y<10;y++){
			for(int x=0;x<10;x++){
				fill(x*10,10+y*10,99);
				rect(x*20,y*20,10,10);//x座標を20ずつずらす
			}
		}
		*/

		//ランダム
		/*
		for(int i=0;i<10;i++){
			fill(random(100),random(50),90);
			ellipse(random(width),random(height),20,20);
		}
		*/

		//青い太陽
		/*
		for(int i=0;i<10;i++){
			fill(20*i,200,200,10);
			ellipse(100,100,10+20*i,10+20*i);
		}
		*/

		//fadeToBlack();
		fadeToWhite();
		kirakira();
		//bable();
/*
		rectMode(CENTER);
		fill(50,25);
		translate(100,100);
		rect(0,0,50,50);
		stroke(100,100,100);
		point(0,0);

		//translate()で座標移動
		fill(255);
		//rect(0,0,30,20);
		translate(40,30);//座標軸を右に40，下に30移動
		rect(0,0,30,20);
		translate(-10,30);//座標軸を左に10，下に30
		rect(0,0,30,20);

		//pushMatrix()座標をスタックに格納する，popMatrix()座標をスタックから取り出す
*/
	}

	//キラキラ部分
	public void kirakira(){
		//translate(-100,-100);
		noStroke();
		fill(135,206,250);//lightSkyblue
		beginShape();//図形描画スタート
		vertex( 50, 50 );
		bezierVertex( 50,75,50,75,38,75);
		bezierVertex( 50,75,50,75,50,100 );
		bezierVertex( 50,75,50,75,62,75);
		bezierVertex( 50,75,50,75,50,50);
		endShape();//終わり

	//	int repeatNum = 0;
	//	for(int j = 0; j<=repeatNum; j++){
	//		for(int i = 0; i<=repeatNum; i++){

	//	while(repeatNum < 10){
		//色
		float colorB = random( 80,255 );
		float colorR = random( 80,255 );
		float colorG = random( 80,255 );
	    fill( colorR, colorG, colorB, 95 );
				//fill(135,206,265);
				int x0 = floor(random(width));
				int y0 = floor(random(height));
				int halfX = floor(random(12,30));
				int halfY = halfX * 2;
				int sX = x0;
				int sY = y0+halfY;
				int x1 = x0-halfX;
				int y1 = y0+halfY;
				int x2 = x0;
				int y2 = y0+halfY*2;
				int x3 = x0+halfX;
				int y3 = y0+halfY;

				beginShape();
				vertex(x0,y0);
				bezierVertex( sX,sY,sX,sY,x1,y1);
				bezierVertex( sX,sY,sX,sY,x2,y2);
				bezierVertex( sX,sY,sX,sY,x3,y3);
				bezierVertex( sX,sY,sX,sY,x0,y0);
				endShape();

	//		repeatNum++;
	//		}
	//	}
		//}
	}

	//泡
	void bable(){
		//色
		float colorB = random( 80,255 );
		float colorR = random( 80,255 );
		float colorG = random( 80,255 );
		fill( colorR, colorG, colorB, 95 );

		float centerX = random(width);
		float centerY = random(height);
		float wh = random(20,60);
		ellipse( centerX,centerY,wh,wh);
	}

	void fadeToBlack() {
		noStroke();
		fill(0, 25);//fill(color,alpha)
		rectMode(CORNER);
		rect(0, 0, width, height);
	}

	void fadeToWhite() {
		noStroke();
		fill(255, 25);//fill(color,alpha)
		rectMode(CORNER);
		rect(0, 0, width, height);
	}

}
