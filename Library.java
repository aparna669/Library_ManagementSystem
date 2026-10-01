import java.util.ArrayList;
import java.util.List;

/**
 * Library is the central class that coordinates everything. It
 * "implements Searchable", meaning it MUST provide real bodies for
 * searchByIsbn() and searchByTitle() (the interface only declared them).
 *
 * This class uses COMPOSITION ("has-a" relationship) rather than
 * inheritance: a Library HAS a list of Books and a list of Members -
 * it isn't a type of Book or Member itself. Composition vs inheritance
 * is a classic interview question - this class is a good concrete example.
 */
public class Library implements Searchable {

    private List<Book> books;
    private List<Member> members;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void registerMember(Member member) {
        members.add(member);
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public List<Member> getAllMembers() {
        return members;
    }

    /**
     * Notice this method does NOT catch the exceptions - it lets them
     * propagate up to whoever called issueBookToMember(). That's a
     * deliberate design choice: Library doesn't know HOW the failure
     * should be shown to the user (console message? GUI popup? log file?),
     * so it leaves that decision to the caller (Main, in our case).
     */
    public void issueBookToMember(Member member, Book book)
            throws MemberLimitExceededException, BookNotAvailableException {
        member.borrowBook(book);
    }

    public void returnBookFromMember(Member member, Book book) {
        member.returnBook(book);
    }

    @Override
    public Book searchByIsbn(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equalsIgnoreCase(isbn)) {
                return b;
            }
        }
        return null; // not found
    }

    @Override
    public List<Book> searchByTitle(String titleKeyword) {
        List<Book> results = new ArrayList<>();
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(titleKeyword.toLowerCase())) {
                results.add(b);
            }
        }
        return results;
    }
}
