package interview.potoki;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 8️⃣ Concurrent Collections — примеры для Senior Automation / SDET.
 * Соответствует разделу базы знаний "Concurrent Collections (Java)".
 */
public class ConcurrentCollectionsExamples {

    // ==================== 1. Неатомарная операция get + put (небезопасно) ====================
    static void unsafeGetPut(Map<String, Integer> map) {
        Integer value = map.get("A");
        map.put("A", value == null ? 1 : value + 1);
        // Между get и put другой поток может изменить "A" -> lost update.
        // Плюс HashMap при одновременном put может повредить структуру.
    }

    // ==================== 2. ConcurrentHashMap — атомарные операции ====================
    static void concurrentHashMapDemo() {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("A", 1);

        // Атомарно: прочитать и обновить под одной блокировкой бакета
        map.compute("A", (k, v) -> v + 1);           // A -> 2
        map.merge("B", 1, Integer::sum);             // B отсутствовал -> 1
        map.merge("B", 1, Integer::sum);             // B -> 2

        map.putIfAbsent("C", 10);                   // C -> 10
        map.putIfAbsent("C", 99);                   // не меняется, C уже есть

        map.replace("A", 2, 3);                     // A было 2 -> станет 3

        System.out.println("ConcurrentHashMap: " + map);
    }

    // ==================== 3. CopyOnWriteArrayList ====================
    static void copyOnWriteArrayListDemo() {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("A");
        list.add("B");
        for (String s : list) {
            System.out.println(s);  // итератор по снимку, без CME
        }
        list.add("C");  // новая копия массива внутри
    }

    // ==================== 4. BlockingQueue — Producer-Consumer ====================
    static void blockingQueueDemo() throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(2);

        Thread producer = new Thread(() -> {
            try {
                queue.put("task1");
                queue.put("task2");
                queue.put("task3");  // блокируется, пока consumer не возьмёт
            } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        });

        Thread consumer = new Thread(() -> {
            try {
                System.out.println("Consumed: " + queue.take());  // блокируется, если пусто
                System.out.println("Consumed: " + queue.take());
            } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        });

        consumer.start();
        producer.start();
        producer.join();
        consumer.join();
    }

    // ==================== Демонстрация: get+put vs compute ====================
    static void demonstrateLostUpdate() throws InterruptedException {
        final Map<String, Integer> unsafeMap = new ConcurrentHashMap<>();
        unsafeMap.put("count", 0);

        // 10 потоков делают get + put (не атомарно) по 1000 раз
        Runnable unsafeTask = () -> {
            for (int i = 0; i < 1000; i++) {
                Integer v = unsafeMap.get("count");
                unsafeMap.put("count", v + 1);
            }
        };

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) threads[i] = new Thread(unsafeTask);
        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        System.out.println("Unsafe get+put, 10*1000 increments: " + unsafeMap.get("count"));
        // Часто меньше 10000 из-за lost updates.

        // Атомарно через compute
        unsafeMap.put("count", 0);
        Runnable safeTask = () -> {
            for (int i = 0; i < 1000; i++) {
                unsafeMap.compute("count", (k, v) -> v + 1);
            }
        };
        for (int i = 0; i < 10; i++) threads[i] = new Thread(safeTask);
        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();
        System.out.println("Safe compute, 10*1000 increments: " + unsafeMap.get("count"));
        // Всегда 10000.
    }

    // ==================== ArrayBlockingQueue (bounded) ====================
    static void arrayBlockingQueueDemo() throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);
        queue.put(1);
        queue.put(2);
        // queue.put(3);  // блокировался бы до освобождения места
        System.out.println(queue.take());
        System.out.println(queue.take());
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== ConcurrentHashMap (compute, merge, putIfAbsent, replace) ===");
        concurrentHashMapDemo();

        System.out.println("\n=== CopyOnWriteArrayList ===");
        copyOnWriteArrayListDemo();

        System.out.println("\n=== Lost update: get+put vs compute ===");
        demonstrateLostUpdate();

        System.out.println("\n=== BlockingQueue (LinkedBlockingQueue) ===");
        blockingQueueDemo();

        System.out.println("\n=== ArrayBlockingQueue (bounded) ===");
        arrayBlockingQueueDemo();
    }
}
