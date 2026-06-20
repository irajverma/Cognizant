public class Problem_40_VirtualThreads {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Launching 10,000 virtual threads (Java 21+)...");
        
        long start = System.currentTimeMillis();
        Thread[] threads = new Thread[10000];
        
        for (int i = 0; i < threads.length; i++) {
            final int index = i;
            // Start virtual thread
            threads[i] = Thread.startVirtualThread(() -> {
                if (index % 2000 == 0) {
                    System.out.println("Virtual Thread " + index + " running on: " + Thread.currentThread());
                }
            });
        }
        
        for (Thread t : threads) {
            t.join();
        }
        long duration = System.currentTimeMillis() - start;
        System.out.println("Completed virtual threads run in " + duration + " ms.");
    }
}