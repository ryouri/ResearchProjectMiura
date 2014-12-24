package researchproject.drawmodule.loop.generator;

import researchproject.drawmodule.loop.LoopManager;

public class LoopGeneratorFor4 extends LoopGenerator{

	public LoopGeneratorFor4(LoopManager loopManager) {
		super(loopManager);
	}

	@Override
	public void generateLoop() {
		System.out.println(varNum);
	}
}
