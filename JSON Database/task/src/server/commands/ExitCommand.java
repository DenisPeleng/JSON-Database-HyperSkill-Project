package server.commands;

import server.Response;
import server.Server;

public class ExitCommand implements Command {
    public ExitCommand() {
    }

    @Override
    public Response execute() {
        Server.setIsRunning(false);
        return new Response("OK");
    }
}
