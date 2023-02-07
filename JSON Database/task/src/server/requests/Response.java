package server.requests;

import com.google.gson.JsonElement;

public class Response {
    String response;
    JsonElement reason;
    JsonElement value;

    public Response(String response, JsonElement value) {
        this.response = response;
        if (response.equals("ERROR")) {
            this.reason = value;
        } else {
            this.value = value;
        }

    }

    public Response(String response) {
        this.response = response;
    }

}
