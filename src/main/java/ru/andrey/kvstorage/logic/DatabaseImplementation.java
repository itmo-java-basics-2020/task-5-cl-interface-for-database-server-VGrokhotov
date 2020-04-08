package ru.andrey.kvstorage.logic;

import ru.andrey.kvstorage.exception.DatabaseException;

import java.util.HashMap;

public final class DatabaseImplementation implements Database {

    private final HashMap<String, HashMap<String, String>> tables;
    private final String name;

    public DatabaseImplementation(String name) {
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
            throw new DatabaseException( "Table " + tableName + " already exists");
        }

        this.tables.put(tableName, new HashMap<>());

    }

    @Override
    public void createTableIfNotExists(String tableName, int segmentSizeInBytes) throws DatabaseException {
        //
    }

    @Override
    public void write(String tableName, String objectKey, String objectValue) throws DatabaseException {

        if (this.tables.containsKey(tableName)) {
            this.tables.get(tableName).put(objectKey, objectValue);
        } else {
            throw new DatabaseException("There is no key " + objectKey + " in table " + tableName);
        }

    }

    @Override
    public String read(String tableName, String objectKey) throws DatabaseException {

        if (this.tables.containsKey(tableName)) {
            if (this.tables.get(tableName).containsKey(objectKey)){
                return this.tables.get(tableName).get(objectKey);
            } else {
                throw new DatabaseException("There is no key " + objectKey + " in table " + tableName);
            }
        } else {
            throw new DatabaseException("There is no key " + objectKey + " in table " + tableName);
        }

    }

}
