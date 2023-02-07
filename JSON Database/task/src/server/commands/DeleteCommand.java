package server.commands;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import server.Database;
import server.requests.Request;
import server.requests.Response;

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
            String errorMessage = new Gson().toJson(exception.getMessage());
            JsonElement jsonElementErrorMessage = JsonParser.parseString(errorMessage);
            return new Response("ERROR", jsonElementErrorMessage);
        }
    }
}
