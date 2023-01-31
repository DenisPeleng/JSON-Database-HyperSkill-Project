package client.CommandRequest;

import client.ServerConnection;

public class ExitCommandRequest extends Request {
    public ExitCommandRequest(ServerConnection serverConnection) {
        super(serverConnection);
        super.setRequestBody("exit");
    }
}
