package client;

import client.CommandRequest.*;

public class Client {
    public static void startClient(ArgsCommandLine consoleArgs) {
        try (ServerConnection serverConnection = new ServerConnection()) {
            serverConnection.createConnection();
            String command = consoleArgs.getRequestType();
            Command commandRequest = new ExitCommandRequest(serverConnection);
            switch (command) {
                case "get": {
                    commandRequest = new GetCommandRequest(serverConnection, consoleArgs.getIndexOfCell());
                    break;
                }
                case "set": {
                    commandRequest = new SetCommandRequest(serverConnection, consoleArgs.getIndexOfCell(), consoleArgs.getText());
                    break;
                }
                case "delete": {
                    commandRequest = new DeleteCommandRequest(serverConnection, consoleArgs.getIndexOfCell());
                    break;
                }
                case "exit": {
                    commandRequest = new ExitCommandRequest(serverConnection);
                    break;
                }
            }
            commandRequest.execute();
        }
    }
}
