package researchproject.drawmodule;

import researchproject.drawmodule.mass.MassManager;

public class DrawManager {
	
	String filePath;
	
	public DrawManager(String filePath) {
		this.filePath = filePath;
		
		init();
	}

	public void init() {
		new MassManager(this.filePath);
	}
	
	public static void main(String[] Arg0) {
		new DrawManager("inputTruthTable.csv");
	}
}
