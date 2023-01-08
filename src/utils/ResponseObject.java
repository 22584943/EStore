package utils;

public class ResponseObject {
    public boolean success;
    public String errorMessage;

    public ResponseObject(boolean success, String errorMessage) {
        this.success = success;
        this.errorMessage = errorMessage;
    }
}