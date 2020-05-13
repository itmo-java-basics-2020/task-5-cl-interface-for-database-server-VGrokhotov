package ru.andrey.kvstorage.logic;

import ru.andrey.kvstorage.exception.DatabaseException;

import java.util.HashMap;
import java.util.Map;

public final class DatabaseImpl implements Database {

    private final Map<String, Map<String, String>> tables;
    private final String name;

    public DatabaseImpl(String name) {
        this.tables = new HashMap<>();
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void createTableIfNotExists(String tableName) throws DatabaseException {

        if (this.tables.containsKey(tableName)) {
            throw new DatabaseException(String.format( "Table %s already exists", tableName));
        }

        this.tables.put(tableName, new HashMap<>());

    }

    @Override
    public void createTableIfNotExists(String tableName, int segmentSizeInBytes) throws DatabaseException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void write(String tableName, String objectKey, String objectValue) throws DatabaseException {

        if (this.tables.containsKey(tableName)) {
            this.tables.get(tableName).put(objectKey, objectValue);
        } else {
            throw new DatabaseException(String.format("There is no key %s in table %s", objectKey, tableName));
        }

    }

    @Override
    public String read(String tableName, String objectKey) throws DatabaseException {

        if (this.tables.containsKey(tableName)) {
            if (this.tables.get(tableName).containsKey(objectKey)){
                return this.tables.get(tableName).get(objectKey);
            } else {
                throw new DatabaseException(String.format("There is no key %s in table %s", objectKey, tableName));
            }
        } else {
            throw new DatabaseException(String.format("There is no key %s in table %s", objectKey, tableName));
        }
    }

}
