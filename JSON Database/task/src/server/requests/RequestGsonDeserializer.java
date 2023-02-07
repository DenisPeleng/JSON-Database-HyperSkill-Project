package server.requests;

import com.google.gson.*;

import java.lang.reflect.Type;

public class RequestGsonDeserializer implements JsonDeserializer<Request> {
    @Override
    public Request deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement jsonRequestType = jsonObject.get("type");
        JsonElement jsonKey = jsonObject.get("key");
        if (jsonKey != null && jsonKey.isJsonPrimitive()) {
            Gson gson = new Gson();
            JsonArray newJsonArr = new JsonArray();
            newJsonArr.add(gson.fromJson(jsonKey.getAsString(), JsonElement.class));
            jsonKey = newJsonArr;
        }
        JsonElement jsonValue = jsonObject.get("value");
        if (jsonValue != null && jsonValue.isJsonPrimitive()) {
            Gson gson = new Gson();
            String jsonValueN = gson.toJson(jsonValue.getAsString());
            jsonValue = gson.fromJson(jsonValueN, JsonElement.class);
        }
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
