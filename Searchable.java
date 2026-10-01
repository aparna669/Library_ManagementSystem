/**
 * INTERFACE - a pure contract with no implementation. Any class that
 * "implements Searchable" is PROMISING it will provide these two methods.
 *
 * Why an interface here instead of just adding these methods directly to
 * Library? In a bigger system you might have multiple searchable things
 * (Library, an online catalog, an archive) that all need a consistent
 * search API but have nothing else in common - inheritance (extends)
 * wouldn't fit since they're not "the same kind of thing", but they can
 * all still implement the same interface.
 */
public interface Searchable {
    Book searchByIsbn(String isbn);
    java.util.List<Book> searchByTitle(String titleKeyword);
}
