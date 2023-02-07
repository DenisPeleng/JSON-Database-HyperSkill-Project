package client;

import client.argsprocessing.ArgsCommandLine;
import client.commandrequest.*;
import com.google.gson.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Client {
 private static final String FILE_REQUEST_PATH_LOCAL_ENVIRONMENT = System.getProperty("user.dir") + "/src/client/data/";

    public static void startClient(ArgsCommandLine consoleArgs) {
        try (ServerConnection serverConnection = new ServerConnection()) {
            serverConnection.createConnection();
            Request request = new Request("exit");
            if (consoleArgs.isRequestInJson()) {
                Gson gson = new GsonBuilder().registerTypeAdapter(Request.class, new RequestGsonDeserializer()).create();
                String jsonString = Files.readString(Path.of(FILE_REQUEST_PATH_LOCAL_ENVIRONMENT + consoleArgs.getInputRequestFilePath()));
                request = gson.fromJson(jsonString, Request.class);

            } else {
                String command = consoleArgs.getRequestType();
                switch (command) {
                    case "get":
                    case "delete": {
                        request = new Request(command, consoleArgs.getKey());
                        break;
                    }
                    case "set": {
                        request = new Request(command, consoleArgs.getKey(), consoleArgs.getValue());
                        break;
                    }
                    case "exit": {
                        request = new Request(command);
                        break;
                    }
                }
            }
            SendCommand sendCommand = new SendCommand(serverConnection, request);
            sendCommand.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
