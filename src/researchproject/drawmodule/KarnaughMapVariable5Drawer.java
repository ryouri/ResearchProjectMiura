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
	private ArrayList<Loop> processLoopArray;

	public void setMassManager(MassManager massManager) {
		this.massManager = massManager;

		//TODO: ダミーデータの生成
		this.massArray = new Mass[2][4][4];
		//massArray[0]が下の表を表現
		massArray[0] = massManager.getMassArray()[0];
		//massArray[1]が上の表を表現
		massArray[1] = massManager.getMassArray()[0];
	}


	public void setCalcurateManager(CalcurateManager calcurateManager) {
		this.calcurateManager = calcurateManager;
		this.loopManager = calcurateManager.getLoopManager();
		//proccessLoopArrayにアクセスするときは，下のようにsynchronized構文を使って
		synchronized (loopManager.getProcessLoopArray()) {
			this.processLoopArray = loopManager.getProcessLoopArray();
		}
	}

	//------

		boolean processDrawFlag;

		Loop processPlace;
		int processX;
    	int processY;
    	int processZ;

		public void start() {
			super.start();
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {

		        public void run() {
		        	boolean translateFlag = false;//座標変換の管理用
		        	boolean proccessLoopArrayFlag = processLoopArray.isEmpty();

		           if(!proccessLoopArrayFlag){
		        	    processDrawFlag = true;
      					int x = 10;
      					int y = 40;
      					int z = -60;


			        	//proccessLoopArrayの0番目の要素を読み込む
//			       		synchronized (loopManager.getProccessLoopArray()) {
//			       			proccessPlace = proccessLoopArray.get(0);
//			       			for (LoopUnit loopUnit : proccessPlace.getLoopUnitArray()) {
//			       				proccessY = loopUnit.getY();
//			       				proccessX = loopUnit.getX();
//			       				proccessZ = loopUnit.getZ();
//
//			       				//デバッグ用
//			       				System.out.println("X = "+proccessX+", Y = "+proccessY+", Z = "+proccessZ);
//
//
//			    	       		//計算中ループの描画
//			       				if( proccessZ == 0 ){
//			       					//座標軸の移動
//			       					//translate(-x,-y,-z);
//			       					//translateFlag = true;
//			       					CaluculatingLoopDraw(proccessY,proccessX);
//			       				}else{
//
//			       					CaluculatingLoopDraw(proccessY,proccessX);
//			       				}
//
//
//			       			}
//			       		}
//			       		System.out.println("------------------------------------");
//
//			       		//proccessDrawFlag = true;
//
//			       		//先頭をremove
//			       		proccessLoopArray.remove(0);
//
//			       		//座標移動がされていた場合は戻す
//			       		if( translateFlag ){
//			       			translate(x,y,z);
//			       		}

		           }else{
		        	 //  proccessDrawFlag = false;
		           }

		        }
		    };

		    timer.schedule(task, 2000L, 400L);

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
		//draw2(0,0);

		//座標軸の保存
		//pushMatrix();


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

		//表(上)に１を埋める．massArray[1]
		for(int k = 0; k<massArray[1].length; k++){
			for(int l = 0; l<massArray[1][k].length; l++){
				if( massArray[1][k][l].getState() == Mass.STATE_ONE ){
					drawText0(k,l,x,y,z);
				}
			}
		}

		//表(下)に１を埋める．massArray[0]
		for(int k = 0; k<massArray[0].length; k++){
			for(int l = 0; l<massArray[0][k].length; l++){
				if( massArray[0][k][l].getState() == Mass.STATE_ONE ){
					drawText1(k,l);
				}
			}
		}

//		//表0(上)に１を埋める
//		int count = 0;
//		for(int k = 0; k<shinri.length; k++){
//			if( shinri[k][4] == 0){
//				for(int l = 0; l<shinri[k].length-1; l++){
//					if(shinri[k][l]==1){
//						drawText0(count,l,x,y,z);
//					}
//				}
//				count++;
//			}
//		}
//
//		//表1(下)に１を埋める
//		count = 0;
//		for(int k = 0; k<shinri.length; k++){
//			if( shinri[k][4] == 1){
//				for(int l = 0; l<shinri[k].length-1; l++){
//					if(shinri[k][l]==1){
//						drawText1(count,l);
//					}
//				}
//				count++;
//			}
//		}



		//計算中のループの描画，別バージョン
		if(processDrawFlag){
	   		synchronized (loopManager.getProcessLoopArray()) {
	   			processPlace = processLoopArray.get(0);
	   			for (LoopUnit loopUnit : processPlace.getLoopUnitArray()) {
	   				processY = loopUnit.getY();
	   				processX = loopUnit.getX();
	   				processZ = loopUnit.getZ();

	   				//デバッグ用
	   				System.out.println("X = "+processX+", Y = "+processY+", Z = "+processZ);


		       		//計算中ループの描画
	   				if( processZ == 0 ){
	   					//translateFlag = true;
	   					translate(-x,-y,-z);
	   					CaluculatingLoopDraw(processY,processX);
	   					translate(x,y,z);
	   				}else{
	   					//座標軸の移動
	   					//translate(-x,-y,-z);
	   					CaluculatingLoopDraw(processY,processX);
	   					//translate(x,y,z);
	   				}

	   			}
	   		}
	   		System.out.println("------------------------------------");

	   		//proccessDrawFlag = true;

	   		//先頭をremove
	   		processLoopArray.remove(0);
	   		processDrawFlag = false;
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

		//座標軸を戻す
		//popMatrix();

		//白にフェードアウト
		fadeToWhite();
		//fadeToWhite_box();
		fadeToWhiteTop(x,y,z);

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
	//白にフェード(表下)
	public void fadeToWhite() {
		noStroke();
		fill(255, 20);//fill(color,alpha)
		rectMode(CORNER);
		rect(0, 0, width, height);
	}

	//白にフェード(表上)
	public void fadeToWhiteTop(int x,int y,int z) {
		noStroke();
		translate(-x,-y,-z);
		fill(255,20);//fill(color,alpha)
		rectMode(CORNER);
		//rect(-x, -y,-z, width, height);
		rect(0, 0, massW * 4, massH * 4);
		translate(x,y,z);
	}

	//紅梅色に着色サンプル(表上)
	public void fadeToColor() {
		noStroke();
		fill(235,121,136, 20);//fill(color,alpha)
		rectMode(CORNER);
		rect(0, 0, width, height);
	}

	public void fadeToWhite_box(){
		int x = (width/3)+10;
		int y = (height/4)+40;
		int z = -60;
		translate(-x,-y,-z);
		fill(255,20);
		box(500);
		translate(x,y,z);
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
