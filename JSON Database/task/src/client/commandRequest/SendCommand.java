package client.commandRequest;

import client.ServerConnection;
import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SendCommand {
    DataOutputStream outputStream;
    DataInputStream inputStream;
    Request request;

    public SendCommand(ServerConnection serverConnection, Request request) {
        outputStream = serverConnection.getOutputStream();
        inputStream = serverConnection.getInputStream();
        this.request = request;
    }

    public void execute() {
        try {
            String requestBody = new Gson().toJson(request);
            outputStream.writeUTF(requestBody);
            System.out.printf("Sent: %s\n", requestBody);
            String answerFromServer = inputStream.readUTF();
            System.out.printf("Received: %s\n", answerFromServer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
