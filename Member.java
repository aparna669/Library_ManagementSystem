import java.util.ArrayList;
import java.util.List;

/**
 * Member IS-A Person (INHERITANCE via "extends"). It inherits name, id,
 * and the getters/setters from Person for free, and adds its own fields
 * and behavior on top (borrowedBooks, maxBooks, borrow/return logic).
 */
public class Member extends Person {

    private List<Book> borrowedBooks;
    private final int maxBooks; // a member can never borrow more than this

    public Member(String name, String id, int maxBooks) {
        // super(...) calls Person's constructor to set up the inherited fields
        super(name, id);
        this.maxBooks = maxBooks;
        this.borrowedBooks = new ArrayList<>();
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    /**
     * Throws a checked exception instead of just printing an error and
     * returning - this forces whoever calls borrowBook() to consciously
     * decide how to handle the failure (e.g. show a message to the user),
     * rather than the failure being silently ignorable.
     */
    public void borrowBook(Book book) throws MemberLimitExceededException, BookNotAvailableException {
        if (borrowedBooks.size() >= maxBooks) {
            throw new MemberLimitExceededException(
                    getName() + " has already borrowed the maximum of " + maxBooks + " books.");
        }
        if (!book.isAvailable()) {
            throw new BookNotAvailableException(
                    "\"" + book.getTitle() + "\" has no available copies right now.");
        }
        book.issueCopy();
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.returnCopy();
        }
    }

    /**
     * POLYMORPHISM: this @Override provides Member's own version of the
     * abstract method declared in Person. When code elsewhere holds a
     * Person reference that actually points to a Member object and calls
     * displayRole(), THIS method runs - decided at runtime, not compile time.
     */
    @Override
    public String displayRole() {
        return "Member (borrowed " + borrowedBooks.size() + "/" + maxBooks + " books)";
    }
}
