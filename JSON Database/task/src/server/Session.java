package server;

import com.google.gson.Gson;
import server.commands.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

class Session extends Thread {
    private final Socket socket;

    public Session(Socket socketForClient) {
        this.socket = socketForClient;
    }

    public void run() {
        try (
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {
            String requestFromClient = input.readUTF();
            System.out.printf("Received: %s\n", requestFromClient);
            Request receivedRequest = new Gson().fromJson(requestFromClient, Request.class);
            Response resultResponse = processCommand(receivedRequest);
            String resultAnswer = new Gson().toJson(resultResponse);
            System.out.printf("Sent: %s\n", resultAnswer);
            output.writeUTF(resultAnswer);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Response processCommand(Request request) {
        String requestType = request.getType();
        Command command = new ExitCommand();
        switch (requestType) {
            case "exit": {
                command = new ExitCommand();
                break;
            }
            case "get": {
                command = new GetCommand(request);
                break;
            }
            case "delete": {
                command = new DeleteCommand(request);
                break;
            }
            case "set": {
                command = new SetCommand(request);
                break;
            }
        }
        return command.execute();
    }
}