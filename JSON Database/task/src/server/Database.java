package server;

import java.util.HashMap;

public class Database {
    private static final HashMap<String,String> database = new HashMap<>();

    public static String getValue(String key) throws IllegalArgumentException {
        if (database.containsKey(key)) {
            return database.get(key);
        } else {
            throw new IllegalArgumentException("No such key");
        }
    }

    public static void deleteValue(String key) throws IllegalArgumentException {
        if (database.containsKey(key)) {
            database.remove(key);
        } else {
            throw new IllegalArgumentException("No such key");
        }
    }

    public static void setValue(String key, String textValue) throws IllegalArgumentException {
        try {
            database.put(key, textValue);
        } catch (Exception exception) {
            throw new IllegalArgumentException("No such key");
        }
    }
}
