package function;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * Predicate<T> <br/>
 * T型の値を引数に受け取って、 boolean の値を返す処理を実装する。 <br/>
 * BiPredicate<T,U> <br/>
 * T型,U型の値を引数に受け取って、 boolean の値を返す処理を実装する。
 */
public class PredicateSample {

	/**
	 * boolean test(T t) <br/>
	 * 指定された引数でこの述語を評価します。
	 */
	private void doTest() {
		Predicate<String> predicate = string -> string.isEmpty();
		System.out.println(predicate.test(""));
		System.out.println(predicate.test("hoge"));
	}

	/**
	 * static <T> Predicate<T> isEqual(Object targetRef) <br/>
	 * 2つの引数が等しいかどうかをObjects.equals(Object, Object)に従ってテストする述語を返します。
	 */
	private void isEqual() {
		Predicate<String> isHoge = Predicate.isEqual("hoge");
		System.out.println(isHoge.test("hoge"));
		System.out.println(isHoge.test("fuga"));

		Predicate<Object> isNull = Predicate.isEqual(null);
		System.out.println(isNull.test(null));
		System.out.println(isNull.test("not null"));
	}

	/**
	 * default Predicate<T> and(Predicate<? super T> other) <br/>
	 * この述語と別の述語の短絡論理積を表す合成述語を返します。合成述語の評価時にこの述語がfalseだった場合、述語otherは評価されません。
	 * いずれかの述語の評価時にスローされた例外はすべて呼出し元に中継されます。この述語の評価時に例外がスローされた場合、述語otherは評価されません。
	 */
	private void doAnd() {
		Predicate<String> isUpperCase = string -> string.matches("[A-Z]+");
		Predicate<String> isAlphabet = string -> string.matches("[a-zA-Z]+");

		Predicate<String> predicate = isAlphabet.and(isUpperCase);

		System.out.println(predicate.test("HOGE"));
		System.out.println(predicate.test("hoge"));
		System.out.println(predicate.test("1234"));
	}

	/**
	 * default Predicate<T> or(Predicate<? super T> other) <br/>
	 * この述語と別の述語の短絡論理和を表す合成述語を返します。合成述語の評価時にこの述語がtrueだった場合、述語otherは評価されません。
	 * いずれかの述語の評価時にスローされた例外はすべて呼出し元に中継されます。この述語の評価時に例外がスローされた場合、述語otherは評価されません。
	 */
	private void doOr() {
		Predicate<String> isUpperCase = string -> string.matches("[A-Z]+");
		Predicate<String> isNumber = string -> string.matches("\\d+");

		Predicate<String> predicate = isNumber.or(isUpperCase);

		System.out.println(predicate.test("HOGE"));
		System.out.println(predicate.test("1234"));
		System.out.println(predicate.test("hoge"));
	}

	/**
	 * default Predicate<T> negate() <br/>
	 * この述語の論理否定を表す述語を返します。
	 */
	private void doNegate() {
		Predicate<String> isEmpty = string -> string.isEmpty();

		Predicate<String> isNotEmpty = isEmpty.negate();

		System.out.println(isNotEmpty.test("hoge"));
		System.out.println(isNotEmpty.test(""));
	}

	/**
	 * boolean test(T t, U u) <br/>
	 * 指定された引数でこの述語を評価します。
	 */
	private void doBiTest() {
		BiPredicate<BigDecimal, BigDecimal> predicate = (d1, d2) -> d1
				.equals(d2);
		System.out.println(predicate.test(new BigDecimal("0.1"),
				new BigDecimal(0.1)));
		System.out.println(predicate.test(new BigDecimal("0.1"),
				new BigDecimal(BigInteger.ONE, 1)));
		System.out.println(predicate.test(new BigDecimal("0.1"),
				BigDecimal.valueOf(0.1)));
	}

	public static void main(String[] args) {
		PredicateSample sample = new PredicateSample();
		System.out.println("--- doTest -----");
		sample.doTest();
		System.out.println("--- isEqual -----");
		sample.isEqual();
		System.out.println("--- doAnd -----");
		sample.doAnd();
		System.out.println("--- doOr -----");
		sample.doOr();
		System.out.println("--- doNegate -----");
		sample.doNegate();

		System.out.println("--- doBiTest -----");
		sample.doBiTest();
	}

}
