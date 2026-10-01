/**
 * CUSTOM CHECKED EXCEPTION.
 * Thrown when a member tries to borrow more books than their allowed
 * limit. Same reasoning as BookNotAvailableException: this is a normal
 * business rule violation, not a bug, so we model it as a checked
 * exception that calling code must explicitly handle.
 */
public class MemberLimitExceededException extends Exception {
    public MemberLimitExceededException(String message) {
        super(message);
    }
}
