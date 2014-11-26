package researchproject.drawmodule.mass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MassManager {
	private String inputCSVPath;
	
	private int variableNum;
	
	private Mass[][][] massArray;

	public Mass[][][] getMassArray() {
		return massArray;
	}

	public MassManager(String filePath) {
		this.inputCSVPath = filePath;

		init();
	}

	public void init() {
		loadTruthTable(inputCSVPath);
	}

	/**
	 * 真理値表ファイルを読み込むメソッド
	 * @param filePath 真理値表ファイルのPath
	 */
	private void loadTruthTable(String filePath) {
		try {
			//ファイルを展開
			File file = new File("./inputTruthTable.csv");
			BufferedReader br = new BufferedReader(new FileReader(file));

			//1行目を読み込み，分割
			String str = br.readLine();
			String[] variableArray = str.split(",");
			
			//massArrayを生成
			generateMassArray(variableArray);
			
			//ここで，massArrayを生成してから，各Massの状態(Sum)を保存しているため
			//少々複雑な処理になっている

			str = br.readLine();
			//2行目以降を読み込み，Massを構築していく
			while (str != null) {
				String[] variablePosArray = str.split(",");
				addVariablePosToMassArray(variableArray, variablePosArray);
				str = br.readLine();
			}

			br.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Massの配列を生成するメソッド
	 * @param variableArray : 変数名が格納された配列
	 */
	private void generateMassArray(String[] variableArray) {
		variableNum = variableArray.length - 1;
		
		//TODO: とりあえずvariableNum = 4 の時のみ
		if (variableNum == 4) {
			massArray = new Mass[1][4][4];
			Mass[][] oneTableMassArray = massArray[0];
			
			//マスを生成してmassArrayに格納する
			for (int y = 0; y < oneTableMassArray.length; y++) {
				for (int x = 0; x < oneTableMassArray[0].length; x++) {
					generateMass(y, x, oneTableMassArray, variableArray);
				}
			}
		} else {
			System.exit(1);
		}
	}
	
	/**
	 * 1つのMassを生成し，配列に格納するメソッド
	 * @param y 格納先のy座標
	 * @param x 格納先のx座標
	 * @param addTableMassArray Massを保存する２次元配列
	 * @param variableArray 変数名が格納された配列
	 */
	private void generateMass(int y, int x, Mass[][] addTableMassArray, String[] variableArray) {
		//Massインスタンスを生成
		Mass mass = new Mass();
		
		//変数の数を登録
		mass.setVariableNum(variableNum);
		
		//変数名を登録
		for (int i = 0; i < variableNum; i++) {
			mass.getVariableArray().add(variableArray[i]);
		}
		
		//変数の座標を登録
		for (int i = 0; i < Mass.POS.length; i++) {
			mass.getVariablePosMap().put(variableArray[i], Mass.POS[i][y][x]);
		}
		
		addTableMassArray[y][x] = mass;
	}

	/**
	 * @param variableArray 変数名が格納された配列
	 * @param variablePosArray CSVファイルの1行の文字列が格納された配列 0,0,0,1,1 みたいな
	 */
	private void addVariablePosToMassArray(String[] variableArray, String[] variablePosArray) {
		//String１行をint1行に変換
		int[] intVariablePosArray = new int[variablePosArray.length];
		for (int i = 0; i < intVariablePosArray.length; i++) {
			intVariablePosArray[i] = Integer.parseInt(variablePosArray[i]);
		}

		//各要素を走査
		for (int z = 0; z < massArray.length; z++) {
			for (int y = 0; y < massArray[0].length; y++) {
				for (int x = 0; x < massArray[0][0].length; x++) {
					boolean sameMassFlag = true;
					//座標を照合
					for (int i = 0; i < variableNum; i++) {
						int pos = massArray[z][y][x].getVariablePosMap().get(variableArray[i]);
						if (pos != intVariablePosArray[i]) {
							sameMassFlag = false;
							break;
						}
					}
					
					//おなし座標のマスじゃないのなら，次のマスへ
					if (sameMassFlag == false) {
						continue;
					}
					
					//状態（0, 1, DONTCARE）を設定
					massArray[z][y][x].setState(intVariablePosArray[intVariablePosArray.length - 1]);
				}
			}
		}
	}
}
