public class ProxyTest {
    public static void main(String[] args) {
        System.out.println("=== Exercise 6: Proxy Pattern ===");

        Image image1 = new ProxyImage("nature_wallpaper.jpg");
        Image image2 = new ProxyImage("family_photo.png");

        // Image 1: First call to display() (should trigger loading from remote server)
        System.out.println("\n--- First display call for Image 1 ---");
        long startTime = System.currentTimeMillis();
        image1.display();
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + " ms");

        // Image 1: Second call to display() (should NOT trigger loading, using cached image)
        System.out.println("\n--- Second display call for Image 1 (Cached) ---");
        startTime = System.currentTimeMillis();
        image1.display();
        endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + " ms");

        // Image 2: First call to display() (should trigger loading)
        System.out.println("\n--- First display call for Image 2 ---");
        startTime = System.currentTimeMillis();
        image2.display();
        endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
    }
}
