package function;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Lambda関数はクロージャではない。<br/>
 * クロージャ：
 * 関数内に出現する自由変数（関数内で宣言されていない変数）の解決の際、実行時の環境ではなく、関数を定義した環境の変数を参照できるようなデータ構造。
 */

public class LambdaIsnotClosure {
	public static void main(String[] args) {
		List<Supplier<String>> list = new ArrayList<>();
		for (int[] i = { 1 }; i[0] <= 10; ++i[0]) {
			list.add(() -> String.format("%d番目", i[0]));
		}
		list.forEach(s -> System.out.println(s.get()));
	}
}
