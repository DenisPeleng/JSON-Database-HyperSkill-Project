package client;

import com.beust.jcommander.JCommander;

public class Main {

    public static void main(String[] args) {
        ArgsCommandLine consoleArgs = new ArgsCommandLine();
        JCommander.newBuilder()
                .addObject(consoleArgs)
                .build()
                .parse(args);
        Client.startClient(consoleArgs);
    }

}
