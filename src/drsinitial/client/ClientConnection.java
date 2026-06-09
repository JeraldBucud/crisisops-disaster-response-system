package drsinitial.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Handles communication between the JavaFX client and the
 * DRS-Enhanced multi-threaded server.
 *
 * This class does not connect directly to MySQL. It sends
 * ClientRequest objects to the backend server and receives
 * ClientResponse objects from the backend server.
 *
 * @author Jerald Christopher Bucud
 * @studentId 12301099
 * @course COIT20258 Software Engineering
 */
public class ClientConnection {

    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 5000;

    private String serverHost;
    private int serverPort;

    /**
     * Creates a client connection using the default server host and port.
     */
    public ClientConnection() {
        this.serverHost = DEFAULT_HOST;
        this.serverPort = DEFAULT_PORT;
    }

    /**
     * Creates a client connection using a specific server host and port.
     *
     * @param serverHost server host
     * @param serverPort server port
     */
    public ClientConnection(String serverHost, int serverPort) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }

    /**
     * Sends a request to the backend server and returns the server response.
     *
     * @param request client request
     * @return server response
     */
    public ClientResponse sendRequest(ClientRequest request) {
        if (request == null) {
            return ClientResponse.failure("Request cannot be empty.");
        }

        try (
                Socket socket = new Socket(this.serverHost, this.serverPort);
                ObjectOutputStream output =
                        new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream input =
                        new ObjectInputStream(socket.getInputStream())
        ) {
            output.writeObject(request);
            output.flush();

            Object responseObject = input.readObject();

            if (responseObject instanceof ClientResponse) {
                return (ClientResponse) responseObject;
            }

            return ClientResponse.failure(
                    "Invalid response received from backend server.");

        } catch (Exception exception) {
            return ClientResponse.failure(
                    "Backend connection unavailable. "
                    + "Please confirm that the DRS-Enhanced server is running.");
        }
    }

    public String getServerHost() {
        return this.serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public int getServerPort() {
        return this.serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    @Override
    public String toString() {
        return "ClientConnection{"
                + "serverHost='" + this.serverHost + '\''
                + ", serverPort=" + this.serverPort
                + '}';
    }
}