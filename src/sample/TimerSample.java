package sample;
/*
 * ループが決定したときに出すアニメーション
 * タイマーつき
 */

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import processing.core.PApplet;

public class TimerSample extends PApplet{

	public void setup(){
		super.setup();
		size(400,400);
		//colorMode(HSB,100);
		background(99);
		noStroke();
		//kirakira();ここで実行するとkirakiraは１回だけ実行される

	}

	boolean animationDrawFlag;
	ArrayList<Integer> ar = new ArrayList<Integer>();
	//ar.add(10);



	public void start() {
		super.start();
		//task : 定期的にanimationDrawFlagをtrueにする
		TimerTask task = new TimerTask() {
	        public void run() {
	            animationDrawFlag = true;
	            //task2 : 一定時間が経ったらanimationDrawFlagをfalseにする
	            TimerTask task2 = new TimerTask(){
	            	public void run(){
	            		animationDrawFlag = false;
	            	}
	            };
	            Timer timer2 = new Timer();
	            timer2.schedule(task2, 1500L);

	        }
	    };
	    Timer timer = new Timer();
	    //animationDrawFlag = true;
	    timer.schedule(task, 1000L,1500L);


	}

	public void draw(){
		//fadeToBlack();
		fadeToWhite();
		if(animationDrawFlag){
			kirakira();
		}



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

		//色
		float colorB = random( 80,255 );
		float colorR = random( 80,255 );
		float colorG = random( 80,255 );
	    fill( colorR, colorG, colorB );
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
	}

	//黒にフェードアウト
	void fadeToBlack() {
		noStroke();
		fill(0, 25);//fill(color,alpha)
		rectMode(CORNER);
		rect(0, 0, width, height);
	}

	//白にフェードアウト
	void fadeToWhite() {
		noStroke();
		fill(255,10);//fill(color,alpha)
		rectMode(CORNER);
		rect(0, 0, width, height);
	}



}