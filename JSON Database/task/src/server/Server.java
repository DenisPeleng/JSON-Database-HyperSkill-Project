package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

public class Server {

    private static final int PORT = 34522;
    private static final String ADDRESS = "127.0.0.1";
    protected static boolean isRunning = true;

    public static void setIsRunning(boolean isRunning) {
        Server.isRunning = isRunning;
    }


    public static void start() {
        try (ServerSocket server = new ServerSocket(PORT, 50, InetAddress.getByName(ADDRESS))) {
            System.out.println("Server started!");
            while (isRunning) {
                Session session = new Session(server.accept());
                session.start();
                session.join();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
