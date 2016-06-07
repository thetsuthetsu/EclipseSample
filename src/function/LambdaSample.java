package function;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LambdaSample {
	/**
	 * 　カスタム関数型インターフェース　
	 */
	@FunctionalInterface
	public interface MyFunctionInterface {
		public String example(int n);
	}

	// 関数型インターフェースCallableへのlambda式利用
	private void doCallable() throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newSingleThreadExecutor();
		try {
			Future<Integer> future = pool.submit(() -> {
				return 123;
			});
			System.out.println("result:" + future.get());
		} finally {
			pool.shutdownNow();
		}
	}

	// 関数型インターフェースComparatorへのlambda式利用
	private void doComparator() {
		List<Integer> list = Arrays.asList(1, 3, 2);
		Collections.sort(list, (o1, o2) -> (o1 - o2));
		// Collections.sort(list, (o1, o2) -> (o2 - o1));
		// Collections.sort(list, (o1, o2) -> Integer.compare(o1, o2));
		for (Integer i : list) {
			System.out.println(i);
		}
	}

	// 関数型インターフェースConsumerへのlambda式利用
	private void doConsumer() {
		List<Integer> list = Arrays.asList(1, 3, 2);
		list.forEach(System.out::println);
	}

	// カスタムインターフェースMyFunctionInterfaceへのlambda式利用
	private void doMyFunctionInterface() {
		MyFunctionInterface func = n -> "example:" + n;
		// MyFunctionInterface func = (int n) -> {
		// return "example:" + n;
		// };
		System.out.println(func.example(99));
	}

	public static void main(String[] args) throws InterruptedException,
			ExecutionException {
		LambdaSample sample = new LambdaSample();

		System.out.println("--- doCallable -----");
		sample.doCallable();
		System.out.println("--- doSort -----");
		sample.doComparator();
		System.out.println("--- doConsumer -----");
		sample.doConsumer();
		System.out.println("--- doMyFunctionInterface -----");
		sample.doMyFunctionInterface();
	}
}
