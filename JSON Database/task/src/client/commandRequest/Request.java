package client.commandRequest;

public class Request {

    private final String type;
    private String key;
    private String value;

    public Request(String typeRequest, String key, String value) {
        this.type = typeRequest;
        this.key = key;
        this.value = value;
    }

    public Request(String typeRequest, String key) {
        this.type = typeRequest;
        this.key = key;
    }

    public Request(String typeRequest) {
        this.type = typeRequest;
    }
 }
