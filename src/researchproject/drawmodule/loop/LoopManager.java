package researchproject.drawmodule.loop;

import java.util.ArrayList;
import java.util.HashMap;

import researchproject.drawmodule.loop.combine.CombinationGenerator;
import researchproject.drawmodule.loop.generator.LoopGenerator;
import researchproject.drawmodule.loop.generator.LoopGeneratorFor4;
import researchproject.drawmodule.loop.generator.LoopGeneratorFor5;
import researchproject.drawmodule.mass.Mass;
import researchproject.drawmodule.mass.MassManager;

public class LoopManager{

	/**
	 * カルノー図の変数の数
	 */
	private int varNum;

	/**
	 * ループを適用する先のmassArrayを保持しているManager
	 */
	private MassManager massManager;

	/**
	 * 成立したループのみが格納される
	 */
	private ArrayList<Loop> successLoopArray;

	/**
	 * 生成したすべてのループが格納される
	 */
	private ArrayList<Loop> allLoopArray;

	/**
	 * 処理してもらいたいループが格納される
	 */
	private ArrayList<Loop> processLoopArray;


	private ArrayList<ArrayList<Loop>> beforeLogicalLoop2Array;

	/**
	 * 最終的に結果として出力するループが格納される
	 */
	private ArrayList<ArrayList<Loop>> resultLoop2Array;
	/**
	 * 最終的に結果として出力する論理式の文字列が格納される
	 */
	private ArrayList<String> resultStringArray;



	public ArrayList<String> getResultStringArray() {
		return resultStringArray;
	}

	public int getVarNum() {
		return varNum;
	}

	public MassManager getMassManager() {
		return massManager;
	}

	public ArrayList<Loop> getSuccessLoopArray() {
		return successLoopArray;
	}

	public ArrayList<Loop> getAllLoopArray() {
		return allLoopArray;
	}

	public ArrayList<Loop> getProcessLoopArray() {
		return processLoopArray;
	}

	public LoopManager(int varNum, MassManager massManager) {
		this.varNum = varNum;
		this.massManager = massManager;
		init();
	}

	public void init() {
		successLoopArray = new ArrayList<Loop>();
		allLoopArray = new ArrayList<Loop>();
		processLoopArray = new ArrayList<Loop>();
		beforeLogicalLoop2Array = new ArrayList<ArrayList<Loop>>();
	}

	LoopGenerator loopGenerator;

	/**
	 * ループの生成メソッド
	 */
	public void generateLoop() {
		switch(varNum) {
		case 2:
			//generate2Loop();
			break;
		case 3:
			//generate3Loop();
			break;
		case 4:
			loopGenerator = new LoopGeneratorFor4(this);
			break;
		case 5:
			loopGenerator = new LoopGeneratorFor5(this);
			break;
		default :
			System.err.println("変数の指定がおかしいです");
			System.exit(-1);
		}

		loopGenerator.generateLoop();
	}

	public void processCombinationLoop() {
		ArrayList<ArrayList<Loop>> combinationLoopArray = new ArrayList<ArrayList<Loop>>();

		if (successLoopArray.size() == 1) {
			beforeLogicalLoop2Array.add(successLoopArray);
			return;
		}

		//Loopの組み合わせを生成
		CombinationGenerator combinationGenerator = new CombinationGenerator(successLoopArray.size());
		for (ArrayList<Integer> combineArray : combinationGenerator.getCombine2Array()) {
			ArrayList<Loop> combinationLoops = new ArrayList<Loop>();
			for (Integer num : combineArray) {
				combinationLoops.add(successLoopArray.get(num));
			}
			combinationLoopArray.add(combinationLoops);
		}

		Mass[][][] massArray = massManager.getMassArray();
		int zMax = massArray.length;
		int yMax = massArray[0].length;
		int xMax = massArray[0][0].length;

		ArrayList<ArrayList<Loop>> judgedEncircleLoopArray = new ArrayList<ArrayList<Loop>>();

		//まずは囲めていないLoopを排除する
		//組み合わせごとにLoopを処理する
		for (ArrayList<Loop> combinationLoops : combinationLoopArray) {
			//囲まれていなければならない情報の配列を生成する
			boolean[][][] encircleArray = new boolean[zMax][yMax][xMax];
			initEncircleArray(encircleArray, massArray);

			// 囲めていないマスがあるかを確認する
			for (Loop loop : combinationLoops) {
				for (LoopUnit loopUnit : loop.getLoopUnitArray()) {
					encircleArray[loopUnit.getZ()][loopUnit.getY()][loopUnit.getX()] = false;
				}
			}

			// 囲めていないマスがあればそのLoopはダメ！
			boolean loopOKFlag = true;
			for (int z = 0; z < encircleArray.length; z++) {
				for (int y = 0; y < encircleArray[0].length; y++) {
					for (int x = 0; x < encircleArray[0][0].length; x++) {
						if (encircleArray[z][y][x] == true) {
							loopOKFlag = false;
						}
					}
				}
			}

			if (loopOKFlag) {
				judgedEncircleLoopArray.add(combinationLoops);
			}
		}

		int minLoopNum = 999;
		//Loopの数が少ないLoopを抜き出す
		//最もLoopが少ない組み合わせの値を抜き出す
		for (ArrayList<Loop> combinationLoops : judgedEncircleLoopArray) {
			if (minLoopNum > combinationLoops.size()) {
				minLoopNum = combinationLoops.size();
			}
		}
		//Loopが少ない組み合わせを結果として抜き出す
		for (ArrayList<Loop> combinationLoops : judgedEncircleLoopArray) {
			if (minLoopNum == combinationLoops.size()) {
				beforeLogicalLoop2Array.add(combinationLoops);
			}
		}
	}

	private void initEncircleArray(boolean[][][] encircleArray, Mass[][][] massArray) {
		for (int z = 0; z < encircleArray.length; z++) {
			for (int y = 0; y < encircleArray[0].length; y++) {
				for (int x = 0; x < encircleArray[0][0].length; x++) {
					if (massArray[z][y][x].getState() == Mass.STATE_ONE) {
						encircleArray[z][y][x] = true;
					} else {
						encircleArray[z][y][x] = false;
					}
				}
			}
		}
	}

	//TODO:ループの組み合わせを計算していない，つまり，ループの作り方が複数存在する場合は，ミスが出る
	/**
	 * 計算されたループから論理式を生成する
	 */
	public void generateLogicalEquation() {
		//notの記号
		String not = "¬";

		resultStringArray = new ArrayList<String>();
		resultLoop2Array = new ArrayList<ArrayList<Loop>>();

		for (ArrayList<Loop> successLoops : beforeLogicalLoop2Array) {
			String logicalEquation = new String();

			//成功した各ループを走査する
			for	(Loop loop : successLoops) {
				//各変数を正負どちらで表示すべきかを保持する
				HashMap<String, Integer> logicVarMap = new HashMap<String, Integer>();

				//各ループの構成単位を走査する
				for (LoopUnit loopUnit : loop.getLoopUnitArray()) {
					Mass mass = massManager.getMassArray()[loopUnit.getZ()][loopUnit.getY()][loopUnit.getX()];
					HashMap<String, Integer> varPosMap = mass.getVariablePosMap();

					//各変数について走査する
					for (String varStr : varPosMap.keySet()) {
						int varValue = varPosMap.get(varStr);
						//既にMapに入ってたら
						if (logicVarMap.containsKey(varStr)) {
							//前回までの値を取得
							int alreadyVarValue = logicVarMap.get(varStr);
							//前回までの値と不一致，つまりその変数を論理式に直さない場合は-1を入れておく
							if (alreadyVarValue != varValue) {
								logicVarMap.put(varStr, -1);
							}
						} else {
							//まだMapに入ってないため，追加する
							logicVarMap.put(varStr, varValue);
						}
					}
				}

				//保存したMapから論理式を表示する
				for (String varStr : logicVarMap.keySet()) {
					int varValue = logicVarMap.get(varStr);
					if (varValue == 1) {
						logicalEquation += varStr;
					} else if (varValue == 0){
						logicalEquation += not + varStr;
					}
				}
				logicalEquation += " + ";
			}

			logicalEquation = logicalEquation.substring(0, logicalEquation.lastIndexOf(" + "));
			logicalEquation = logicalEquation.replace(" ", "");

			System.out.println(logicalEquation);

			resultStringArray.add(logicalEquation);
			resultLoop2Array.add(successLoops);
		}
	}

	public ArrayList<ArrayList<Loop>> getResultLoop2Array() {
		return resultLoop2Array;
	}
}
