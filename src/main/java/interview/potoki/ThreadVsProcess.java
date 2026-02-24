package interview.potoki;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Multithreading: Process vs Thread в Java.
 * Примеры для Senior Automation / SDET — в соответствии с разделом базы знаний.
 */
public class ThreadVsProcess {

    // ==================== 2. Race condition: count++ не атомарен ====================
    static class UnsafeCounter {
        private int count = 0;
        public void increment() { count++; }  // read -> add -> write
        public int get() { return count; }
    }

    // ==================== 7. synchronized ====================
    static class SyncCounter {
        private int count = 0;
        public synchronized void increment() { count++; }
        public synchronized int get() { return count; }
    }

    // ==================== 7. volatile (только видимость, не атомарность) ====================
    static class VolatileFlag {
        private volatile boolean stop = false;
        public void requestStop() { stop = true; }
        public boolean isStopped() { return stop; }
    }

    // ==================== 7. ReentrantLock ====================
    static class LockCounter {
        private final ReentrantLock lock = new ReentrantLock();
        private int count = 0;
        public void increment() {
            lock.lock();
            try { count++; } finally { lock.unlock(); }
        }
        public int get() {
            lock.lock();
            try { return count; } finally { lock.unlock(); }
        }
    }

    // ==================== 7. AtomicInteger ====================
    static class AtomicCounter {
        private final AtomicInteger count = new AtomicInteger(0);
        public void increment() { count.incrementAndGet(); }
        public int get() { return count.get(); }
    }

    // ==================== 6. Deadlock (упрощённый пример) ====================
    static class DeadlockDemo {
        private final Object lockA = new Object();
        private final Object lockB = new Object();
        void method1() {
            synchronized (lockA) {
                try { Thread.sleep(10); } catch (InterruptedException ignored) {}
                synchronized (lockB) { /* ... */ }
            }
        }
        void method2() {
            synchronized (lockB) {
                try { Thread.sleep(10); } catch (InterruptedException ignored) {}
                synchronized (lockA) { /* ... */ }
            }
        }
        // Решение: единый порядок захвата (всегда сначала lockA, потом lockB).
    }

    // ==================== 4. Thread: start() vs run() ====================
    static class WorkerThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread: " + Thread.currentThread().getName());
        }
    }

    // ==================== 4. Runnable + lambda ====================
    // Thread t = new Thread(() -> { ... });

    // ==================== 4. Callable + Future ====================
    static Integer callableTask() throws Exception {
        return 42;
    }

    // ==================== 5. Состояния потока (для демонстрации) ====================
    static void printThreadState(Thread t) {
        System.out.println(t.getName() + " state: " + t.getState());
        // NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, TERMINATED
    }

    // ==================== 8. ExecutorService, ThreadPoolExecutor ====================
    static void executorServiceDemo() throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Integer> f = es.submit(() -> 100);
        System.out.println("Future.get() = " + f.get()); // блокируется
        es.shutdown();
        es.awaitTermination(2, TimeUnit.SECONDS);
    }

    // ==================== 8. CompletableFuture ====================
    static void completableFutureDemo() throws Exception {
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> 1)
                .thenApply(n -> n + 1)
                .thenApply(n -> n * 2);
        System.out.println("CompletableFuture result: " + cf.get());
    }

    // ==================== 7. ConcurrentHashMap ====================
    static void concurrentMapDemo() {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("a", 1);
        map.putIfAbsent("b", 2);
        map.compute("a", (k, v) -> v == null ? 0 : v + 1);
    }

    // -------------------------------------------------------------------------
    public static void main(String[] args) throws Exception {
        System.out.println("=== 1. start() vs run() ===");
        WorkerThread w = new WorkerThread();
        System.out.println("Before start: " + w.getState()); // NEW
        w.start();   // новый поток -> RUNNABLE
        w.join();
        System.out.println("After join: " + w.getState()); // TERMINATED

        System.out.println("\n=== 2. Race condition (unsafe counter) ===");
        UnsafeCounter unsafe = new UnsafeCounter();
        Thread t1 = new Thread(() -> { for (int i = 0; i < 10_000; i++) unsafe.increment(); });
        Thread t2 = new Thread(() -> { for (int i = 0; i < 10_000; i++) unsafe.increment(); });
        t1.start(); t2.start(); t1.join(); t2.join();
        System.out.println("Unsafe count (expect 20000): " + unsafe.get());

        System.out.println("\n=== 3. synchronized ===");
        SyncCounter sync = new SyncCounter();
        Thread t3 = new Thread(() -> { for (int i = 0; i < 10_000; i++) sync.increment(); });
        Thread t4 = new Thread(() -> { for (int i = 0; i < 10_000; i++) sync.increment(); });
        t3.start(); t4.start(); t3.join(); t4.join();
        System.out.println("Sync count: " + sync.get());

        System.out.println("\n=== 4. AtomicInteger ===");
        AtomicCounter atomic = new AtomicCounter();
        Thread t5 = new Thread(() -> { for (int i = 0; i < 10_000; i++) atomic.increment(); });
        Thread t6 = new Thread(() -> { for (int i = 0; i < 10_000; i++) atomic.increment(); });
        t5.start(); t6.start(); t5.join(); t6.join();
        System.out.println("Atomic count: " + atomic.get());

        System.out.println("\n=== 5. Callable + Future ===");
        ExecutorService exec = Executors.newSingleThreadExecutor();
        Future<Integer> future = exec.submit(ThreadVsProcess::callableTask);
        System.out.println("Callable result: " + future.get());
        exec.shutdown();

        System.out.println("\n=== 6. CompletableFuture ===");
        completableFutureDemo();

        System.out.println("\n=== 7. ConcurrentHashMap ===");
        concurrentMapDemo();
        System.out.println("Done.");
    }
}
