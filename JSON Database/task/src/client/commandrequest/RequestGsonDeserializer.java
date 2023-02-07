package client.commandrequest;

import com.google.gson.*;

import java.lang.reflect.Type;

public class RequestGsonDeserializer implements JsonDeserializer<Request> {
    @Override
    public Request deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement jsonRequestType = jsonObject.get("type");
        JsonElement jsonKey = jsonObject.get("key");
        JsonElement jsonValue = jsonObject.get("value");
        if (jsonKey != null && jsonValue != null) {
            return new Request(jsonRequestType.getAsString(),
                    jsonKey,
                    jsonValue);
        } else if (jsonKey != null) {
            return new Request(jsonRequestType.getAsString(),
                    jsonKey);
        } else {
            return new Request(jsonRequestType.getAsString());
        }

    }
}
