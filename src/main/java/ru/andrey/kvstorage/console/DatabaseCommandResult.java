package ru.andrey.kvstorage.console;

import java.util.Objects;
import java.util.Optional;

public interface DatabaseCommandResult {

    Optional<String> getResult();

    DatabaseCommandStatus getStatus();

    boolean isSuccess();

    String getErrorMessage();

    enum DatabaseCommandStatus {
        SUCCESS, FAILED
    }

    static DatabaseCommandResult success(String result){
        return new DatabaseCommandResultImplementation(result, null, DatabaseCommandStatus.SUCCESS);
    }

    static DatabaseCommandResult error(String message){
        return new DatabaseCommandResultImplementation(null, message, DatabaseCommandStatus.FAILED);
    }

    final class DatabaseCommandResultImplementation implements DatabaseCommandResult {

        private final String result;
        private final String errorMessage;
        private final DatabaseCommandStatus status;

        public DatabaseCommandResultImplementation(String result, String errorMessage, DatabaseCommandStatus status) {
            this.result = result;
            this.errorMessage = errorMessage;
            this.status = status;
        }

        @Override
        public Optional<String> getResult() {
            return Optional.ofNullable(result);
        }

        @Override
        public DatabaseCommandStatus getStatus() {
            return status;
        }

        @Override
        public boolean isSuccess() {
            return status.equals(DatabaseCommandStatus.SUCCESS);
        }

        @Override
        public String getErrorMessage() {
            return errorMessage;
        }

        @Override
        public String toString() {
            return "DatabaseCommandResultImplementation:" +
                    "result = \"" + result + '\"' +
                    ", errorMessage = \"" + errorMessage + '\"' +
                    ", status = " + status;
        }
    }

}

