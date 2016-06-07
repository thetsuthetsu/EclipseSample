package function;

import java.util.function.Supplier;

/**
 * Supplier<T>　サンプル<br/>
 * 引数をとらず、T型の値を返す処理を実装する。
 */
public class SupplierSample {

	private void doGet() {
		Supplier<String> supplier = () -> "hoge";
		System.out.println(supplier.get());
	}

	public static void main(String[] args) {
		SupplierSample sample = new SupplierSample();
		System.out.println("--- doGet -----");
		sample.doGet();

	}

}
