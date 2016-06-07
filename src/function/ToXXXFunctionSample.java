package function;

import java.math.BigDecimal;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class ToXXXFunctionSample {

	private void doInt() {
		ToIntFunction<String> toPrimitive = string -> string.length();
		System.out.println(toPrimitive.applyAsInt("hoge"));
	}

	private void doLong() {
		ToLongFunction<String> toPrimitive = string -> Long.valueOf(string)
				.longValue();
		System.out.println(toPrimitive.applyAsLong("12345"));
	}

	private void doDouble() {
		ToDoubleFunction<BigDecimal> toPrimitive = d -> d.doubleValue();
		System.out.println(String.format("%.99f",
				toPrimitive.applyAsDouble(new BigDecimal(0.1))));
	}

	public static void main(String[] args) {
		ToXXXFunctionSample sample = new ToXXXFunctionSample();
		System.out.println("--- doInt -----");
		sample.doInt();
		System.out.println("--- doLong -----");
		sample.doLong();
		System.out.println("--- doDouble -----");
		sample.doDouble();

	}
}
