package drsinitial.client;

/**
 * Provides the frontend-side connection point for future communication
 * between the JavaFX client and the DRS-Enhanced multi-threaded server.
 *
 * This class currently returns placeholder responses so the frontend
 * can compile and run before backend integration.
 *
 * @author Jerald Christopher Bucud
 * @studentId 12301099
 * @course COIT20258 Software Engineering
 */
public class ClientConnection {

    private String serverHost;
    private int serverPort;

    /**
     * Creates a client connection using default local server settings.
     */
    public ClientConnection() {
        this.serverHost = "localhost";
        this.serverPort = 5000;
    }

    /**
     * Creates a client connection with custom server settings.
     *
     * @param serverHost server host name or IP address
     * @param serverPort server port number
     */
    public ClientConnection(String serverHost, int serverPort) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
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

    /**
     * Sends a request to the server.
     *
     * This is a placeholder method. The real socket connection will
     * be added after the multi-threaded server is available.
     *
     * @param request frontend request
     * @return placeholder response
     */
    public ClientResponse sendRequest(ClientRequest request) {
        if (request == null || request.getRequestType() == null) {
            return new ClientResponse(false, "Invalid client request.");
        }

        return new ClientResponse(
                true,
                "Placeholder response for request: "
                        + request.getRequestType()
        );
    }

    /**
     * Checks whether the server connection is available.
     *
     * This currently returns false because backend connection
     * is not implemented yet.
     *
     * @return false until backend integration is complete
     */
    public boolean isServerAvailable() {
        return false;
    }
}