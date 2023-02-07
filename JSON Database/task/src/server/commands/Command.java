package server.commands;

import server.requests.Response;

public interface Command {
    Response execute();
}
