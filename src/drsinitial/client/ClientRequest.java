package drsinitial.client;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a request sent from the JavaFX client to the server.
 *
 * This class stores the request type and request data needed
 * for future client-server communication in DRS-Enhanced.
 *
 * @author Jerald Christopher Bucud
 * @studentId 12301099
 * @course COIT20258 Software Engineering
 */
public class ClientRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String LOGIN = "LOGIN";
    public static final String REGISTER_PUBLIC_USER = "REGISTER_PUBLIC_USER";

    public static final String GET_PUBLIC_ALERTS = "GET_PUBLIC_ALERTS";
    public static final String CREATE_PUBLIC_ALERT = "CREATE_PUBLIC_ALERT";
    public static final String PUBLISH_PUBLIC_ALERT = "PUBLISH_PUBLIC_ALERT";

    public static final String GET_EVACUATION_SHELTERS =
            "GET_EVACUATION_SHELTERS";
    public static final String ADD_EVACUATION_SHELTER =
            "ADD_EVACUATION_SHELTER";
    public static final String UPDATE_EVACUATION_SHELTER =
            "UPDATE_EVACUATION_SHELTER";

    public static final String GET_USERS = "GET_USERS";
    public static final String ADD_USER = "ADD_USER";
    public static final String UPDATE_USER = "UPDATE_USER";

    private String requestType;
    private Map<String, String> data;

    /**
     * Creates an empty client request.
     */
    public ClientRequest() {
        this.data = new HashMap<>();
    }

    /**
     * Creates a client request with a request type.
     *
     * @param requestType request type
     */
    public ClientRequest(String requestType) {
        this.requestType = requestType;
        this.data = new HashMap<>();
    }

    public String getRequestType() {
        return this.requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public Map<String, String> getData() {
        return this.data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    /**
     * Adds a key-value pair to the request data.
     *
     * @param key data key
     * @param value data value
     */
    public void addData(String key, String value) {
        this.data.put(key, value);
    }

    /**
     * Gets a value from the request data.
     *
     * @param key data key
     * @return matching value
     */
    public String getDataValue(String key) {
        return this.data.get(key);
    }

    @Override
    public String toString() {
        return "ClientRequest{"
                + "requestType='" + requestType + '\''
                + ", data=" + data
                + '}';
    }
}