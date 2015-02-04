package researchproject.inputmodule;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartInput {
	private StartInput thisObject = this;
	
	private JFrame frame;
	private JPanel radioPanel;
	private JPanel logicalPanel;

	private RadioInputer radioInputer;
	private LogicalInputer logicalInputer;
	
	public JFrame getFrame() {
		return frame;
	}

	public StartInput() {
		init();
	}
	
	public void init() {
		//ラジオボタン入力モジュールをインスタンス化
	    radioInputer = new RadioInputer();
	    radioInputer.setStartInput(this);
	    radioInputer.init();

		//Panelに貼り付けてから，JFrameに貼り付ける
		radioPanel = new JPanel();
		radioPanel.setSize(radioInputer.getWidth(), radioInputer.getHeight());
		radioPanel.add(radioInputer);
		
		//JFrameに貼り付けることで，JAppletを表示する
		frame = new JFrame("入力モジュール");
		frame.setSize(radioInputer.getWidth(), radioInputer.getHeight()); // サイズは適当。上で作ったインスタンスからゲットするのもいいと思います。
		frame.setVisible(true);
		frame.getContentPane().add(radioPanel, BorderLayout.CENTER);
		
		//論理式入力モジュールをインスタンス化
		logicalInputer = null;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					logicalInputer = new LogicalInputer(frame, thisObject);
					logicalPanel = logicalInputer.getPanel();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void changeRadio() {
		frame.getContentPane().remove(logicalPanel);
		frame.setSize(radioInputer.getWidth(), radioInputer.getHeight()); // サイズは適当。上で作ったインスタンスからゲットするのもいいと思います。
		frame.setVisible(true);
		frame.getContentPane().add(radioPanel, BorderLayout.CENTER);
	}
	
	public void changeLogical() {
		frame.getContentPane().remove(radioPanel);
		logicalInputer.setPanelToFrame();
	}

	static public void main(String[] passedArgs) {
		new StartInput();
	}
}
