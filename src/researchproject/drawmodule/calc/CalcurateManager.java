package researchproject.drawmodule.calc;

import researchproject.drawmodule.loop.LoopManager;
import researchproject.drawmodule.mass.MassManager;

public class CalcurateManager extends Thread{
	private MassManager massManager;

	private LoopManager loopManager;

	public LoopManager getLoopManager() {
		return loopManager;
	}

	public void setMassManager(MassManager massManager) {
		this.massManager = massManager;
	}

	@Override
	public void run() {
		super.run();

		//TODO: ループの生成プログラムを書く
		loopManager = new LoopManager(massManager.getVariableNum(), massManager);
		loopManager.generateLoop();
		loopManager.processCombinationLoop();
		loopManager.generateLogicalEquation();
	}


}
