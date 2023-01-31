package client.CommandRequest;

import client.ServerConnection;

public class DeleteCommandRequest extends Request {

    public DeleteCommandRequest(ServerConnection serverConnection, int number) {
        super(serverConnection);
        String requestBody = String.format("delete %d", number);
        setRequestBody(requestBody);
    }

}
