package client.argsprocessing;

import com.beust.jcommander.Parameter;
import com.google.gson.JsonElement;

public class ArgsCommandLine {

    @Parameter(names = "-t", description = "The type of the request")
    private String requestType = "";
    @Parameter(names = "-k", converter = JsonElementConverter.class, description = "index of the cell")
    private JsonElement key = null;
    @Parameter(names = "-v", converter = JsonElementConverter.class, description = "Text to set")
    private JsonElement value = null;
    @Parameter(names = "-in", description = "File path to request in JSON")
    private String inputRequestFilePath = "";

    public String getRequestType() {
        return requestType;
    }

    public JsonElement getKey() {
        return key;
    }

    public JsonElement getValue() {

        return value;
    }

    public String getInputRequestFilePath() {
        return inputRequestFilePath;
    }

    public boolean isRequestInJson() {
        return !inputRequestFilePath.isEmpty();
    }
}