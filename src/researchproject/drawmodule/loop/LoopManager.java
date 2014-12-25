package researchproject.drawmodule.loop;

import java.util.ArrayList;
import java.util.HashMap;

import researchproject.drawmodule.loop.generator.LoopGenerator;
import researchproject.drawmodule.loop.generator.LoopGeneratorFor4;
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
	private ArrayList<Loop> proccessLoopArray;



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

	public ArrayList<Loop> getProccessLoopArray() {
		return proccessLoopArray;
	}

	public LoopManager(int varNum, MassManager massManager) {
		this.varNum = varNum;
		this.massManager = massManager;
		init();
	}

	public void init() {
		successLoopArray = new ArrayList<Loop>();
		allLoopArray = new ArrayList<Loop>();
		proccessLoopArray = new ArrayList<Loop>();
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
		default :
			System.err.println("変数の指定がおかしいです");
			System.exit(-1);
		}

		loopGenerator.generateLoop();
	}

	//TODO:ループの組み合わせを計算していない，つまり，ループの作り方が複数存在する場合は，ミスが出る
	/**
	 * 計算されたループから論理式を生成する
	 */
	public void generateLogicalEquation() {
		//notの記号
		String not = "¬";
		String logicalEquation = new String();

		//成功した各ループを走査する
		for	(Loop loop : successLoopArray) {
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

		System.out.println(logicalEquation);
	}
}
