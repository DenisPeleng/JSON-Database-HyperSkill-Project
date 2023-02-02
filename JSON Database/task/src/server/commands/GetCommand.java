package server.commands;

import server.Database;
import server.Request;
import server.Response;

public class GetCommand implements Command {
    private final Request request;

    public GetCommand(Request request) {
        this.request = request;
    }

    @Override
    public Response execute() {
        try {
            String value = Database.getValue(request.getKey());
            return new Response("OK", value);
        } catch (Exception exception) {
            return new Response("ERROR", exception.getMessage());
        }
    }
}
