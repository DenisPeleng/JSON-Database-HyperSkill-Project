package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static server.Database.getDatabase;

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
            String answerFromClient = input.readUTF();
            System.out.printf("Received: %s\n", answerFromClient);
            String resultAnswer = processCommand(answerFromClient);
            System.out.printf("Sent: %s\n", resultAnswer);
            output.writeUTF(resultAnswer);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String processCommand(String fullLineCommand) {
        String[] database = getDatabase();
        String[] commandArr = fullLineCommand.split(" ");

        try {

            String command = commandArr[0].toLowerCase();
            if (command.equals("exit")) {
                Server.setIsRunning(false);
                return "OK";
            }
            int numberArr = Integer.parseInt(commandArr[1]) - 1;
            if (numberArr < 0 || numberArr > database.length) {
                throw new IllegalArgumentException();
            }
            switch (command) {
                case "set":
                    String text = fullLineCommand.replace(command + " ", "").replace(numberArr + " ", "");
                    database[numberArr] = text;
                    return "OK";
                case "get":
                    if (database[numberArr] != null) {
                        return database[numberArr];
                    } else {
                        return "ERROR";
                    }
                case "delete":
                    database[numberArr] = null;
                    return "OK";
            }

        } catch (Exception exception) {
            return ("ERROR");
        }
        return ("ERROR");
    }

}