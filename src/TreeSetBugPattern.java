import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TreeSetBugPattern {
	public class Log implements Comparable<Log> {
		private long id;
		private String message;

		public Log(long id, String message) {
			super();
			this.id = id;
			this.message = message;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
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
	}

	private final int size = 5;
	private final long threshold = 50;
	protected TreeSet<Log> logSet = new TreeSet<Log>();

	private boolean addLog(Log log) {
		if (!logSet.add(log)) {
			return false;
		}
		if (logSet.size() > size) {
			Log del = logSet.first();
			logSet.remove(del);
		} else if (logSet.size() < size) {
			return false;
		}

		Log first = logSet.first();
		Log last = logSet.last();
		long diff = last.getId() - first.getId();
		if (diff <= threshold) {
			printLogSet();
			logSet.clear();
			return true;
		}
		return false;
	}

	private void printLogSet() {
		System.out.print("logSet[");
		for (Log log : logSet) {
			System.out.print(log.getId() + ",");
		}
		System.out.println("]");
	}

	private List<Log> createLogs(int count) {
		Random rd = new Random(System.currentTimeMillis());
		List<Log> logs = new ArrayList<Log>();
		for (int i = 0; i < count; i++) {
			int id = rd.nextInt(count);
			logs.add(new Log((long) id, "message[" + i + "]"));
		}
		return logs;
	}

	// テスト本体
	private void test() throws InterruptedException, ExecutionException {
		final List<Log> logs = createLogs(1000);

		ExecutorService pool = Executors.newFixedThreadPool(2);
		try {
			Future<?> future1 = pool.submit(new Runnable() {
				@Override
				public void run() {
					for (Log log : logs) {
						addLog(log);
					}
				}
			});
			Future<?> future2 = pool.submit(new Runnable() {
				@Override
				public void run() {
					for (Log log : logs) { // logを更新する他の処理
					}
				}
			});
			future1.get();
			future2.get();
		} finally {
			pool.shutdown();
		}
	}

	public static void main(String[] args) {
		TreeSetBugPattern director = new TreeSetBugPattern();
		try {
			director.test();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
