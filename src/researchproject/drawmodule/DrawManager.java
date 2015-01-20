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
		calcurateManager.setMassManager(massManager);
		calcurateManager.start();

		//4変数
		//カルノー図描画クラスを生成し，Mass管理クラスを渡す
//		KarnaughMapVariable4Drawer karnaughMapDrawer = new KarnaughMapVariable4Drawer();
//		karnaughMapDrawer.setMassManager(massManager);
//		karnaughMapDrawer.setCalcurateManager(calcurateManager);
//		karnaughMapDrawer.init();

		//矢部さんの5変数プログラムを実行
		KarnaughMapVariable5Drawer karnaughMapDrawer = new KarnaughMapVariable5Drawer();
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
