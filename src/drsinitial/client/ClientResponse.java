package drsinitial.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents a response returned from the DRS-Enhanced
 * multi-threaded server to the JavaFX client.
 *
 * This class stores the result status, response message,
 * one optional data map, and one optional list of data maps.
 *
 * @author Jerald Christopher Bucud
 * @studentId 12301099
 * @course COIT20258 Software Engineering
 */
public class ClientResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean success;
    private String message;
    private Map<String, String> data;
    private List<Map<String, String>> dataList;

    /**
     * Creates an empty client response.
     */
    public ClientResponse() {
        this.success = false;
        this.message = "";
        this.dataList = new ArrayList<>();
    }

    /**
     * Creates a client response with success status and message.
     *
     * @param success response status
     * @param message response message
     */
    public ClientResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.dataList = new ArrayList<>();
    }

    /**
     * Creates a client response with one data record.
     *
     * @param success response status
     * @param message response message
     * @param data response data
     */
    public ClientResponse(boolean success, String message,
            Map<String, String> data) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.dataList = new ArrayList<>();
    }

    /**
     * Creates a client response with multiple data records.
     *
     * @param success response status
     * @param message response message
     * @param dataList response data list
     */
    public ClientResponse(boolean success, String message,
            List<Map<String, String>> dataList) {
        this.success = success;
        this.message = message;
        this.dataList = dataList;
    }

    /**
     * Creates a failed response.
     *
     * @param message failure message
     * @return failed client response
     */
    public static ClientResponse failure(String message) {
        return new ClientResponse(false, message);
    }

    /**
     * Creates a successful response.
     *
     * @param message success message
     * @return successful client response
     */
    public static ClientResponse success(String message) {
        return new ClientResponse(true, message);
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getData() {
        return this.data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public List<Map<String, String>> getDataList() {
        return this.dataList;
    }

    public void setDataList(List<Map<String, String>> dataList) {
        this.dataList = dataList;
    }

    /**
     * Checks whether the response contains one data record.
     *
     * @return true if data exists
     */
    public boolean hasData() {
        return this.data != null && !this.data.isEmpty();
    }

    /**
     * Checks whether the response contains a list of data records.
     *
     * @return true if data list exists
     */
    public boolean hasDataList() {
        return this.dataList != null && !this.dataList.isEmpty();
    }

    /**
     * Gets a value from the response data.
     *
     * @param key data key
     * @return matching value, or empty string if unavailable
     */
    public String getDataValue(String key) {
        if (this.data == null || !this.data.containsKey(key)) {
            return "";
        }

        return this.data.get(key);
    }

    @Override
    public String toString() {
        return "ClientResponse{"
                + "success=" + this.success
                + ", message='" + this.message + '\''
                + ", data=" + this.data
                + ", dataList=" + this.dataList
                + '}';
    }
}