package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.DatabaseImplementation;

import java.util.Optional;

public class ReadKey implements DatabaseCommand {

    private final ExecutionEnvironment environment;
    private final String databaseName;
    private final String tableName;
    private final String objectKey;

    public ReadKey(
            ExecutionEnvironment environment,
            String databaseName,
            String tableName,
            String objectKey
    ) {
        this.environment = environment;
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.objectKey = objectKey;
    }

    @Override
    public DatabaseCommandResult execute() {

        var database = environment.getDatabase(databaseName);

        if (database.isPresent()) {
            try {
                return DatabaseCommandResult.success(database.get().read(tableName, objectKey));
            } catch (DatabaseException e) {
                return DatabaseCommandResult.error(e.getMessage());
            }
        } else {
            return DatabaseCommandResult.error("Database" + databaseName + "don't exists");
        }

    }
}
