package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.DatabaseImplementation;

import java.util.Optional;

public class UpdateKey implements DatabaseCommand {

    private final ExecutionEnvironment environment;
    private final String databaseName;
    private final String tableName;
    private final String objectKey;
    private final String objectValue;

    public UpdateKey(
            ExecutionEnvironment environment,
            String databaseName,
            String tableName,
            String objectKey,
            String objectValue
    ) {
        this.environment = environment;
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.objectKey = objectKey;
        this.objectValue = objectValue;
    }


    @Override
    public DatabaseCommandResult execute() {

        var database = environment.getDatabase(databaseName);

        if (database.isPresent()) {
            try {
                database.get().write(tableName, objectKey, objectValue);
                return DatabaseCommandResult.success(null);
            } catch (DatabaseException e) {
                return DatabaseCommandResult.error(e.getMessage());
            }
        } else {
            return DatabaseCommandResult.error("Database" + databaseName + "don't exists");
        }
    }
}
