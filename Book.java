/**
 * Book - a plain encapsulated class (no inheritance involved here).
 * Demonstrates encapsulation: fields are private, and availableCopies
 * can only change through controlled methods (issueCopy/returnCopy),
 * never set directly - this prevents it from going negative or above
 * totalCopies from outside code.
 */
public class Book {

    private String isbn;
    private String title;
    private String author;
    private int totalCopies;
    private int availableCopies;

    public Book(String isbn, String title, String author, int totalCopies) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies; // all copies available initially
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    // Package-private-style controlled mutation: only Library (which
    // coordinates the business rules) should call these, not random code.
    public boolean isAvailable() {
        return availableCopies > 0;
    }

    public void issueCopy() {
        if (availableCopies > 0) {
            availableCopies--;
        }
    }

    public void returnCopy() {
        if (availableCopies < totalCopies) {
            availableCopies++;
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] \"%s\" by %s - %d/%d available",
                isbn, title, author, availableCopies, totalCopies);
    }
}
