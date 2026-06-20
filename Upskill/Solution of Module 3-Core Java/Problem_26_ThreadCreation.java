class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Thread 1 (Extends Thread) running... Step " + i);
            try { Thread.sleep(200); } catch (InterruptedException e) {}
        }
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Thread 2 (Implements Runnable) running... Step " + i);
            try { Thread.sleep(200); } catch (InterruptedException e) {}
        }
    }
}

public class Problem_26_ThreadCreation {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        Thread t2 = new Thread(new MyRunnable());
        
        t1.start();
        t2.start();
    }
}