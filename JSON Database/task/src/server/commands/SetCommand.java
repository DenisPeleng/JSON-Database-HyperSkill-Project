package server.commands;

import server.Database;
import server.Request;
import server.Response;

public class SetCommand implements Command {
    private final Request request;

    public SetCommand(Request request) {
        this.request = request;
    }

    @Override
    public Response execute() {
        try {
            Database.setValue(request.getKey(), request.getValue());
            return new Response("OK");
        } catch (Exception exception) {
            return new Response("ERROR", exception.getMessage());
        }
    }
}
