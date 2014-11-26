package researchproject.drawmodule;

import processing.core.PApplet;
import researchproject.drawmodule.mass.Mass;
import researchproject.drawmodule.mass.MassManager;

/**
 * このクラス単体で実行するとバグります
 * DrawManagerから実行してください
 */
public class KarnaughMapDrawer extends PApplet {
	MassManager massManager;

	Mass[][][] massArray;
	
	public void setMassManager(MassManager massManager) {
		this.massManager = massManager;
		this.massArray = massManager.getMassArray();
	}

	public void setup() {
		size(400, 400);
		background(255, 255, 255);
		smooth();
	}

	final int massW = 36;
	final int massH = 30;

	public void draw() {

		// 座標軸の移動
		translate(width / 4, height / 4);

		int i = 0;// 配列の行番号に対応
		int j = 0;// 配列の列番号に対応

		scale(1.5f);// 表全体の大きさを操作
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
					draw2(k, l);
				}
			}
		}

		drawLine(1);
		drawLetter("A", "B", "C", "D");

		// textSize(10);
		// text("■",10,10);
		// rect(0,0,10,10);
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

		fill(135, 206, 250, 10);
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
}