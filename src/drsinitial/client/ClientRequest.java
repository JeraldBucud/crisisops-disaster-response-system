package drsinitial.client;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a request sent from the JavaFX client to the
 * DRS-Enhanced multi-threaded server.
 *
 * This class stores the request type and request data.
 * Controllers should not communicate directly with the server.
 * Controllers should call BackendClient, which creates ClientRequest
 * objects and sends them through ClientConnection.
 *
 * @author Jerald Christopher Bucud
 * @studentId 12301099
 * @course COIT20258 Software Engineering
 */
public class ClientRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String LOGIN = "LOGIN";
    public static final String REGISTER_PUBLIC_USER = "REGISTER_PUBLIC_USER";

    public static final String GET_USERS = "GET_USERS";
    public static final String ADD_USER = "ADD_USER";
    public static final String UPDATE_USER = "UPDATE_USER";

    public static final String SUBMIT_DISASTER_REPORT =
            "SUBMIT_DISASTER_REPORT";
    public static final String GET_DISASTER_REPORTS =
            "GET_DISASTER_REPORTS";

    public static final String GET_INCIDENTS = "GET_INCIDENTS";
    public static final String REGISTER_INCIDENT = "REGISTER_INCIDENT";
    public static final String ASSESS_INCIDENT_PRIORITY =
            "ASSESS_INCIDENT_PRIORITY";
    public static final String UPDATE_INCIDENT_STATUS =
            "UPDATE_INCIDENT_STATUS";
    public static final String SEARCH_INCIDENTS = "SEARCH_INCIDENTS";

    public static final String DISPATCH_RESPONSE = "DISPATCH_RESPONSE";
    public static final String GET_RESPONSE_LOGS = "GET_RESPONSE_LOGS";

    public static final String GET_EMERGENCY_RESOURCES =
            "GET_EMERGENCY_RESOURCES";
    public static final String GET_RESPONSE_AGENCIES =
            "GET_RESPONSE_AGENCIES";
    public static final String UPDATE_RESOURCE_AVAILABILITY =
            "UPDATE_RESOURCE_AVAILABILITY";

    public static final String GET_EVACUATION_SHELTERS =
            "GET_EVACUATION_SHELTERS";
    public static final String ADD_EVACUATION_SHELTER =
            "ADD_EVACUATION_SHELTER";
    public static final String UPDATE_EVACUATION_SHELTER =
            "UPDATE_EVACUATION_SHELTER";

    public static final String GET_PUBLIC_ALERTS = "GET_PUBLIC_ALERTS";
    public static final String GET_PUBLIC_ALERTS_FOR_PUBLIC_USER =
            "GET_PUBLIC_ALERTS_FOR_PUBLIC_USER";
    public static final String GET_ALL_PUBLIC_ALERTS =
            "GET_ALL_PUBLIC_ALERTS";
    public static final String CREATE_PUBLIC_ALERT = "CREATE_PUBLIC_ALERT";
    public static final String PUBLISH_PUBLIC_ALERT = "PUBLISH_PUBLIC_ALERT";
    public static final String EXPIRE_PUBLIC_ALERT = "EXPIRE_PUBLIC_ALERT";

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

    /**
     * Creates a client request with a request type and request data.
     *
     * @param requestType request type
     * @param data request data
     */
    public ClientRequest(String requestType, Map<String, String> data) {
        this.requestType = requestType;
        this.data = data;
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
                + "requestType='" + this.requestType + '\''
                + ", data=" + this.data
                + '}';
    }
}