package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final int PORT = 34522;
    private static final String ADDRESS = "127.0.0.1";
    protected static boolean isRunning = true;
    private final static int poolSize = Runtime.getRuntime().availableProcessors();

    public static void setIsRunning(boolean isRunning) {
        Server.isRunning = isRunning;
    }

    public static void start() {
        try (ServerSocket server = new ServerSocket(PORT, 50, InetAddress.getByName(ADDRESS))) {
            ExecutorService executor = Executors.newFixedThreadPool(poolSize);
            Database.readDatabase();
            System.out.println("Server started!");
            while (isRunning) {
                Session session = new Session(server);
                executor.submit(session);
            }
            executor.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
