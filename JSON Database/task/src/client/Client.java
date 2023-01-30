package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void startClient() {
        String ADDRESS = "127.0.0.1";
        int PORT = 34522;
        System.out.println("Client started!");
        try (Socket socket = new Socket(InetAddress.getByName(ADDRESS), PORT)) {

            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            String requestFromClient = "Give me a record # 12";
            output.writeUTF(requestFromClient);
            System.out.printf("Sent: %s\n", requestFromClient);
            String answerFromServer = input.readUTF();
            System.out.printf("Received: %s\n", answerFromServer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
