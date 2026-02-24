package tasks;

import java.util.*;

/**
 * Запуск только задач 11–20 (Concurrency + Backend/API/DB).
 * Классы Tasks11to15 и Tasks16to20 находятся в SeniorAutomationQATasks.java.
 */
public class SeniorAutomationQATasksPart2 {
    public static void main(String[] args) throws Exception {

        System.out.println("=== 11. Producer-Consumer ===");
        Tasks11to15.producerConsumerExample();

        System.out.println("\n=== 12. Race Condition ===");
        Tasks11to15.incrementCounter();

        System.out.println("\n=== 13. CompletableFuture Parallel ===");
        Tasks11to15.completableFutureExample();

        System.out.println("\n=== 14. CountDownLatch ===");
        Tasks11to15.countDownLatchExample();
        Thread.sleep(500); // подождать завершения latch

        System.out.println("\n=== 15. Singleton ===");
        System.out.println(Tasks11to15.Singleton.getInstance() == Tasks11to15.Singleton.getInstance());

        System.out.println("\n=== 16. JSON Filtering ===");
        List<Tasks16to20.JsonUser> users = Arrays.asList(
                new Tasks16to20.JsonUser(17,"A"), new Tasks16to20.JsonUser(20,"B"));
        System.out.println(Tasks16to20.filterAdultUsers(users));

        System.out.println("\n=== 17. Find duplicate emails ===");
        List<Tasks16to20.DBUser> dbUsers = Arrays.asList(
                new Tasks16to20.DBUser(1,"a@b.com"),
                new Tasks16to20.DBUser(2,"c@d.com"),
                new Tasks16to20.DBUser(3,"a@b.com"));
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
