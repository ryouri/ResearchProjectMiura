package researchproject.drawmodule.loop.generator;

import java.util.ArrayList;

import researchproject.drawmodule.loop.Loop;
import researchproject.drawmodule.loop.LoopManager;
import researchproject.drawmodule.mass.MassManager;

public abstract class LoopGenerator {
	protected LoopManager loopManager;

	/**
	 * カルノー図の変数の数
	 */
	protected int varNum;

	/**
	 * ループを適用する先のmassArrayを保持しているManager
	 */
	protected MassManager massManager;

	/**
	 * 成立したループのみが格納される
	 */
	protected ArrayList<Loop> successLoopArray;

	/**
	 * 生成したすべてのループが格納される
	 */
	protected ArrayList<Loop> allLoopArray;

	/**
	 * 処理してもらいたいループが格納される
	 */
	protected ArrayList<Loop> proccessLoopArray;

	public LoopGenerator(LoopManager loopManager) {
		this.loopManager = loopManager;
		this.varNum = loopManager.getVarNum();
		this.massManager = loopManager.getMassManager();
		synchronized(loopManager.getSuccessLoopArray()) {
			this.successLoopArray = loopManager.getSuccessLoopArray();
		}
		this.allLoopArray = loopManager.getAllLoopArray();
		this.proccessLoopArray = loopManager.getProcessLoopArray();
	}

	abstract public void generateLoop();
}
