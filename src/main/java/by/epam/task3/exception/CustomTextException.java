package by.epam.task3.exception;

public class CustomTextException extends Exception {
    public CustomTextException() {
    }

    public CustomTextException(String message) {
        super(message);
    }

    public CustomTextException(String message, Exception e) {
        super(message, e);
    }

    public CustomTextException(Exception e) {
        super(e);
    }
}