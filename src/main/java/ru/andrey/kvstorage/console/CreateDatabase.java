package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.logic.DatabaseImplementation;

public class CreateDatabase implements DatabaseCommand {

    private final ExecutionEnvironment environment;
    private final String databaseName;

    public CreateDatabase(ExecutionEnvironment environment, String databaseName) {
        this.environment = environment;
        this.databaseName = databaseName;
    }

    @Override
    public DatabaseCommandResult execute() {

        var database = environment.getDatabase(databaseName);

        if (database.isPresent()) {
            return DatabaseCommandResult.error("Database " + databaseName + " already exists");
        }

        environment.addDatabase(new DatabaseImplementation(databaseName));

        return DatabaseCommandResult.success(null);
    }
}
