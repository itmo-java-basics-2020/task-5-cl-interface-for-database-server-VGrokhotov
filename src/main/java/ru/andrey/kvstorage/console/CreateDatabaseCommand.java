package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.logic.DatabaseImpl;

public class CreateDatabaseCommand implements DatabaseCommand {

    private final ExecutionEnvironment environment;
    private final String databaseName;

    public CreateDatabaseCommand(ExecutionEnvironment environment, String databaseName) {
        this.environment = environment;
        this.databaseName = databaseName;
    }

    @Override
    public DatabaseCommandResult execute() {

        var database = environment.getDatabase(databaseName);

        if (database.isPresent()) {
            return DatabaseCommandResult.error(String.format("Database %s already exists", databaseName));
        }

        environment.addDatabase(new DatabaseImpl(databaseName));

        return DatabaseCommandResult.success(String.format("Database %s created", databaseName));
    }
}
