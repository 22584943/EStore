import java.util.ArrayList;

public class ResponseObject {
    boolean success;
    String errorMessage;

    ResponseObject(boolean success, String errorMessage) {
        this.success = success;
        this.errorMessage = errorMessage;
    }
}