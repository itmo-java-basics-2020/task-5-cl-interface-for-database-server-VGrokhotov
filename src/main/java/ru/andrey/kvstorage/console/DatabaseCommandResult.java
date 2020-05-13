package ru.andrey.kvstorage.console;

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
        return new DatabaseCommandResultImpl(result, null, DatabaseCommandStatus.SUCCESS);
    }

    static DatabaseCommandResult error(String message){
        return new DatabaseCommandResultImpl(null, message, DatabaseCommandStatus.FAILED);
    }

    final class DatabaseCommandResultImpl implements DatabaseCommandResult {

        private final String result;
        private final String errorMessage;
        private final DatabaseCommandStatus status;

        public DatabaseCommandResultImpl(String result, String errorMessage, DatabaseCommandStatus status) {
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
            return status == DatabaseCommandStatus.SUCCESS;
        }

        @Override
        public String getErrorMessage() {
            return errorMessage;
        }

        @Override
        public String toString() {
            return String.format("DatabaseCommandResultImplementation: result = \"%s\", errorMessage = \"%s\", status = %s" , result, errorMessage, status);
        }
    }

}

