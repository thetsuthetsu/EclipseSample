package sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SyncObjectAndField {
    private final Object field;

    public SyncObjectAndField() {
        super();
        this.field = new Object();
    }

    private synchronized void exec1() {
        System.out.println("exec1");
    }

    private synchronized void exec2() {
        System.out.println("exec2");
    }

    private void execField() {
        synchronized (field) {
            System.out.println("execField");
        }
    }

    public static void main(String[] args) {
        SyncObjectAndField master = new SyncObjectAndField();
        ExecutorService ex = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 2; i++) {
            ex.execute(() -> {
                master.exec1();
                master.exec2();
                master.execField();
            });
        }
        ex.shutdown();
    }

}
