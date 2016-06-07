package function;

import java.math.BigDecimal;
import java.util.function.BinaryOperator;

/**
 * BinaryOperator<T>
 */
public class BinaryOperatorSample {
	/**
	 * static <T> BinaryOperator<T> maxBy(Comparator<? super T> comparator)
	 */
	private void doMaxBy() {
		BinaryOperator<BigDecimal> maxBy = BinaryOperator
				.maxBy(BigDecimal::compareTo);
		System.out.println(maxBy.apply(new BigDecimal(0.1), new BigDecimal(
				"0.1")));
	}

	/**
	 * static <T> BinaryOperator<T> minBy(Comparator<? super T> comparator)
	 */
	private void doMinBy() {
		BinaryOperator<BigDecimal> minBy = BinaryOperator
				.minBy(BigDecimal::compareTo);
		System.out.println(minBy.apply(new BigDecimal(0.1), new BigDecimal(
				"0.1")));
	}

	public static void main(String[] args) {
		BinaryOperatorSample sample = new BinaryOperatorSample();
		System.out.println("--- doMaxBy -----");
		sample.doMaxBy();
		System.out.println("--- doMinBy -----");
		sample.doMinBy();
	}
}
