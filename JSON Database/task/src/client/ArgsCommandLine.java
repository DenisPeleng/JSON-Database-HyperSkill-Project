package client;

import com.beust.jcommander.Parameter;

public class ArgsCommandLine {

    @Parameter(names = "-t", description = "The type of the request", required = true)
    private String requestType = "";
    @Parameter(names = "-i", description = "index of the cell")
    private int indexOfCell = 0;

    @Parameter(names = "-m", description = "Text to set")
    private String text = "";

    public String getRequestType() {
        return requestType;
    }

    public int getIndexOfCell() {
        return indexOfCell;
    }

    public String getText() {
        return text;
    }
}