package client;

import com.beust.jcommander.Parameter;

public class ArgsCommandLine {

    @Parameter(names = "-t", description = "The type of the request", required = true)
    private String requestType = "";
    @Parameter(names = "-k", description = "index of the cell")
    private String key = "";

    @Parameter(names = "-v", description = "Text to set")
    private String text = "";

    public String getRequestType() {
        return requestType;
    }

    public String getKey() {
        return key;
    }

    public String getText() {
        return text;
    }
}