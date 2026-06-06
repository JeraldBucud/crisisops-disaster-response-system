package drsinitial.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a response returned from the server to the JavaFX client.
 *
 * This class stores the success status, message, and returned
 * records from future server communication.
 *
 * @author Jerald Christopher Bucud
 * @studentId 12301099
 * @course COIT20258 Software Engineering
 */
public class ClientResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean success;
    private String message;
    private List<String> records;

    /**
     * Creates an empty client response.
     */
    public ClientResponse() {
        this.records = new ArrayList<>();
    }

    /**
     * Creates a client response with success status and message.
     *
     * @param success true if the request was successful
     * @param message response message
     */
    public ClientResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.records = new ArrayList<>();
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

    public List<String> getRecords() {
        return this.records;
    }

    public void setRecords(List<String> records) {
        this.records = records;
    }

    /**
     * Adds a text record to the response.
     *
     * @param record response record
     */
    public void addRecord(String record) {
        this.records.add(record);
    }

    @Override
    public String toString() {
        return "ClientResponse{"
                + "success=" + success
                + ", message='" + message + '\''
                + ", records=" + records
                + '}';
    }
}