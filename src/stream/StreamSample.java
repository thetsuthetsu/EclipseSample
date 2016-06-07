package stream;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class StreamSample {

	@Rule
	public TestName testName = new TestName();

	public class Person {
		private String name;
		private int age;
		private List<Person> children = new ArrayList<Person>();

		public Person(String name) {
			this(name, 0);
		}

		public Person(String name, int age, Person... children) {
			this.name = name;
			this.age = age;
			for (Person c : children) {
				this.children.add(c);
			}
		}

		public String getName() {
			return name;
		}

		public int getAge() {
			return age;
		}

		public List<Person> getChildren() {
			return children;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			for (Person c : children) {
				builder.append(c.toString()).append(",");
			}

			return String.format("%s:[%d] {%s}", this.name, this.age,
					builder.toString());
		}
	}

	private void printMethod() {
		System.out.println(String.format("--- %s -----------------",
				testName.getMethodName()));
	}

	/**
	 * Stream<String> -> Stream<Person>
	 */
	@Test
	public void doReflection() {
		printMethod();
		String[] names = new String[] { "Tom", "Bob", "Alice" };
		Stream<String> stream = Stream.of(names);
		stream.map(Person::new).forEach(System.out::println);
	}

	/**
	 * Stream<Person> -> Stream<String> -> List<String>
	 */
	@Test
	public void doConvertList() {
		printMethod();
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Tom", 21));
		persons.add(new Person("Bob", 25));
		persons.add(new Person("Alice", 19));

		List<String> nameList = persons.stream().map(Person::getName)
				.collect(Collectors.toList());

		nameList.stream().forEach(System.out::println);
	}

	/**
	 * List<Person> -> Stream<Person> -> Stream<String> -> csv出力
	 */
	@Test
	public void doPrintCSV() {
		printMethod();
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Tom", 21));
		persons.add(new Person("Bob", 25));
		persons.add(new Person("Alice", 19));
		String nameCSV = persons.stream()
				.map(p -> String.format("\"%s\"", p.getName())) // クォート不要なら、.map(Person::getName)でOK
				.collect(Collectors.joining(","));

		System.out.println(nameCSV);
	}

	/**
	 * List<Person> -> Stream<Person> -> filter
	 */
	@Test
	public void doFilter() {
		printMethod();
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Tom", 21));
		persons.add(new Person("Bob", 25));
		persons.add(new Person("Alice", 19));
		persons.add(new Person("Ken", 18));

		persons.stream().filter(person -> person.getAge() < 20)
				.forEach(System.out::println);
	}

	/**
	 * List<Person> -> Stream<Person> -> Stream<Integer> -> distinct
	 */
	@Test
	public void doDistinctList() {
		printMethod();
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Tom", 21));
		persons.add(new Person("Bob", 25));
		persons.add(new Person("Alice", 19));
		persons.add(new Person("Mike", 19));

		persons.stream().map(Person::getAge).distinct()
				.forEach(System.out::println);
	}

	/**
	 * Stream<Person> -> sort
	 */
	@Test
	public void doSortAge() {
		printMethod();
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Tom", 21));
		persons.add(new Person("Bob", 25));
		persons.add(new Person("Alice", 19));

		persons.stream().sorted(Comparator.comparingInt(Person::getAge))
				.forEach(System.out::println);
		persons.stream()
				.sorted(Comparator.comparingInt(Person::getAge).reversed())
				.forEach(System.out::println);
	}

	/**
	 * Stream<Person> -> groupingBy -> Map<Char, List<Person>>
	 */
	@Test
	public void doGroupBy() {
		printMethod();
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Tom", 21));
		persons.add(new Person("John", 18));
		persons.add(new Person("Jack", 19));

		Map<Object, List<Person>> nameIndex = persons.stream().collect(
				Collectors.groupingBy(p -> p.getName().charAt(0)));

		System.out.println(nameIndex);
	}

	/**
	 * Stream<Person> -> Stream<Person::getChildren()>
	 */
	@Test
	public void doFlatMap2() {
		printMethod();
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Tom", 21, new Person("Tomas", 1), new Person(
				"Tommy", 0)));
		persons.add(new Person("Bob", 25, new Person("Bobby", 2)));
		persons.add(new Person("Alice", 19));

		persons.stream().flatMap(person -> person.getChildren().stream())
				.forEach(System.out::println);
	}

	/**
	 * Stream<Person> -> Stream<Person::getChildren()> ->
	 * Stream<Person::getChildren()>
	 */
	@Test
	public void doFlatMap3() {
		printMethod();
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("A", 100, new Person("AA", 50, new Person("AAA",
				10), new Person("AAB", 9)), new Person("AB", 49, new Person(
				"ABA", 8))));
		persons.add(new Person("B", 99, new Person("BB", 48, new Person("BBA",
				7), new Person("BBB", 6))));
		persons.add(new Person("C", 98));

		persons.stream().flatMap(person -> person.getChildren().stream())
				.flatMap(person -> person.getChildren().stream())
				.forEach(System.out::println);
	}

	/**
	 * Stream -> Array -> Streamの無駄を暴く
	 */
	@Test
	public void doStreamArrayStream() {
		printMethod();
		List<Person> persons = new ArrayList<>();
		for (int i = 0; i < 1000; i++) {
			persons.add(new Person(String.format("person%d", i), i));
		}

		long st = System.nanoTime();
		Arrays.stream(
				Arrays.stream(
						Arrays.stream(persons.stream().toArray(Person[]::new))
								.toArray(Person[]::new)).toArray(Person[]::new))
				.toArray(Person[]::new);
		System.out.println(String.format("pass1: %d(ns)", System.nanoTime()
				- st));

		st = System.nanoTime();
		persons.stream();
		System.out.println(String.format("pass2: %d(ns)", System.nanoTime()
				- st));
	}

	/**
	 * 一度終端処理をしたstreamは二度と使えないこと。
	 */
	@Test(expected = IllegalStateException.class)
	public void doCount() {
		printMethod();
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Tom", 21));
		persons.add(new Person("John", 18));
		persons.add(new Person("Jack", 19));

		Stream<Person> st = persons.stream();
		System.out.println(String.format("st:[%d]", st.count()));
		st.count();
	}

	@Test
	public void doFindOptional() {
		printMethod();
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Tom", 21));
		persons.add(new Person("John", 18));
		persons.add(new Person("Jack", 19));

		Optional<Person> first = persons.stream().findFirst();
		first.ifPresent(s -> {
			System.out.println(s);
		});

		Optional<Person> any = persons.stream().findAny();
		any.ifPresent(s -> {
			System.out.println(s);
		});
	}

	@Test
	public void doMax() {
		printMethod();
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Tom", 21));
		persons.add(new Person("John", 18));
		persons.add(new Person("Jack", 19));

		Optional<Person> max = persons.stream().max(
				Comparator.comparingInt(Person::getAge));
		max.ifPresent(s -> {
			System.out.println(s);
		});
	}

	@Test
	public void doMin() {
		printMethod();
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Tom", 21));
		persons.add(new Person("John", 18));
		persons.add(new Person("Jack", 19));

		Optional<Person> min = persons.stream().min(
				Comparator.comparingInt(Person::getAge));
		min.ifPresent(s -> {
			System.out.println(s);
		});
	}

	@Test
	public void doSummary() {
		printMethod();
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Tom", 21));
		persons.add(new Person("John", 18));
		persons.add(new Person("Jack", 19));

		System.out.println(persons.stream().collect(
				Collectors.summarizingInt(Person::getAge)));
	}

	@Test
	public void doAllMatch() {
		printMethod();
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Jonson", 21));
		persons.add(new Person("John", 18));
		persons.add(new Person("Jack", 19));

		assertThat(persons.stream().allMatch(p -> p.getName().startsWith("J")),
				is(true));
	}

	@Test
	public void doAnyMatch() {
		printMethod();
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Tom", 21));
		persons.add(new Person("John", 18));
		persons.add(new Person("Jack", 19));

		assertThat(persons.stream().anyMatch(p -> p.getName().startsWith("J")),
				is(true));
	}

	@Test
	public void doNoneMatch() {
		printMethod();
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Tom", 21));
		persons.add(new Person("John", 18));
		persons.add(new Person("Jack", 19));

		assertThat(
				persons.stream().noneMatch(p -> p.getName().startsWith("X")),
				is(true));
	}
}
