package researchproject.drawmodule;

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

/**
 * このクラス単体で実行するとバグります
 * DrawManagerから実行してください
 */
public class KarnaughMapVariable4Drawer extends PApplet {
	//三澤が追加したもの
	private MassManager massManager;

	private Mass[][][] massArray;

	private CalcurateManager calcurateManager;

	private LoopManager loopManager;

	/**
	 * 処理してもらいたいループが格納される
	 */
	private ArrayList<Loop> proccessLoopArray;

	private ArrayList<Loop> successLoopArray;

	/**
	 * 最終的に結果として出力するループが格納される
	 */
	private ArrayList<ArrayList<Loop>> resultLoop2Array;

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

	public void setup() {
		size(400, 400);
		background(255, 255, 255);
		smooth();

	}
//------

	boolean proccessDrawFlag;
	Loop proccessPlace;

	public void start() {
		super.start();
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			int proccessX;
			int proccessY;
			ArrayList<Loop> nowproccessResultArray;
			Loop nowproccessResultLoopArray;
			//LoopUnit resultLoop;
			int nowproccessResultIndex=0;
			//int nowproccessResultLoopIndex=0;
			//int nowLoopIndex = 0;
			int resultX;
			int resultY;

			public void run() {



				boolean proccessLoopArrayFlag = proccessLoopArray.isEmpty();

				if (!proccessLoopArrayFlag) {

					// proccessLoopArrayの0番目の要素を読み込む
					synchronized (loopManager.getProccessLoopArray()) {
						proccessPlace = proccessLoopArray.get(0);
						for (LoopUnit loopUnit : proccessPlace.getLoopUnitArray()) {
							proccessY = loopUnit.getY();
							proccessX = loopUnit.getX();

							// 計算中ループの描画
							CaluculatingLoopDraw(proccessY, proccessX);

						}
					}

					// 先頭をremove
					proccessLoopArray.remove(0);

				//Loop生成の終了チェック用の処理を追加
				//nullじゃなければfieldの変数に参照を代入
				} else if (loopManager.getResultLoop2Array() != null){
					synchronized (loopManager.getResultLoop2Array()) {

						resultLoop2Array = loopManager.getResultLoop2Array();

						nowproccessResultArray = resultLoop2Array.get(nowproccessResultIndex);
						//System.out.println("----"+nowproccessResultIndex+"----");//デバッグ用
						//System.out.println("nowproccessResultArray.size() = "+nowproccessResultArray.size());

						for (int i = 0; i < nowproccessResultArray.size(); i++) {
							nowproccessResultLoopArray = nowproccessResultArray.get(i);
							//System.out.println("ArrayList<ArrayList> i = "+i);

							for (LoopUnit resultLoop : nowproccessResultLoopArray.getLoopUnitArray()) {
								resultX = resultLoop.getX();
								resultY = resultLoop.getY();

								// 決定ループの描画
								resultLoopDraw(resultY, resultX);
								//System.out.println("x = "+resultX+" ,y = "+resultY);
							}
						}

						int size = resultLoop2Array.size()-1;
						if( nowproccessResultIndex < size ){
							nowproccessResultIndex++;
						}else {//if(nowproccessResultIndex >= resultLoop2Array.size() ){
							nowproccessResultIndex = 0;
						}

					}


				}

			}


		};

	    timer.schedule(task, 2000L, 100L);

	}
//------
	final int massW = 36;
	final int massH = 30;

	public void draw() {

		// 座標軸の移動
		translate(width / 4, height / 4);

		int i = 0;// 配列の行番号に対応
		int j = 0;// 配列の列番号に対応

		scale(1.5f);// 表全体の大きさを操作

		//表の枠を描画
		for (i = 0; i < 4; i++) {
			for (j = 0; j < 4; j++) {
				drawSquare(i, j);
				// drawText(i,j);
				// draw2(i,j);
			}
		}

		//TODO: とりあえず4変数のため，以下の様な処理とする
		Mass[][] oneTableMassArray = massArray[0];

		// 表を埋める
		for (int k = 0; k < oneTableMassArray.length; k++) {
			for (int l = 0; l < oneTableMassArray[k].length; l++) {
				if (oneTableMassArray[k][l].getState() == Mass.STATE_ONE) {
					drawText(k, l);
					//draw2(k, l);
				}
			}
		}

		drawLine(1);
		drawLetter("A", "B", "C", "D");

		//ループの描画


		//白にフェードアウト
		fadeToWhite();




	}

	// マス描画
	public void drawSquare(int i, int j) {

		noFill();// 塗りつぶしなし
		stroke(0, 0, 0);// 枠線の色(黒)
		rect(massW * j, massH * i, massW, massH);
	}

	// 1を書く
	public void drawText(int i, int j) {

		fill(0, 0, 0);
		textSize(12);
		text("1", massW / 3 + massW * j, massH / 3 * 2 + massH * i);
	}

	// マスに色をつける
	public void draw2(int i, int j) {

		fill(135, 206, 250, 40);
		rect(massW * j, massH * i, massW, massH);
	}

	// マスの周りの要素
	public void drawLine(int strokeSize) {
		stroke(0, 0, 0);

		strokeWeight(strokeSize);
		line(0, 0, -massW, -massH);
	}

	// マスの周りの文字
	public void drawLetter(String a, String b, String c, String d) {
		textSize(14);

		fill(0, 0, 0);
		text(c + d, -massW + massW / 3, -massH + massH / 3);
		text(a + b, -massW, 0);
	}

	//計算中のループ描画
	public void CaluculatingLoopDraw(int i, int j){
		fill(175,238,238);
		rect(massW * j, massH * i, massW, massH);
	}

	//決定ループ描画
	public void resultLoopDraw(int i, int j){
		fill(84,77,203,20);
		rect(massW * j, massH * i, massW, massH);
	}

	//白にフェード
	public void fadeToWhite() {
		noStroke();
		fill(255, 20);//fill(color,alpha)
		rectMode(CORNER);
		rect(0, 0, width, height);
	}

}