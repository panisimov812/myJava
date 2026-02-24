package tasks;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.*;

// ======================= 1–5: Коллекции и строки =======================
class Tasks1to5 {

    public static List<String> findDuplicates(List<String> list) {
        Map<String, Long> counts = list.stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        return counts.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static Map<Character, Long> charFrequency(String text) {
        return text.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    }

    public static String reverseString(String text) {
        char[] chars = text.toCharArray();
        int n = chars.length;
        for (int i = 0; i < n / 2; i++) {
            char temp = chars[i];
            chars[i] = chars[n - i - 1];
            chars[n - i - 1] = temp;
        }
        return new String(chars);
    }

    public static List<Integer> mergeFilterSort(List<Integer> list1, List<Integer> list2) {
        return Stream.concat(list1.stream(), list2.stream())
                .distinct()
                .filter(n -> n > 10)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public static boolean isPalindrome(String text) {
        String cleaned = text.toLowerCase().replaceAll("[^a-z0-9]", "");
        return new StringBuilder(cleaned).reverse().toString().equals(cleaned);
    }
}

// ======================= 6–10: OOP / Builder / Strategy / SOLID =======================
class TestCase {
    private final String name;
    private final List<String> steps;
    private final String expectedResult;
    private final int priority;

    private TestCase(Builder builder) {
        this.name = builder.name;
        this.steps = builder.steps;
        this.expectedResult = builder.expectedResult;
        this.priority = builder.priority;
    }

    @Override
    public String toString() {
        return "TestCase{" +
                "name='" + name + '\'' +
                ", steps=" + steps +
                ", expectedResult='" + expectedResult + '\'' +
                ", priority=" + priority +
                '}';
    }

    public static class Builder {
        private String name;
        private List<String> steps = new ArrayList<>();
        private String expectedResult;
        private int priority;

        public Builder name(String name) { this.name = name; return this; }
        public Builder steps(List<String> steps) { this.steps = steps; return this; }
        public Builder expectedResult(String res) { this.expectedResult = res; return this; }
        public Builder priority(int p) { this.priority = p; return this; }
        public TestCase build() { return new TestCase(this); }
    }
}

interface Role {
    void login();
}

class UserRole implements Role {
    public void login() { System.out.println("User login"); }
}
class AdminRole implements Role {
    public void login() { System.out.println("Admin login"); }
}
class GuestRole implements Role {
    public void login() { System.out.println("Guest login"); }
}

class User {
    private int id;
    private String name;
    private String email;

    public User(int id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, email);
    }
}

final class BankTransaction {
    private final String id;
    private final double amount;

    public BankTransaction(String id, double amount){
        this.id = id;
        this.amount = amount;
    }
    public String getId(){ return id; }
    public double getAmount(){ return amount; }
}

interface PaymentMethod {
    void pay(double amount);
}

class CreditCardPayment implements PaymentMethod {
    public void pay(double amount){ System.out.println("Paid " + amount + " by CreditCard"); }
}
class PayPalPayment implements PaymentMethod {
    public void pay(double amount){ System.out.println("Paid " + amount + " by PayPal"); }
}

class PaymentProcessor {
    public void processPayment(PaymentMethod method, double amount){
        method.pay(amount);
    }
}

// ======================= 11–15: Concurrency / Threading =======================
class Tasks11to15 {

    private static final AtomicInteger counter = new AtomicInteger(0);

    // 11. Producer-Consumer
    public static void producerConsumerExample() throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
        Runnable producer = () -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    queue.put(i);
                    System.out.println("Produced: " + i);
                }
            } catch (InterruptedException ignored) {}
        };
        Runnable consumer = () -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    System.out.println("Consumed: " + queue.take());
                }
            } catch (InterruptedException ignored) {}
        };
        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);
        t1.start(); t2.start();
        t1.join(); t2.join();
    }

    // 12. Race condition fix
    public static void incrementCounter() throws InterruptedException {
        Runnable r = () -> { for(int i=0;i<1000;i++) counter.incrementAndGet(); };
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start(); t2.start();
        t1.join(); t2.join();
        System.out.println("Counter = " + counter.get()); // должно быть 2000
    }

    // 13. CompletableFuture parallel
    public static void completableFutureExample() throws Exception {
        List<CompletableFuture<String>> futures = new ArrayList<>();
        for(int i=0;i<5;i++){
            int index = i;
            futures.add(CompletableFuture.supplyAsync(() -> "Result " + index));
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        futures.forEach(f -> System.out.println(f.join()));
    }

    // 14. CountDownLatch
    public static void countDownLatchExample() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Runnable worker = () -> {
            try {
                latch.await();
                System.out.println(Thread.currentThread().getName() + " started after latch");
            } catch (InterruptedException ignored) {}
        };
        for(int i=0;i<3;i++) new Thread(worker).start();
        Thread.sleep(100); // имитация инициализации
        latch.countDown();
    }

    // 15. Thread-safe Singleton
    static class Singleton {
        private static volatile Singleton instance;
        private Singleton(){}
        public static Singleton getInstance(){
            if(instance==null){
                synchronized(Singleton.class){
                    if(instance==null) instance = new Singleton();
                }
            }
            return instance;
        }
    }
}

// ======================= 16–20: Backend / API / DB =======================
class Tasks16to20 {

    // 16. JSON parsing simulation (list of users)
    static class JsonUser { int age; String name; JsonUser(int age,String name){this.age=age;this.name=name;} }
    public static List<JsonUser> filterAdultUsers(List<JsonUser> users){
        return users.stream().filter(u -> u.age >= 18).collect(Collectors.toList());
    }

    // 17. Check DB duplicates simulation
    static class DBUser { int id; String email; DBUser(int id, String email){this.id=id;this.email=email;} }
    public static List<String> findDuplicateEmails(List<DBUser> users){
        Map<String, Long> counts = users.stream()
                .collect(Collectors.groupingBy(u -> u.email, Collectors.counting()));
        return counts.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    // 18. Async API simulation
    public static void asyncApiExample() {
        List<CompletableFuture<String>> futures = new ArrayList<>();
        for(int i=0;i<5;i++){
            int idx=i;
            futures.add(CompletableFuture.supplyAsync(() -> "Response " + idx));
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        futures.forEach(f -> System.out.println(f.join()));
    }

    // 19. Kafka simulation
    public static void kafkaSimulation() {
        Queue<String> topic = new LinkedList<>();
        topic.add("msg1"); topic.add("msg2");
        while(!topic.isEmpty()) System.out.println("Consumed: " + topic.poll());
    }

    // 20. Idempotency simulation
    private static final Set<String> processedIds = new HashSet<>();

    public static void postTransfer(String id){
        if(processedIds.contains(id)){
            System.out.println("Already processed: " + id);
        } else {
            processedIds.add(id);
            System.out.println("Processed: " + id);
        }
    }
}

// ======================= Main =======================
public class SeniorAutomationQATasks {
    public static void main(String[] args) throws Exception {

        System.out.println("=== 1. Duplicates ===");
        System.out.println(Tasks1to5.findDuplicates(Arrays.asList("Alex", "Bob", "Alex", "John")));

        System.out.println("\n=== 2. Char Frequency ===");
        System.out.println(Tasks1to5.charFrequency("aaabbc"));

        System.out.println("\n=== 3. Reverse String ===");
        System.out.println(Tasks1to5.reverseString("automation"));

        System.out.println("\n=== 4. Merge Filter Sort ===");
        System.out.println(Tasks1to5.mergeFilterSort(Arrays.asList(5,20,15), Arrays.asList(7,20,30)));

        System.out.println("\n=== 5. Palindrome ===");
        System.out.println(Tasks1to5.isPalindrome("Madam"));

        System.out.println("\n=== 6. Builder TestCase ===");
        TestCase testCase = new TestCase.Builder()
                .name("Login Test")
                .steps(Arrays.asList("Open page","Enter credentials","Click login"))
                .expectedResult("User logged in")
                .priority(1)
                .build();
        System.out.println(testCase);

        System.out.println("\n=== 7. Strategy Roles ===");
        Role admin = new AdminRole(); admin.login();
        Role user = new UserRole(); user.login();

        System.out.println("\n=== 8. Equals & hashCode ===");
        User u1 = new User(1,"Alex","a@b.com");
        User u2 = new User(1,"Alex","a@b.com");
        System.out.println(u1.equals(u2));

        System.out.println("\n=== 9. Immutable Object ===");
        BankTransaction tx = new BankTransaction("tx1",100);
        System.out.println(tx.getId() + " : " + tx.getAmount());

        System.out.println("\n=== 10. Payment Processor ===");
        PaymentProcessor processor = new PaymentProcessor();
        processor.processPayment(new CreditCardPayment(), 200);

        System.out.println("\n=== 11. Producer-Consumer ===");
        Tasks11to15.producerConsumerExample();

        System.out.println("\n=== 12. Race Condition ===");
        Tasks11to15.incrementCounter();

        System.out.println("\n=== 13. CompletableFuture Parallel ===");
        Tasks11to15.completableFutureExample();

        System.out.println("\n=== 14. CountDownLatch ===");
        Tasks11to15.countDownLatchExample();
        Thread.sleep(500); // ждать завершения latch примера

        System.out.println("\n=== 15. Singleton ===");
        System.out.println(Tasks11to15.Singleton.getInstance() == Tasks11to15.Singleton.getInstance());

        System.out.println("\n=== 16. JSON Filtering ===");
        List<Tasks16to20.JsonUser> users = Arrays.asList(new Tasks16to20.JsonUser(17,"A"), new Tasks16to20.JsonUser(20,"B"));
        System.out.println(Tasks16to20.filterAdultUsers(users));

        System.out.println("\n=== 17. Find duplicate emails ===");
        List<Tasks16to20.DBUser> dbUsers = Arrays.asList(
                new Tasks16to20.DBUser(1,"a@b.com"),
                new Tasks16to20.DBUser(2,"c@d.com"),
                new Tasks16to20.DBUser(3,"a@b.com")
        );
        System.out.println(Tasks16to20.findDuplicateEmails(dbUsers));

        System.out.println("\n=== 18. Async API Simulation ===");
        Tasks16to20.asyncApiExample();

        System.out.println("\n=== 19. Kafka Simulation ===");
        Tasks16to20.kafkaSimulation();

        System.out.println("\n=== 20. Idempotency Simulation ===");
        Tasks16to20.postTransfer("id1");
        Tasks16to20.postTransfer("id1"); // should not process again
    }
}
