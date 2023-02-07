package server;

import client.commandrequest.Request;
import client.commandrequest.RequestGsonDeserializer;
import com.google.gson.*;

import java.io.*;
import java.nio.file.Files;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Database {
    private static JsonObject database = new JsonObject();
    private static final ReadWriteLock lock = new ReentrantReadWriteLock();
    private static final Lock readLock = lock.readLock();
    private static final Lock writeLock = lock.writeLock();
    static String FILENAME_LOCAL_ENVIRONMENT = System.getProperty("user.dir") + "/src/server/data/db.json";
    private static final File fileDb = new File(FILENAME_LOCAL_ENVIRONMENT);

    public static JsonElement getValue(JsonElement keyInJson) throws IllegalArgumentException {
        readLock.lock();
        synchronized (readLock) {
            try {
                JsonObject value = database;
                Gson gson = new GsonBuilder().registerTypeAdapter(Request.class, new RequestGsonDeserializer()).create();
                JsonArray jsonArrayKeys = gson.fromJson(keyInJson, JsonArray.class);
                for (JsonElement jsonElementKey : jsonArrayKeys
                ) {
                    String key = jsonElementKey.getAsString();
                    if (value.has(key)) {
                        if (value.get(key).isJsonPrimitive()) {
                            readLock.unlock();
                            return value.get(key);
                        }
                        value = value.get(key).getAsJsonObject();
                    } else {
                        throw new IllegalArgumentException("No such key");
                    }
                }
                readLock.unlock();
                return value;
            } catch (Exception e) {
                readLock.unlock();
                throw new IllegalArgumentException("No such key");
            }
        }
    }

    public static void deleteValue(JsonElement keyInJson) throws IllegalArgumentException {
        writeLock.lock();
        synchronized (writeLock) {
            JsonObject value = database;
            Gson gson = new GsonBuilder().registerTypeAdapter(Request.class, new RequestGsonDeserializer()).create();
            JsonArray jsonArrayKeys = gson.fromJson(keyInJson, JsonArray.class);
            for (int i = 0; i < jsonArrayKeys.size() - 1; i++) {
                String key = jsonArrayKeys.get(i).getAsString();
                if (value.has(key)) {
                    value = value.get(key).getAsJsonObject();
                } else {
                    writeLock.unlock();
                    throw new IllegalArgumentException("No such key");
                }
            }
            value.remove(jsonArrayKeys.get(jsonArrayKeys.size() - 1).getAsString());

            saveDatabase();
        }
        writeLock.unlock();
    }

    public static void setValue(JsonElement keyInJson, JsonElement jsonValue) throws IllegalArgumentException {
        writeLock.lock();
        synchronized (writeLock) {
            try {
                JsonObject value = database;
                Gson gson = new GsonBuilder().registerTypeAdapter(Request.class, new RequestGsonDeserializer()).create();
                JsonArray jsonArrayKeys = gson.fromJson(keyInJson, JsonArray.class);
                for (int i = 0; i < jsonArrayKeys.size() - 1; i++) {
                    String key = jsonArrayKeys.get(i).getAsString();
                    if (value.has(key)) {
                        value = value.get(key).getAsJsonObject();
                    } else {
                        throw new IllegalArgumentException("No such key");
                    }
                }
                value.add(jsonArrayKeys.get(jsonArrayKeys.size() - 1).getAsString(), jsonValue);

            } catch (Exception exception) {
                writeLock.unlock();
                throw new IllegalArgumentException("No such key");
            }
            saveDatabase();
        }
        writeLock.unlock();
    }

    public static void saveDatabase() {
        writeLock.lock();
        synchronized (writeLock) {
            Gson gson = new Gson();
            String jsonString = gson.toJson(database);
            FileWriter writer;
            try {
                if (fileDb.exists()) {
                    writer = new FileWriter(fileDb, false);
                } else {
                    boolean resultFileProblem = !fileDb.getParentFile().mkdirs() && !fileDb.createNewFile();
                    if (resultFileProblem) {
                        throw new FileNotFoundException();
                    }
                    writer = new FileWriter(fileDb);
                }
                writer.write(jsonString);
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        writeLock.unlock();
    }

    public static void readDatabase() {
        readLock.lock();
        synchronized (readLock) {
            try {
                if (fileDb.exists()) {
                    Gson gson = new Gson();
                    String jsonString = Files.readString(fileDb.toPath());
                    try {
                        database = gson.fromJson(jsonString, JsonObject.class);
                    } catch (Exception e) {
                        database = new JsonObject();
                    }
                }
            } catch (IOException e) {
                readLock.unlock();
                throw new RuntimeException(e);
            }
        }
        readLock.unlock();
    }
}
