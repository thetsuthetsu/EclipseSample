package function;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.ObjIntConsumer;

/**
 * Consumer<T>　 <br/>
 * BiConsumer<T,U> <br/>
 * ObjIntConsumer<T> <br/>
 * 
 * 戻り値無しの処理を実行。
 */
public class ConsumerSample {

	/**
	 * void accept(T t) <br/>
	 * 指定された引数でこのオペレーションを実行します。
	 */
	private void doAccept() {
		Consumer<String> consumer = string -> System.out.println("Cunsumer : "
				+ string);
		consumer.accept("hoge");
	}

	/**
	 * void accept(T t, U u) <br/>
	 * 指定された引数でこのオペレーションを実行します。
	 */
	private void doBiAccept() {
		BiConsumer<String, Integer> consumer = (string, num) -> System.out
				.println(String.format("Consumer:[%s/%d]", string, num));
		consumer.accept("hoge", 99);
	}

	/**
	 * default Consumer<T> andThen(Consumer<? super T> after) <br/>
	 * このオペレーションを実行した後、続けてafterオペレーションを実行する合成Consumerを返します。
	 * いずれかのオペレーションの実行時に例外がスローされた場合、その例外は合成オペレーションの呼出し元に中継されます。
	 * このオペレーションの実行時に例外がスローされた場合、afterオペレーションは実行されません。
	 */
	private void doAndThen() {
		Consumer<String> hoge = string -> System.out
				.println("hoge : " + string);
		Consumer<String> fuga = string -> System.out
				.println("fuga : " + string);

		Consumer<String> piyo = hoge.andThen(fuga);
		piyo.accept("piyo");
	}

	/**
	 * default BiConsumer<T,U> andThen(BiConsumer<? super T,? super U> after) <br/>
	 * このオペレーションを実行した後、続けてafterオペレーションを実行する合成BiConsumerを返します。
	 * いずれかのオペレーションの実行時に例外がスローされた場合、その例外は合成オペレーションの呼出し元に中継されます。
	 * このオペレーションの実行時に例外がスローされた場合、afterオペレーションは実行されません。
	 */
	private void doBiAndThen() {
		BiConsumer<String, Integer> hoge = (string, num) -> System.out
				.println(String.format("hoge : [%s/%d]", string, num));
		BiConsumer<String, Integer> fuga = (string, num) -> System.out
				.println(String.format("fuga : [%s/%d]", string + "%", num + 1));

		BiConsumer<String, Integer> piyo = hoge.andThen(fuga);
		piyo.accept("piyo", 1);

	}

	private void doObjInt() {
		List<String> list = Arrays.asList("hoge", "fuga", "piyo");
		ObjIntConsumer<String> callback = (value, index) -> {
			System.out.printf("list[%d] = %s%n", index, value);
		};
		for (int i = 0; i < list.size(); i++) {
			callback.accept(list.get(i), i);
		}
	}

	public static void main(String[] args) {
		ConsumerSample sample = new ConsumerSample();
		System.out.println("--- doAccept -----");
		sample.doAccept();
		System.out.println("--- doAndThen -----");
		sample.doAndThen();
		System.out.println("--- doBiAccept -----");
		sample.doBiAccept();
		System.out.println("--- doBiAndThen -----");
		sample.doBiAndThen();
		System.out.println("--- doObjInt -----");
		sample.doObjInt();
	}

}
