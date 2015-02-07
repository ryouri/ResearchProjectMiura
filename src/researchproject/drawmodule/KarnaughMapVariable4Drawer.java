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
	private ArrayList<Loop> processLoopArray;

	private ArrayList<Loop> successLoopArray;

	/**
	 * 最終的に結果として出力するループが格納される
	 */
	private ArrayList<ArrayList<Loop>> resultLoop2Array;

	private ArrayList<String> resultStringArray;

	public void setMassManager(MassManager massManager) {
		this.massManager = massManager;
		this.massArray = massManager.getMassArray();
	}
	public void setCalcurateManager(CalcurateManager calcurateManager) {
		this.calcurateManager = calcurateManager;
		this.loopManager = calcurateManager.getLoopManager();
		//processLoopArrayにアクセスするときは，下のようにsynchronized構文を使って
		synchronized (loopManager.getProcessLoopArray()) {
			this.processLoopArray = loopManager.getProcessLoopArray();
		}
	}

	public void setup() {
		size(400, 400);
		background(255, 255, 255);
		smooth();

	}
//------

	boolean processDrawFlag;
	boolean finishAnimationFlag;
	//boolean translageFlag;//座標変換の管理用
	Loop processPlace;

	//LoopUnit resultLoop;
	//文字列の描画に用いるために，Timerクラスから出した
	int nowProcessResultIndex=0;
	int nowProcessResultLoopIndex=0;

	public void start() {
		super.start();
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			int processX;
			int processY;
			ArrayList<Loop> nowProcessResultArray;
			Loop nowProcessResultLoopArray;
			//int nowLoopIndex = 0;
			int resultX;
			int resultY;

			public void run() {
				boolean processLoopArrayFlag = processLoopArray.isEmpty();

				if (!processLoopArrayFlag) {

					// processLoopArrayの0番目の要素を読み込む
					synchronized (loopManager.getProcessLoopArray()) {
						processPlace = processLoopArray.get(0);
						for (LoopUnit loopUnit : processPlace.getLoopUnitArray()) {
							processY = loopUnit.getY();
							processX = loopUnit.getX();

							if (processPlace.isLoopSuccess()) {
								// 計算中ループの描画
								CaluculatingSuccessLoopDraw(processY, processX);
							} else {
								CaluculatingLoopDraw(processY, processX);
							}
						}
					}

					// 先頭をremove
					processLoopArray.remove(0);

				//Loop生成の終了チェック用の処理を追加
				//nullじゃなければfieldの変数に参照を代入
				} else if (loopManager.getResultLoop2Array() != null){

					synchronized (loopManager.getResultLoop2Array()) {
						resultStringArray = loopManager.getResultStringArray();

						resultLoop2Array = loopManager.getResultLoop2Array();

						//インデックスが指す，ある結果を表示する
						nowProcessResultArray = resultLoop2Array.get(nowProcessResultIndex);
						//System.out.println("----"+nowprocessResultIndex+"----");//デバッグ用
						//System.out.println("nowprocessResultArray.size() = "+nowprocessResultArray.size());

						//結果の文字列を表示する


						//ある結果のあるLoopを処理する
						Loop nowProcessLoop = nowProcessResultArray.get(nowProcessResultLoopIndex);

						//LoopUnitを取り出して各マスを描画
						for (LoopUnit resultLoop : nowProcessLoop.getLoopUnitArray()) {
							resultX = resultLoop.getX();
							resultY = resultLoop.getY();

							// 決定ループの描画
							resultLoopDraw(resultY, resultX);
							//System.out.println("x = "+resultX+" ,y = "+resultY);
						}


						// LoopArrayのサイズを保存
						int processLoopArraySize = nowProcessResultArray.size() - 1;
						if (nowProcessResultLoopIndex < processLoopArraySize) {
							// ある結果の処理すべきLoopが残っている
							nowProcessResultLoopIndex++;
							try {
								Thread.sleep(300);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						} else {
							nowProcessResultLoopIndex = 0;
							// ある結果のLoopは全て描画し終えた
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

							int resultArraySize = resultLoop2Array.size() - 1;
							if (nowProcessResultIndex < resultArraySize) {
								nowProcessResultIndex++;
							} else {
								nowProcessResultIndex = 0;
							}
						}
					}
				}
			}
		};

	    timer.schedule(task, 2000L, 200L);

//		//ループ決定時のアニメーション用タイマ
//	    if(loopManager.getResultLoop2Array() != null){
//			TimerTask animeTask = new TimerTask() {
//		        public void run() {
//		            finishAnimationFlag = false;
//
//		        }
//		    };
//		    Timer animeTimer = new Timer();
//		    finishAnimationFlag = true;
//		    animeTimer.schedule(animeTask,14600L ,1500L);
//	    }


	}
//------
	final int massW = 36;
	final int massH = 30;

	public void draw() {

		// 座標軸の移動
		translate(width / 4 + 20, height / 4 + 20);

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

		//結果の論理式の描画
		//TODO: Synchoronized構文を入れるとバグるよ，気をつけて
		if (resultStringArray != null){
			text(resultStringArray.get(nowProcessResultIndex), -50, 150);
		}

		//マス周りの文字(0 or 1)横
		drawLetterCrosswise("0","0",0);
		drawLetterCrosswise("0","1",1);
		drawLetterCrosswise("1","1",2);
		drawLetterCrosswise("1","0",3);

		//マス周りの文字(0 or 1)縦
		drawLetterLengthwise("0","0",0);
		drawLetterLengthwise("0","1",1);
		drawLetterLengthwise("1","1",2);
		drawLetterLengthwise("1","0",3);

		if(finishAnimationFlag){
			kirakira();
		}

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

	//表周りの文字の描画(横)
	public void drawLetterCrosswise(String left,String right,int i){
		text( left + right, massW / 4 + massW * i, -5);
	}

	//表周りの文字の描画(縦)
	public void drawLetterLengthwise(String left,String right,int i){
		text( left + right, -24, massH / 4 * 3 + massH * i );
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

	//計算中のループ描画
	public void CaluculatingSuccessLoopDraw(int i, int j){
		fill(238,175,238);
		rect(massW * j, massH * i, massW, massH);
	}

	//決定ループ描画
	public void resultLoopDraw(int i, int j){
		fill(84,77,203,75);
		rect(massW * j, massH * i, massW, massH);
	}

	//白にフェード
	public void fadeToWhite() {
		noStroke();
		fill(255, 20);//fill(color,alpha)
		rectMode(CORNER);
		rect(0, 0, width, height);
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
					int x0 = floor(random(massW*4));
					int y0 = floor(random(massH*4));
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

}