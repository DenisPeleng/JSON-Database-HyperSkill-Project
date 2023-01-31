package client.CommandRequest;

import client.ServerConnection;

public class GetCommandRequest extends Request {
    public GetCommandRequest(ServerConnection serverConnection, int number) {
        super(serverConnection);
        String requestBody = String.format("get %d", number);
        setRequestBody(requestBody);
    }
}
