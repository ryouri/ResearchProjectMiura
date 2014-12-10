package researchproject.drawmodule;

import javax.swing.JFrame;

import researchproject.drawmodule.calc.CalcurateManager;
import researchproject.drawmodule.mass.MassManager;

public class DrawManager {

	String filePath;

	public DrawManager(String filePath) {
		this.filePath = filePath;

		init();
	}

	public void init() {
		//Mass管理クラスを生成
		MassManager massManager = new MassManager(this.filePath);

		CalcurateManager calcurateManager = new CalcurateManager();
		calcurateManager.start();

		//カルノー図描画クラスを生成し，Mass管理クラスを渡す
		KarnaughMapDrawer karnaughMapDrawer = new KarnaughMapDrawer();
		karnaughMapDrawer.setMassManager(massManager);
		karnaughMapDrawer.setCalcurateManager(calcurateManager);
		karnaughMapDrawer.init();

		//JFrameに貼り付けることで，JAppletを表示する
		JFrame frame = new JFrame("Test");
		frame.setSize(karnaughMapDrawer.getWidth(), karnaughMapDrawer.getHeight()); // サイズは適当。上で作ったインスタンスからゲットするのもいいと思います。
		frame.add(karnaughMapDrawer);
		frame.setVisible(true);
	}
}
