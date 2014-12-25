package sample;

import processing.core.PApplet;

public class neonLine extends PApplet {
	float halfWidth, halfHeight;  //  画面の幅の真ん中

	final int MAX_LINE = 30;  //  ネオンラインの個数
	NeonLine[] neonLine = new NeonLine[MAX_LINE];  //  ネオンライン

	final int DRAW_TIMES = 10;  //  一度に描画する回数
	final int BORDER_DEAD_COUNT = 8;  //  復活するのに必要な無効ライン数


	public void setup(){
	  size(600, 600, P2D);
	  halfWidth = width / 2;
	  halfHeight = height / 2;
	  background(0);
	  noFill();

	  //  ネオンラインを作成しする
	  for(int i = 0; i < MAX_LINE; i++){
	    neonLine[i] = new NeonLine();
	  }
	}


	public void draw(){

	  //  画面を少しずつ暗くする（軌跡のため）
	  fill(0, 0, 0, 10);
	  rect(0, 0, width, height);

	  int deadCounter = 0;  //  画面外に出たネオンラインの個数
	  loadPixels();

	  //  全てのネオンラインについて、画面内いれば描画、画面外ならばカウンターを増やす。
	  for(int j = 0; j < MAX_LINE;j++){
	    if(neonLine[j].live) {

	      //  綺麗に描画するため一度に描画する
	      for(int i = 0; i < DRAW_TIMES; i++){
	        neonLine[j].draw(pixels);
	      }
	    } else {
	      deadCounter++;
	    }
	  }
	  updatePixels();

	  //  画面外にあるネオンラインの数が閾値を超えていたら、画面内に向けて再復活させる
	  if(deadCounter >= BORDER_DEAD_COUNT){
	    float x = 0,y = 0;

	    //  復活位置を決める
	    if(random(1) > 0.5) {
	      x = (int)(random(2)) * width;
	      y = random(height);
	    } else {
	      x = random(width);
	      y = (int)(random(2)) * height;
	    }

	    //  画面外に行ってしまったネオンラインを復活する
	    for(int i = 0; i < MAX_LINE; i++){
	      if(!neonLine[i].live){
	        neonLine[i].revival(x, y);
	      }
	    }
	  }
	}

	class NeonLine {
	  float x;  //  X座標
	  float y;  //  Y座標
	  float speed;  //  速度
	  float direction;  //  進行方向の角度
	  float addDirection;  //  進行方向の角度の加算値
	  float accelDirection;  //  進行方向の角度の加速度
	  float addlDirectionRange;  //  進行方向の角度のリセット幅
	  float stateCounter;  //  状態を変更するためのカウンター
	  int col;  //  色
	  boolean live = false;  //  画面内いるかどうか

	  final int BORDER_STATE_COUNTER = 150;  //  状態変更カウンターの閾値

	  NeonLine(){
	    //  色を決める
	    switch((int)random(6)){
	    case 0:
	      col = color(255, 255, 128, 25);  //  イエロー
	      break;
	    case 1:
	      col = color(255, 128, 255, 25);  //  マゼンタ
	      break;
	    case 2:
	      col = color(128, 255, 255, 25);  //  シアン
	      break;
	    case 3:
	      col = color(255, 128, 128, 25);  //  赤
	      break;
	    case 4:
	      col = color(128, 255, 128, 25);  //  青
	      break;
	    case 5:
	      col = color(128, 128, 255, 25);  //  緑
	      break;
	    }
	  }

	  void revival(float _x, float _y){
	    x = _x;
	    y = _y;
	    speed = 1 + random(0.5f);
	    direction = degrees(atan2(halfHeight - y, halfWidth - x));
	    addDirection = 0;
	    accelDirection = 0;
	    addlDirectionRange = random(6);
	    stateCounter = -random(200);
	    live = true;
	  }

	  void draw(int[] pixels){
	    if(live){
	      x += cos(radians(direction)) * speed;
	      y += sin(radians(direction)) * speed;
	      direction += addDirection;
	      addDirection += accelDirection;

	      //  状態カウンターが閾値を超えたら進行方向角度の加速度を変えることで、進む向きを変える
	      stateCounter++;
	      if(stateCounter >= BORDER_STATE_COUNTER) {
	        stateCounter = 0;
	        addDirection = random(addlDirectionRange) - addlDirectionRange / 2;
	      }

	      //  画面内にいるかどうかの判定
	      boolean deadFlg = false;
	      if(abs(x - width) >= width) {
	        live = false;
	        return;
	      }
	      if(abs(y - height) >= height) {
	        live = false;
	        return ;
	      }

	      //  大きさを変えて複数回描画することで輝きを表現
	      for(int i = 1; i < 4; i++){
	        neonDia(pixels, i);
	      }
	    }
	  }

	  //  ダイヤの図形を指定範囲に加算合成する
	  private void neonDia(int[] px, int size){
	    float size2 = size * size / 2;

	    for(int j = max(0, (int)(y - size2)); j < min(height - 1, (int)(y + size2)); j++){
	      float wide = size2 - abs(j - y);
	      for(int i = max(0, (int)(x - wide)); i < min(width - 1, (int)(x + wide)); i++){
	        int id = j * width + i;
	        px[id] = blendColor(px[id], col, ADD);
	      }
	    }
	  }
	}

	public void mousePressed(){
	  for(int i = 0; i < MAX_LINE; i++){
	    neonLine[i].x = mouseX;
	    neonLine[i].y = mouseY;
	  }
	}
}
