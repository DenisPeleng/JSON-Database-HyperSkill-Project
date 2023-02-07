package server.requests;

import com.google.gson.JsonElement;

public class Request {
    String type;
    JsonElement key;
    JsonElement value;

    public String getType() {
        return type;
    }

    public JsonElement getKey() {
        return key;
    }

    public JsonElement getValue() {
        return value;
    }

    public Request(String typeRequest, JsonElement key, JsonElement value) {
        this.type = typeRequest;
        this.key = key;
        this.value = value;
    }

    public Request(String typeRequest, JsonElement key) {
        this.type = typeRequest;
        this.key = key;
    }

    public Request(String typeRequest) {
        this.type = typeRequest;
    }
}
