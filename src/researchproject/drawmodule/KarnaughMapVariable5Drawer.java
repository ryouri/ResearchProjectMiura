package researchproject.drawmodule;
/*
 * ５変数
 * 背景：黒，マス：白に塗りつぶし
 * ‘１’の描画がうまくいかない
 */
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import processing.core.PApplet;
import researchproject.drawmodule.calc.CalcurateManager;
import researchproject.drawmodule.loop.Loop;
import researchproject.drawmodule.loop.LoopManager;
import researchproject.drawmodule.loop.LoopUnit;
import researchproject.drawmodule.mass.Mass;
import researchproject.drawmodule.mass.MassManager;

public class KarnaughMapVariable5Drawer extends PApplet{
	public void setup() {
		size(500, 500,OPENGL);
		//background(0,0,0);//黒
		background(255,255,255);//白
		smooth();
		//frameRate(30);//フレームレート
	}

	final int massW = 30;
	final int massH = 25;

	int[][] shinri = {{0,0,0,0,0},{1,1,0,0,0},{1,0,1,0,0},{0,0,1,1,0},//表(上)
					  {1,1,1,1,1},{1,1,1,1,1},{1,0,0,0,1},{0,0,1,1,1}};//表(下)


	//三澤が追加したもの
	private MassManager massManager;

	private Mass[][][] massArray;

	private CalcurateManager calcurateManager;

	private LoopManager loopManager;

	/**
	 * 処理してもらいたいループが格納される
	 */
	private ArrayList<Loop> proccessLoopArray;

	public void setMassManager(MassManager massManager) {
		this.massManager = massManager;
		this.massArray = massManager.getMassArray();
	}
	public void setCalcurateManager(CalcurateManager calcurateManager) {
		this.calcurateManager = calcurateManager;
		this.loopManager = calcurateManager.getLoopManager();
		//proccessLoopArrayにアクセスするときは，下のようにsynchronized構文を使って
		synchronized (loopManager.getProccessLoopArray()) {
			this.proccessLoopArray = loopManager.getProccessLoopArray();
		}
	}

	//------

		boolean proccessDrawFlag;
		Loop proccessPlace;
		int proccessX;
    	int proccessY;
    	int proccessZ;

		public void start() {
			super.start();
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {

		        public void run() {
		        	boolean proccessLoopArrayFlag = proccessLoopArray.isEmpty();

		           if(!proccessLoopArrayFlag){
      					int x = 10;
      					int y = 40;
      					int z = -60;

		        	//proccessLoopArrayの0番目の要素を読み込む
		       		synchronized (loopManager.getProccessLoopArray()) {
		       			proccessPlace = proccessLoopArray.get(0);
		       			for (LoopUnit loopUnit : proccessPlace.getLoopUnitArray()) {
		       				proccessY = loopUnit.getY();
		       				proccessX = loopUnit.getX();
		       				proccessZ = loopUnit.getZ();

		    	       		//計算中ループの描画
		       			//	if(proccessZ == 0){
		       			//		CaluculatingLoopDraw(proccessY,proccessX);
		       			//	}else{
		       				//座標軸の移動
		       			//		translate(x,y,z);
		       			//		CaluculatingLoopDraw(proccessY,proccessX);
		       			//	}

		    	       //		System.out.println("X = "+proccessX+",Y = "+proccessY);

		       			}
		       		}
		       		//System.out.println("------------------------------------");

		       		proccessDrawFlag = true;

		       		//先頭をremove
		       		proccessLoopArray.remove(0);

		       		//translate(-x,-y,-z);//座標を戻す

		        	  // proccessDrawFlag = true;
		           }else{
		        	 //  proccessDrawFlag = false;
		           }

		        }
		    };

		    timer.schedule(task, 2000L, 500L);

		}
	//------


	public void draw(){
		//座標軸の中心を移動
		translate(width/3,height/4);
		scale(1.5f);

		rotateX(radians(50));    //X軸に対して50度回転
		rotateZ(radians(20));

		//表(上)を描画
		int i = 0;
		int j = 0;
		for(i=0;i<4;i++){
			for(j=0;j<4;j++){
				drawSquare(i,j);
			}
		}

		//マスに色を塗る
		draw2(0,0);


		/* 2つめの表を描く */

		//座標軸の移動
		int x = 10;
		int y = 40;
		int z = -60;
		translate(x,y,z);

		//表(下)を描画
		for(i=0;i<4;i++){
			for(j=0;j<4;j++){
				drawSquare(i,j);
			}
		}

		//表0(上)に１を埋める
		int count = 0;
		for(int k = 0; k<shinri.length; k++){
			if( shinri[k][4] == 0){
				for(int l = 0; l<shinri[k].length-1; l++){
					if(shinri[k][l]==1){
						drawText0(count,l,x,y,z);
					}
				}
				count++;
			}
		}

		//表1(下)に１を埋める
		count = 0;
		for(int k = 0; k<shinri.length; k++){
			if( shinri[k][4] == 1){
				for(int l = 0; l<shinri[k].length-1; l++){
					if(shinri[k][l]==1){
						drawText1(count,l);
					}
				}
				count++;
			}
		}


		//マスに色を塗る
		draw2(0,1);

		//計算中のループの描画
		if(proccessDrawFlag){
			CaluculatingLoopDraw(proccessY,proccessX);
			proccessDrawFlag = false;
		}


		//軸線
		/*
		stroke(255,0,0);
		line(-400,0,0,400,0,0);
		stroke(0,255,0);
		line(0,-400,0,0,400,0);
		stroke(0,0,255);
		line(0,0,-400,0,0,400);
		//line(0,massH*4,0,-10,massH*4-40,60);
*/

		//破線を描く
		dotLine(-x,-y,0,0,-z);
		dotLine(-x,-y,0,4,-z);
		dotLine(-x,-y,4,0,-z);
		dotLine(-x,-y,4,4,-z);

		//白にフェードアウト
		fadeToWhite();

	}

	//マス描画
	public void drawSquare(int i,int j){
		noFill();//塗りつぶしなし//fill(255);
		stroke(0);//枠線の色(黒)
		strokeWeight(2);
		rect(massW*j,massH*i,massW,massH);
	}

	//マスに色をつける
	public void draw2(int i,int j){
		fill(135,206,250,10);
		rect(massW*j,massH*i,massW,massH);
	}

	//1を書く(表０(上))
	public void drawText0(int i,int j,int x,int y,int z){
		fill(0,0,0);
		textSize(14);
		text("1",+massW/3+massW*j-x,15+massH*i-y,-z);
	}

	//1を書く(表１)
	public void drawText1(int i,int j){
		fill(0);
		textSize(14);
		text("1",massW/3+massW*j,15+massH*i,0);
	}

	//計算中のループ描画
		public void CaluculatingLoopDraw(int i, int j){
			fill(175,238,238);
			rect(massW * j, massH * i, massW, massH);

		}
	//白にフェード
	public void fadeToWhite() {
		noStroke();
		fill(255, 20);//fill(color,alpha)
		rectMode(CORNER);
		rect(0, 0, width, height);
	}


	//点線を描く
	public void dotLine(int transX,int transY,int pointX,int pointY,int z){
		stroke(0,0,255);
		strokeWeight(1);
		int j = 0;
		int i = 0;
		float deltaX = transX / 8.0f;
		float deltaY = transY / 8.0f;
		int x = massW * pointX;//x座標
		int y = massH * pointY;//y座標
		while( j<z-4 ){
			j = 8 * i;
			line(x+floor(deltaX * i),y+floor(deltaY * i),j,x+floor(deltaX*(i+1)),y+floor(deltaY*(i+1)),j+4);
			i++;
		}
	}
}
