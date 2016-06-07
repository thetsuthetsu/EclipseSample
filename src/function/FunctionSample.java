package function;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Function<T, R> <br/>
 * T型の引数を受け取って、 R 型の値を返す処理を実装する。 BiFunction<T,U,R> <br/>
 * T型,U型の引数を受け取って、 R 型の値を返す処理を実装する。
 */
public class FunctionSample {
	/**
	 * applyサンプル.<br/>
	 * 指定引数への関数適用。<br/>
	 */
	private void doApply() {
		Function<String, Integer> function = string -> Integer.parseInt(string);
		System.out.println(function.apply("12345"));

		function = param -> Math.abs(Integer.parseInt(param));
		System.out.println(function.apply("-321"));
	}

	/**
	 * 合成関数composeサンプル.<br/>
	 * シングルクォート結果にダブルクォート結果を複合する関数。
	 */
	private void doCompose() {
		Function<String, String> wrapDoubleQuotation = str -> "\"" + str + "\"";
		Function<String, String> wrapSingleQuotation = str -> "'" + str + "'";

		Function<String, String> wrapDoubleAndSingleQuotation = wrapDoubleQuotation
				.compose(wrapSingleQuotation);
		String result = wrapDoubleAndSingleQuotation.apply("hoge");

		System.out.println(result);
	}

	/**
	 * 合成関数andThenサンプル.<br/>
	 * ダグルクォート結果にシングルクォート結果を複合する関数。
	 */
	private void doAndThen() {
		Function<String, String> wrapDoubleQuotation = str -> "\"" + str + "\"";
		Function<String, String> wrapSingleQuotation = str -> "'" + str + "'";

		Function<String, String> wrapDoubleAndSingleQuotation = wrapDoubleQuotation
				.andThen(wrapSingleQuotation);
		String result = wrapDoubleAndSingleQuotation.apply("hoge");

		System.out.println(result);
	}

	/**
	 * identityサンプル.<br/>
	 * 常に入力引数を返す関数を返す。
	 */
	private void doIdentity() {
		Function<String, String> function = Function.identity();
		System.out.println(function.apply("string message"));
	}

	/**
	 * R apply(T t, U u).<br/>
	 * 指定された引数にこの関数を適用します。<br/>
	 */
	private void doBiApply() {
		BiFunction<Double, String, String> function = (d, string) -> String
				.format("[%.36f:%.36f]", new BigDecimal(d), new BigDecimal(
						string));
		System.out.println(function.apply(0.1, "0.1"));
	}

	/**
	 * default <V> BiFunction<T,U,V> andThen(Function<? super R,? extends V>
	 * after) <br/>
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 */
	private void doBiAndThen() {
		BiFunction<BigInteger, Integer, BigDecimal> construction = (number,
				scale) -> new BigDecimal(number, scale);
		Function<BigDecimal, String> format = d -> String.format("[%.36f]", d);
		BiFunction<BigInteger, Integer, String> constAndFormat = construction
				.andThen(format);

		System.out.println(constAndFormat.apply(new BigInteger("12345"), 5));
	}

	public static void main(String[] args) {
		FunctionSample sample = new FunctionSample();
		System.out.println("--- doApply ----");
		sample.doApply();
		System.out.println("--- doCompose ----");
		sample.doCompose();
		System.out.println("--- doAndThen ----");
		sample.doAndThen();
		System.out.println("--- doIdentity ----");
		sample.doIdentity();
		System.out.println("--- doBiApply ----");
		sample.doBiApply();
		System.out.println("--- doBiAndThen ----");
		sample.doBiAndThen();
	}

}
