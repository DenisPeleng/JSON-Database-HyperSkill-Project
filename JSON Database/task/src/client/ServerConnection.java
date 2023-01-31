package client;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ServerConnection implements Closeable {
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private Socket socket;

    public void createConnection() {
        try {
            System.out.println("Client started!");
            String ADDRESS = "127.0.0.1";
            int PORT = 34522;
            socket = new Socket(InetAddress.getByName(ADDRESS), PORT);
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public DataInputStream getInputStream() {
        return inputStream;
    }

    public DataOutputStream getOutputStream() {
        return outputStream;
    }

    @Override
    public void close() {
        try {
            outputStream.close();
            inputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
