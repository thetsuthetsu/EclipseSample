package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolSample {
    private void runSingleThreadExecutor(int taskNum) {
        final AtomicInteger threadNumber = new AtomicInteger(1);
        ExecutorService ex = Executors.newSingleThreadExecutor(r -> {
            // この処理は１度しか実行されない
            Thread t = new Thread(r);
            t.setName(String.format("SingleThrad-%d", threadNumber.getAndIncrement()));
            return t;
        });
        for (int i = 0; i < taskNum; i++) {
            ex.execute(() -> {
                String thName = Thread.currentThread().getName();
                System.out.println("start[" + thName + "]");
                System.out.println("end[" + thName + "]");
            });
        }
        ex.shutdown();
    }

    private void runFixedThreadPool(int taskNum, int poolNum) throws InterruptedException, ExecutionException {
        final AtomicInteger threadNumber = new AtomicInteger(1);
        ExecutorService ex = Executors.newFixedThreadPool(poolNum, r -> {
            // この処理はpoolNum回しか実行されない
            Thread t = new Thread(r);
            t.setName(String.format("FixedThrad-%d", threadNumber.getAndIncrement()));
            return t;
        });
        for (int i = 0; i < taskNum; i++) {
            ex.execute(() -> {
                String thName = Thread.currentThread().getName();
                System.out.println("start[" + thName + "]");
                System.out.println("end[" + thName + "]");
            });
        }
        ex.shutdown();
    }

    private void callFixedThreadPool(int taskNum, int poolNum) throws InterruptedException, ExecutionException {
        final AtomicInteger threadNumber = new AtomicInteger(1);
        ExecutorService ex = Executors.newFixedThreadPool(poolNum, r -> {
            // この処理はpoolNum回しか実行されない
            Thread t = new Thread(r);
            t.setName(String.format("FixedThrad-%d", threadNumber.getAndIncrement()));
            return t;
        });
        List<Future<String>> fs = new ArrayList<Future<String>>();
        for (int i = 0; i < taskNum; i++) {
            final int taskNo = i + 1;
            fs.add(ex.submit(() -> {
                return String.format("task[%d]-thread[%s]", taskNo, Thread.currentThread().getName());
            }));
        }
        ex.shutdown();
        for (Future<String> f : fs) {
            System.out.println(f.get());
        }
    }

    public static void main(String[] args) {
        try {
            ThreadPoolSample sample = new ThreadPoolSample();
            sample.runSingleThreadExecutor(5);
            sample.runFixedThreadPool(10, 3);
            sample.callFixedThreadPool(10, 3);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
