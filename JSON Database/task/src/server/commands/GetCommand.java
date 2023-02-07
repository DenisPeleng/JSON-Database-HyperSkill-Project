package server.commands;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import server.Database;
import server.requests.Request;
import server.requests.Response;

public class GetCommand implements Command {
    private final Request request;

    public GetCommand(Request request) {
        this.request = request;
    }

    @Override
    public Response execute() {
        try {
            JsonElement value = Database.getValue(request.getKey());
                        return new Response("OK", value);
        } catch (Exception exception) {
            String errorMessage = new Gson().toJson(exception.getMessage());
            JsonElement jsonElementErrorMessage = JsonParser.parseString(errorMessage);
            return new Response("ERROR", jsonElementErrorMessage);
        }
    }
}
