package client;

import com.beust.jcommander.Parameter;

public class ArgsCommandLine {

    @Parameter(names = "-t", description = "The type of the request")
    private String requestType = "";
    @Parameter(names = "-k", description = "index of the cell")
    private String key = "";

    @Parameter(names = "-v", description = "Text to set")
    private String text = "";
    @Parameter(names = "-in", description = "File path to request in JSON")
    private String inputRequestFilePath = "";

    public String getRequestType() {
        return requestType;
    }

    public String getKey() {
        return key;
    }

    public String getText() {
        return text;
    }

    public String getInputRequestFilePath() {
        return inputRequestFilePath;
    }

    public boolean isRequestInJson() {
        return !inputRequestFilePath.isEmpty();
    }
}