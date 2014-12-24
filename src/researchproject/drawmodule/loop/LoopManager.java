package researchproject.drawmodule.loop;

import java.util.ArrayList;

import researchproject.drawmodule.loop.generator.LoopGenerator;
import researchproject.drawmodule.loop.generator.LoopGeneratorFor4;
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
}
