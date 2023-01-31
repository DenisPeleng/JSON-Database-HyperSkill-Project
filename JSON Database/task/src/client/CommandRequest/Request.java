package client.CommandRequest;

import client.ServerConnection;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

abstract class Request implements Command {
    DataOutputStream outputStream;
    DataInputStream inputStream;
    String requestBody;

    Request(ServerConnection serverConnection) {
        outputStream = serverConnection.getOutputStream();
        inputStream = serverConnection.getInputStream();
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    @Override
    public void execute() {
        try {
            outputStream.writeUTF(requestBody);
            System.out.printf("Sent: %s\n", requestBody);
            String answerFromServer = inputStream.readUTF();
            System.out.printf("Received: %s\n", answerFromServer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
