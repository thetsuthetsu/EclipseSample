package sync;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SyncSample1 {
    Map<String, List<Integer>> cache = new HashMap<String, List<Integer>>();

    private void overwriteCache(String key, int count) throws InterruptedException {
        List<Integer> list = null;
        synchronized (cache) {
            list = cache.get(key);
            if (list == null) {
                list = new ArrayList<Integer>();
                cache.put(key, list);
            }
        }
        synchronized (list) { // 共有マップの要素の排他制御する。このsyncがなければレースコンディションが発生する。
            for (int i = 0; i < count; i++) {
                list.add(i);
            }
            Thread.sleep(10000);

            Iterator<Integer> it = list.stream().iterator();
            for (int i = 0; i < count; i++) {
                assertThat(it.next().intValue(), is(i));
            }
        }
    }

    public static void main(String[] args) {
        final int threadNum = 10;
        final SyncSample1 sample = new SyncSample1();
        ExecutorService ex = Executors.newFixedThreadPool(threadNum);
        for (int i = 0; i < threadNum; i++) {
            //final String key = String.format("key[%05d]", i);
            final String key = "12345";
            ex.execute(() -> {
                try {
                    sample.overwriteCache(key, 100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        ex.shutdown();
    }

}
