package researchproject.drawmodule.loop.combine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * http://www.geocities.jp/s2412502/combination.html
 * 上記サイトのプログラムを，コンビネーション生成のために利用する
 */
public class CombinationGenerator {
	int[] c;
	int n, r, count;
	private ArrayList<ArrayList<Integer>> combine2Array;

	public ArrayList<ArrayList<Integer>> getCombine2Array() {
		return combine2Array;
	}

	public CombinationGenerator(int n) {
		if (n == 1) {
			System.out.println("1じゃ組み合わせも生成できんぜよ");
		}

		this.n = n;
		this.r = 2;
		c = new int[100];
		count = 0;
		for (int j = 0; j < 20; j++) {
			c[j] = 0;
		}

		combine2Array = new ArrayList<ArrayList<Integer>>();

		generateCombine();
	}

	public void combine(int m) {
		if (m <= r) {
			for (int i = c[m - 1] + 1; i <= n - r + m; i++) {
				c[m] = i;
				combine(m + 1);
			}
		} else {
			count = count + 1;

			//ArrayListを生成し，組み合わせを格納する3
			ArrayList<Integer> combineArray = new ArrayList<Integer>();
			for (int i = 1; i <= r; i++) {
				combineArray.add(c[i] - 1);
//				System.out.print(" " + (c[i] - 1));
			}
			combine2Array.add(combineArray);
//			System.out.println();
		}
	}

	private void generateCombine() {
		for (int i = 2; i <= n; i++) {
			for (int j = 0; j < c.length; j++) {
				c[j] = 0;
			}
			this.r = i;
			combine(1);
		}
	}

	public static void main(String[] args) {
		int n;
		int r;
		String str = "";
		while (true) {
			System.out.println();
			System.out
					.println("整数 n  を 入力して下さい　");
			System.out.println("( 終了するときは 0 を )");
			try {
				// キーボードからの入力は InputStreamReaderクラスを使う。　
				InputStreamReader isr = new InputStreamReader(System.in);
				// BufferedReaderクラスには、1行ごとにデータを
				// 読み込むメソッドreadLine()が用意されている。
				BufferedReader br = new BufferedReader(isr);
				str = br.readLine();
			} catch (IOException e) {
			}

			// スペースで区切られた文字列を個々のTokenに分け 整数化
			StringTokenizer st = new StringTokenizer(str, " ");
			n = Integer.parseInt(st.nextToken());
			if (n == 0)
				break;
			else {
				CombinationGenerator cb = new CombinationGenerator(n);
				System.out.println(" n =  " + n +  " のとき");

				for (ArrayList<Integer> combineArray : cb.getCombine2Array()) {
					for (Integer num : combineArray) {
						System.out.print(" " + num);
					}
					System.out.println();
				}

//				cb.combine(1);
				System.out.println("( 全部で " + cb.count + " 通り )");
			}
		}
	}
}