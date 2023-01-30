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
            int number = Integer.parseInt(answerFromClient.split("#")[1].trim());
            //     String answerFromServer = processCommand(answerFromClient);
            String answerFromServer = String.format("A record # %d was sent!", number);
            System.out.printf("Sent: %s\n", answerFromServer);
            output.writeUTF(answerFromServer);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String processCommand(String fullLineCommand) {
        String[] database = getDatabase();
        while (true) {

            String[] commandArr = fullLineCommand.split(" ");
            try {
                String command = commandArr[0].toLowerCase();
                int numberArr = Integer.parseInt(commandArr[1]) - 1;
                if (numberArr < 0 || numberArr > database.length) {
                    throw new IllegalArgumentException();
                }
                switch (command) {
                    case "set":
                        String text = fullLineCommand.replace(command + " ", "").replace(numberArr + " ", "");
                        database[numberArr] = text;
                        System.out.println("OK");
                        break;
                    case "get":
                        if (database[numberArr] != null) {
                            System.out.println(database[numberArr]);
                        } else {
                            System.out.println("ERROR");
                        }
                        break;
                    case "delete":
                        database[numberArr] = null;
                        System.out.println("OK");
                        break;
                }

            } catch (Exception exception) {
                System.out.println("ERROR");
            }
        }
    }

}