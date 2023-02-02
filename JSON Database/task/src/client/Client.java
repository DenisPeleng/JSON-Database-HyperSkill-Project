package client;

import client.commandRequest.*;

public class Client {
    public static void startClient(ArgsCommandLine consoleArgs) {
        try (ServerConnection serverConnection = new ServerConnection()) {
            serverConnection.createConnection();
            String command = consoleArgs.getRequestType();
            Request request = new Request("exit");
            switch (command) {
                case "get":
                case "delete": {
                    request = new Request(command, consoleArgs.getKey());
                    break;
                }
                case "set": {
                    request = new Request(command, consoleArgs.getKey(), consoleArgs.getText());
                    break;
                }
                case "exit": {
                    request = new Request(command);
                    break;
                }
            }
            SendCommand sendCommand = new SendCommand(serverConnection, request);
            sendCommand.execute();
        }
    }
}
