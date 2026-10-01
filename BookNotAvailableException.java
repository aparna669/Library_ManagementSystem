/**
 * CUSTOM CHECKED EXCEPTION.
 * Extending Exception (not RuntimeException) makes this a CHECKED
 * exception - Java forces any calling code to either catch it or declare
 * "throws BookNotAvailableException". This is intentional here: failing
 * to issue a book because no copies are left is an expected, recoverable
 * business situation, not a programming bug - so we want callers to be
 * forced to handle it explicitly rather than let it silently crash.
 */
public class BookNotAvailableException extends Exception {
    public BookNotAvailableException(String message) {
        super(message);
    }
}
