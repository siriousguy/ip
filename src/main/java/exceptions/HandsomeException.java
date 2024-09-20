package exceptions;

/**
 * Represents a custom exception specific to Handsome.
 * This exception is thrown when an error occurs within the Handsome's operations.
 */
public class HandsomeException extends Exception {

    /**
     * Constructs a new HandsomeException with the specified error message.
     *
     * @param error The error message describing the reason for the exception.
     */
    public HandsomeException(String error) {
        super(error);
    }
}
