package client.argsprocessing;

import com.beust.jcommander.IStringConverter;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonElementConverter implements IStringConverter<JsonElement> {
    @Override
    public JsonElement convert(String value) {
        Gson gson = new Gson();
        String jsonValue = gson.toJson(value);
        return JsonParser.parseString(jsonValue);
    }
}
