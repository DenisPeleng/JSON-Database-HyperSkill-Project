package client.CommandRequest;

import client.ServerConnection;

public class SetCommandRequest extends Request {
    public SetCommandRequest(ServerConnection serverConnection, int numberCell, String text) {
        super(serverConnection);
        String requestBody = String.format("set %d %s", numberCell, text);
        setRequestBody(requestBody);
    }
}
