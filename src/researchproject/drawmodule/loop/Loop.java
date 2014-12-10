package researchproject.drawmodule.loop;

import java.util.ArrayList;

public class Loop {
	public Loop() {
		this.loopUnitArray = new ArrayList<LoopUnit>();
		this.loopSuccess = false;
	}

	public void addOneLoopUnit(int z, int y, int x) {
		loopUnitArray.add(new LoopUnit(z, y, x));
	}

	private ArrayList<LoopUnit> loopUnitArray;

	private boolean loopSuccess;

	public ArrayList<LoopUnit> getLoopUnitArray() {
		return loopUnitArray;
	}

	public boolean isLoopSuccess() {
		return loopSuccess;
	}

	public void setLoopSuccess(boolean loopSuccess) {
		this.loopSuccess = loopSuccess;
	}
}
