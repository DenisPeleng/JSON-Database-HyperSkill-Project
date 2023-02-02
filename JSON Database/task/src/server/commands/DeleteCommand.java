package server.commands;

import server.Database;
import server.Request;
import server.Response;

public class DeleteCommand implements Command {
    private final Request request;

    public DeleteCommand(Request request) {
        this.request = request;
    }

    @Override
    public Response execute() {
        try {
            Database.deleteValue(request.getKey());
            return new Response("OK");
        } catch (Exception exception) {
            return new Response("ERROR", exception.getMessage());
        }
    }
}
