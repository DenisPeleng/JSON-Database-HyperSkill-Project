package server.commands;

import server.Response;

public interface Command {
    Response execute();
}
