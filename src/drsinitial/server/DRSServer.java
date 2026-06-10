package drsinitial.server;

import drsinitial.database.DatabaseInitializer;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Multi-threaded socket server for DRS-Enhanced.
 */
public class DRSServer {

    public static final int SERVER_PORT = 5000;

    public static void main(String[] args) {
        DatabaseInitializer.initializeDatabase();

        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("DRS-Enhanced multi-threaded server started on port " + SERVER_PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());
                ClientHandler handler = new ClientHandler(clientSocket);
                Thread clientThread = new Thread(handler);
                clientThread.start();
            }

        } catch (IOException exception) {
            System.err.println("Server error: " + exception.getMessage());
        }
    }
}
