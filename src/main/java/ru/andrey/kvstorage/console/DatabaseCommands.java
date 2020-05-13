package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;

import java.util.Arrays;

public enum DatabaseCommands {

    CREATE_DATABASE(){
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment environment, String... options) throws DatabaseException {
            if (isArgumentsAmountCorrect(options.length)) {
                return new CreateDatabaseCommand(environment, options[1]);
            }
            throw new DatabaseException("Incorrect amount of arguments");
        }

        @Override
        protected int neededArgumentsAmount() {
            return 2;
        }
    },

    CREATE_TABLE(){
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment environment, String... options) throws DatabaseException {
            if (isArgumentsAmountCorrect(options.length)) {
                return new CreateTableCommand(environment, options[1], options[2]);
            }
            throw new DatabaseException("Incorrect amount of arguments");
        }

        @Override
        protected int neededArgumentsAmount() {
            return 3;
        }
    },

    UPDATE_KEY(){
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment environment, String... options) throws DatabaseException {
            if (isArgumentsAmountCorrect(options.length)) {
                return new UpdateKeyCommand(environment, options[1], options[2], options[3], options[4]);
            }
            throw new DatabaseException("Incorrect amount of arguments");
        }

        @Override
        protected int neededArgumentsAmount() {
            return 5;
        }
    },

    READ_KEY(){
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment environment, String... options) throws DatabaseException {
            if (isArgumentsAmountCorrect(options.length)) {
                return new ReadKeyCommand(environment, options[1], options[2], options[3]);
            }
            throw new DatabaseException("Incorrect amount of arguments");
        }

        @Override
        protected int neededArgumentsAmount() {
            return 4;
        }
    };

    public abstract DatabaseCommand getCommand( ExecutionEnvironment environment, String... options) throws DatabaseException;

    protected abstract int neededArgumentsAmount();

    protected boolean isArgumentsAmountCorrect(int argumentAmount) {
        return neededArgumentsAmount() == argumentAmount;
    }

    public static DatabaseCommand commandFromInput(ExecutionEnvironment environment, String input) throws DatabaseException {
        if (input == null || input.isEmpty()) {
            throw new DatabaseException("Incorrect input");
        }

        var options = input.split(" ");

        var commandName = options[0];

        var databaseCommands = Arrays.toString(DatabaseCommands.values());

        if (!databaseCommands.contains(commandName)){
            throw new DatabaseException("Incorrect command");
        }

        return DatabaseCommands.valueOf(commandName).getCommand(environment, options);
    }
}
