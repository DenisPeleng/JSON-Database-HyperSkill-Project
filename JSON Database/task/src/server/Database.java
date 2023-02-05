package server;

import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Database {
    private static HashMap<String, String> database = new HashMap<>();
    private static final ReadWriteLock lock = new ReentrantReadWriteLock();
    private static final Lock readLock = lock.readLock();
    private static final Lock writeLock = lock.writeLock();
    static String FILENAME_LOCAL_ENVIRONMENT = System.getProperty("user.dir") + "/src/server/data/db.json";
    private static final File fileDb = new File(FILENAME_LOCAL_ENVIRONMENT);

    public static String getValue(String key) throws IllegalArgumentException {
        readLock.lock();
        synchronized (readLock) {
            readDatabase();
            if (database.containsKey(key)) {
                readLock.unlock();
                return database.get(key);
            } else {
                readLock.unlock();
                throw new IllegalArgumentException("No such key");
            }
        }

    }

    public static void deleteValue(String key) throws IllegalArgumentException {
        writeLock.lock();
        synchronized (writeLock) {
            if (database.containsKey(key)) {
                database.remove(key);
            } else {
                writeLock.unlock();
                throw new IllegalArgumentException("No such key");
            }
            saveDatabase();
        }
        writeLock.unlock();
    }

    public static void setValue(String key, String textValue) throws IllegalArgumentException {
        writeLock.lock();
        synchronized (writeLock) {
            try {
                database.put(key, textValue);
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
                    boolean resultFileSuccess = fileDb.getParentFile().mkdirs() && fileDb.createNewFile();
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
                        database = gson.fromJson(jsonString, HashMap.class);
                    } catch (Exception e) {
                        database = new HashMap<>();
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
