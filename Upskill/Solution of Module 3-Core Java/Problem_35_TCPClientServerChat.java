import java.io.*;
import java.net.*;

public class Problem_35_TCPClientServerChat {
    public static void main(String[] args) {
        System.out.println("TCP Client-Server chat structure.");
        System.out.println("To execute, compile this file and launch Server and Client sockets.");
        
        // Mock Server thread class representation
        class ServerMock extends Thread {
            public void run() {
                try (ServerSocket serverSocket = new ServerSocket(5000)) {
                    System.out.println("Server listening on port 5000...");
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client connected!");
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    out.println("Hello from Server!");
                    System.out.println("Client says: " + in.readLine());
                } catch (IOException e) {
                    System.out.println("Server exception: " + e.getMessage());
                }
            }
        }
    }
}