import java.util.concurrent.*;

public class Problem_41_ExecutorServiceCallable {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        Callable<Integer> task = () -> {
            int sum = 0;
            for (int i = 1; i <= 100; i++) {
                sum += i;
            }
            return sum;
        };
        
        Future<Integer> future = executor.submit(task);
        
        try {
            System.out.println("Waiting for callable task to complete...");
            Integer result = future.get(); // Collect result
            System.out.println("Result received from Future: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}