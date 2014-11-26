package researchproject.drawmodule.mass;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * カルノー図上のある1マスを表すクラス
 * とりあえず4変数対応
 * @author ryou
 */
public class Mass {
	public Mass() {
		this.variableArray = new ArrayList<String>();
		this.variablePosMap = new HashMap<String, Integer>();
	}
	
	//以下の4つは4変数の際の，各変数の0, 1の場所を記録
	public static final int[][][] POS = 
		{
			{//A
				{0, 0, 0, 0},
				{0, 0, 0, 0},
				{1, 1, 1, 1},
				{1, 1, 1, 1}
			},

			{//B
				{0, 0, 0, 0},
				{1, 1, 1, 1},
				{1, 1, 1, 1},
				{0, 0, 0, 0}
			},

			{//C
				{0, 0, 1, 1},
				{0, 0, 1, 1},
				{0, 0, 1, 1},
				{0, 0, 1, 1}
			},

			{//D
				{0, 1, 1, 0},
				{0, 1, 1, 0},
				{0, 1, 1, 0},
				{0, 1, 1, 0}
			},
		};
	
	/**
	 * 各変数の名前を保存している定数
	 */
	public static final String[] VARIABLE_NAME = 
		{"A", "B", "C", "D", "E", "F"};
	
	/**
	 * このMassに入ってる変数名を保存しておく
	 */
	private ArrayList<String> variableArray;
	
	/**
	 * このMassの座標を保存しておく
	 */
	private HashMap<String, Integer> variablePosMap;
	
	/**
	 * このMassに入ってる変数の数を保存しておく
	 */
	private int variableNum;

	//以下はこのMASSの各状態(Sum)を表す定数
	/**
	 * 状態：０を表す定数
	 */
	public static final int STATE_ZERO = 0;
	/**
	 * 状態：１を表す定数
	 */
	public static final int STATE_ONE = 1;
	/**
	 * 状態：DON'T CAREを表す定数
	 */
	public static final int STATE_DONTCARE = 2;
	
	/**
	 * このMassの状態(Sum)を保存する変数
	 */
	private int state;
	

	
	public int getVariableNum() {
		return variableNum;
	}

	public void setVariableNum(int variableNum) {
		this.variableNum = variableNum;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public ArrayList<String> getVariableArray() {
		return variableArray;
	}

	public HashMap<String, Integer> getVariablePosMap() {
		return variablePosMap;
	}
}
