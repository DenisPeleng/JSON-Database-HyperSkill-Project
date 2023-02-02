package server;

public class Response {
     String response;
     String reason;
     String value;

    public Response(String response, String value) {
        this.response = response;
        if (response.equals("ERROR")){
            this.reason = value;
        }else {
            this.value = value;
        }

    }

    public Response(String response) {
        this.response = response;
    }

}
