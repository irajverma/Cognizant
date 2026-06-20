public class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromRemoteServer();
    }

    private void loadFromRemoteServer() {
        System.out.println("Loading image '" + filename + "' from remote server... (this takes bandwidth/time)");
        try {
            // Simulate delay
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Image '" + filename + "' loaded successfully.");
    }

    @Override
    public void display() {
        System.out.println("Displaying Real Image: " + filename);
    }
}
