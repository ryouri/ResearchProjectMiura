package researchproject.drawmodule.loop.generator;

import researchproject.drawmodule.loop.Loop;
import researchproject.drawmodule.loop.LoopManager;
import researchproject.drawmodule.loop.LoopUnit;
import researchproject.drawmodule.mass.Mass;

public class LoopGeneratorFor5 extends LoopGenerator {

	public LoopGeneratorFor5(LoopManager loopManager) {
		super(loopManager);
	}

	private static final int ARRAY_SIZE_Z = 2;
	private static final int ARRAY_SIZE_X = 4;
	private static final int ARRAY_SIZE_Y = 4;

	/**
	 * TODO: どのようなループが生成されるか洗い出す
	 * ループの生成情報を格納する二次元配列
	 * {z方向のループの大きさ, y方向のループの大きさ, x方向のループの大きさ,
	 *  z方向にループをずらす数, y方向にループをずらす数, x方向にループをずらす数}
	 */
	public static final int[][] GENERATE_INFO_ARRAY =
		{
			{0, 3, 3, 0, 0, 0}
//			//16
//			{3, 3, 0, 0},
//			//8
//			{3, 1, 0, 2},
//			{1, 3, 2, 0},
//			//4
//			{3, 0, 0, 3},
//			{0, 3, 3, 0},
//			{1, 1, 3, 3},
//			//2
//			{1, 0, 3, 3},
//			{0, 1, 3, 3},
//			//1
//			{0, 0, 3, 3}
		};

	/**
	 * 配列のサイズ内に収まるように座標を調整する
	 * @param movedCoordinate
	 * @param maxIndex
	 * @return
	 */
	private int adjustArrayIndex(int movedCoordinate, int maxIndex) {
		int newCoordinate = movedCoordinate;
		if (newCoordinate > maxIndex - 1) {
			newCoordinate = newCoordinate - (maxIndex);
			return newCoordinate;
		} else {
			return newCoordinate;
		}
	}

	@Override
	public void generateLoop() {
		System.out.println("デバッグ, 変数の数は:" + varNum);

		int loopNum = 0;
		System.out.println(loopNum);

		//情報格納配列ごとに処理を行う
		for (int [] generateInfoArray : GENERATE_INFO_ARRAY) {
			int loopZSize = generateInfoArray[0];
			int loopYSize = generateInfoArray[1];
			int loopXSize = generateInfoArray[2];
			int moveZLoopMax = generateInfoArray[3];
			int moveYLoopMax = generateInfoArray[4];
			int moveXLoopMax = generateInfoArray[5];

			//ループを動かす数値ごとに処理
			for (int moveZLoop = 0; moveZLoop <= moveZLoopMax; moveZLoop++) {
				for (int moveYLoop = 0; moveYLoop <= moveYLoopMax; moveYLoop++) {
					for (int moveXLoop = 0; moveXLoop <= moveXLoopMax; moveXLoop++) {
						// ループを動かす数値ごとにループを生成
						Loop loop = generateBasicLoop(loopZSize, loopYSize, loopXSize);

						boolean loopSuccess = true;

						// 生成されたループの各座標の値を変更して，ループを動かす
						for (LoopUnit loopUnit : loop.getLoopUnitArray()) {
							// 配列の大きさから各座標の値がはみ出た時の処理を考慮しつつループを動かす
							loopUnit.setZ(adjustArrayIndex(loopUnit.getZ()
									+ moveZLoop, ARRAY_SIZE_Z));
							loopUnit.setY(adjustArrayIndex(loopUnit.getY()
									+ moveYLoop, ARRAY_SIZE_Y));
							loopUnit.setX(adjustArrayIndex(loopUnit.getX()
									+ moveXLoop, ARRAY_SIZE_X));

							// ループの成否のチェック
							Mass mass =
									massManager.getMassArray()[loopUnit.getZ()][loopUnit.getY()][loopUnit.getX()];
							if (mass.getState() == Mass.STATE_ZERO) {
								loopSuccess = false;
							}
						}

						// ループの成否を格納
						loop.setLoopSuccess(loopSuccess);
						// System.out.println(loopNum);

						// ループが成り立っていれば格納
						if (loopSuccess) {
							addSuccessLoop(loop);
						}

						// 生成したループを格納
						allLoopArray.add(loop);

						// 処理してほしいLoopを格納
						// Thread処理対応のため，synchronizedで囲む
						synchronized (proccessLoopArray) {
							proccessLoopArray.add(loop);
						}
					}
				}
			}
		}

		System.out.println("デバッグ, 変数の数は:" + varNum);
	}


	/**
	 * zとyとxの値から，一番左上を示すループを作成する
	 * @param z 0からこの値までの大きさのループを生成する
	 * @param y 0からこの値までの大きさのループを生成する
	 * @param x 0からこの値までの大きさのループを生成する
	 */
	private Loop generateBasicLoop(int z, int y, int x) {
		Loop loop = new Loop();

		for (int zIndex = 0; zIndex <= z; zIndex++) {
			for (int yIndex = 0; yIndex <= y; yIndex++) {
				for (int xIndex = 0; xIndex <= x; xIndex++) {
					loop.getLoopUnitArray().add(new LoopUnit(zIndex, yIndex, xIndex));
				}
			}
		}

		return loop;
	}


	/**
	 * 成り立っているループが既にSuccessLoopArrayに格納済みのループに含まれているかチェックし，
	 * 全ての既に格納済みのループに含まれていなければ格納する
	 * @param targetLoop 成り立っているループ
	 */
	private void addSuccessLoop(Loop targetLoop) {
		//そもそも成功ループ配列のサイズが0なら追加する
		synchronized (proccessLoopArray) {
			if (successLoopArray.size() == 0) {
				successLoopArray.add(targetLoop);
			}
		}

		//全ての既に格納されているループに含まれていなければ，追加する

		//すでに格納されている成り立っているループごとに処理を行う
		for (Loop successLoop : successLoopArray) {
			boolean[][][] loopExistArray = new boolean[ARRAY_SIZE_Z][ARRAY_SIZE_Y][ARRAY_SIZE_Y];

			//成功したループの座標をtrueとして，Arrayに格納する
			for (LoopUnit loopUnit : successLoop.getLoopUnitArray()) {
				loopExistArray[loopUnit.getZ()][loopUnit.getY()][loopUnit.getX()] = true;
			}

			boolean noAddFlag = true;
			//追加したいループの座標を走査して，今まで格納されているループに含まれているかを判定する
			for (LoopUnit loopUnit : targetLoop.getLoopUnitArray()) {
				if (!loopExistArray[loopUnit.getZ()][loopUnit.getY()][loopUnit.getX()]) {
					//ループに含まれていなければ
					noAddFlag = false;
				}
			}

			//すでに格納されているループに含まれているため，ループを追加しない
			if (noAddFlag) {
				return;
			}
		}

		//ここまでくれば，全ての既に格納されているループに含まれていないため，追加する
		successLoopArray.add(targetLoop);
	}
}
