import java.util.TreeSet;
import java.util.concurrent.ExecutionException;

public class TreeSetBugPattern2 {
	protected TreeSet<Log> logSet = new TreeSet<Log>();

	public class Log implements Comparable<Log> {
		private long id;
		private String ids;

		public Log(long id) {
			super();
			this.id = id;
			this.ids = String.valueOf(id);
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getIds() {
			return ids;
		}

		@Override
		public int compareTo(Log o) {
			if (id > o.id) {
				return 1;
			} else if (id < o.id) {
				return -1;
			}
			return 0;
		}

		@Override
		public String toString() {
			return String.format("[%s:%d]", ids, id);
		}
	}

	private void printLogSet() {
		for (Log log : logSet) {
			System.out.print(log);
		}
		System.out.println("");
	}

	// テスト本体
	private void test() throws InterruptedException, ExecutionException {
		Log log1 = new Log(1);
		Log log2 = new Log(2);
		Log log3 = new Log(3);
		logSet.add(log1);
		logSet.add(log2);
		logSet.add(log3);

		printLogSet();

		// データ汚染
		log2.setId(4);

		printLogSet();

		System.out.println(logSet.contains(log3));
	}

	public static void main(String[] args) {
		TreeSetBugPattern2 director = new TreeSetBugPattern2();
		try {
			director.test();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
