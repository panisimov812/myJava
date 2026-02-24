package interview;

import java.util.*;

/**
 * Java Collections Framework (Core) — примеры для Senior Java / SDET.
 * List, Set, Map, Queue — без многопоточности.
 */
public class CollectionsFrameworkExamples {

    static void arrayListDemo() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        System.out.println(list.get(0));        // A
        list.set(1, "B2");
        list.remove("A");                       // по значению (equals)
        System.out.println(list.size() + ", " + list.isEmpty());
        list.clear();
    }

    static void linkedListDemo() {
        LinkedList<String> list = new LinkedList<>();
        list.addFirst("A");
        list.addLast("B");
        list.addLast("C");
        System.out.println(list.getFirst() + ", " + list.getLast());
        list.removeFirst();
        list.removeLast();
    }

    static void hashSetDemo() {
        Set<String> set = new HashSet<>();
        set.add("A");
        set.add("A");                           // дубликат не добавится
        set.add("B");
        System.out.println(set.size());         // 2
        set.remove("A");
        System.out.println(set.contains("B"));  // true
    }

    static void treeSetDemo() {
        Set<Integer> set = new TreeSet<>();
        set.add(3);
        set.add(1);
        set.add(2);
        for (Integer i : set) {
            System.out.print(i + " ");          // 1 2 3 (natural order)
        }
        System.out.println();
    }

    static void hashMapDemo() {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        System.out.println(map.get("A"));      // 1
        map.remove("A");
        System.out.println(map.containsKey("B"));
        System.out.println(map.keySet() + ", " + map.values());
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }
    }

    static void linkedHashMapDemo() {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("C", 3);
        map.put("A", 1);
        map.put("B", 2);
        System.out.println(map.keySet());      // [C, A, B] — порядок вставки
    }

    static void treeMapDemo() {
        Map<String, Integer> map = new TreeMap<>();
        map.put("C", 3);
        map.put("A", 1);
        map.put("B", 2);
        System.out.println(map.keySet());      // [A, B, C] — сортировка по ключу
    }

    static void queueDemo() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        q.offer(2);
        System.out.println(q.peek());          // 1, не удаляя
        System.out.println(q.poll());          // 1
        System.out.println(q.poll());          // 2
        System.out.println(q.poll());          // null (пусто)
    }

    static void priorityQueueDemo() {
        Queue<Integer> pq = new PriorityQueue<>();
        pq.add(5);
        pq.add(1);
        pq.add(3);
        System.out.println(pq.poll());         // 1
        System.out.println(pq.poll());         // 3
        System.out.println(pq.poll());         // 5
    }

    public static void main(String[] args) {
        System.out.println("=== ArrayList ===");
        arrayListDemo();
        System.out.println("=== LinkedList ===");
        linkedListDemo();
        System.out.println("=== HashSet ===");
        hashSetDemo();
        System.out.println("=== TreeSet ===");
        treeSetDemo();
        System.out.println("=== HashMap ===");
        hashMapDemo();
        System.out.println("=== LinkedHashMap ===");
        linkedHashMapDemo();
        System.out.println("=== TreeMap ===");
        treeMapDemo();
        System.out.println("=== Queue (FIFO) ===");
        queueDemo();
        System.out.println("=== PriorityQueue ===");
        priorityQueueDemo();
    }
}
