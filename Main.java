import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        seedData(library);

        // ---- POLYMORPHISM IN ACTION ----
        // This array is typed as Person[], but actually holds a mix of
        // Member and Librarian objects. We don't need to know or check
        // which one each element "really" is - calling displayRole() on
        // each one automatically runs the correct subclass's version.
        Person[] people = {
                library.getAllMembers().get(0),
                library.getAllMembers().get(1),
                new Librarian("Mr. Verma", "L001", "EMP-9")
        };
        System.out.println("=== All people in the system (polymorphism demo) ===");
        for (Person p : people) {
            System.out.println(p + " -> " + p.displayRole());
        }
        System.out.println();

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt(scanner);

            switch (choice) {
                case 1 -> listBooks(library);
                case 2 -> searchBook(library, scanner);
                case 3 -> issueBook(library, scanner);
                case 4 -> returnBook(library, scanner);
                case 5 -> listMembers(library);
                case 0 -> {
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid choice, try again.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void seedData(Library library) {
        library.addBook(new Book("ISBN001", "Clean Code", "Robert C. Martin", 2));
        library.addBook(new Book("ISBN002", "Effective Java", "Joshua Bloch", 1));
        library.addBook(new Book("ISBN003", "Design Patterns", "Gang of Four", 1));

        Member m1 = new Member("Aparna Rani", "M001", 3);
        Member m2 = new Member("Rohit Kumar", "M002", 2);
        library.registerMember(m1);
        library.registerMember(m2);
    }

    private static void printMenu() {
        System.out.println("=== Library Menu ===");
        System.out.println("1. List all books");
        System.out.println("2. Search book by title");
        System.out.println("3. Issue a book to a member");
        System.out.println("4. Return a book from a member");
        System.out.println("5. List all members");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private static void listBooks(Library library) {
        System.out.println("--- Books ---");
        for (Book b : library.getAllBooks()) {
            System.out.println(b);
        }
    }

    private static void listMembers(Library library) {
        System.out.println("--- Members ---");
        for (Member m : library.getAllMembers()) {
            System.out.println(m + " -> " + m.displayRole());
        }
    }

    private static void searchBook(Library library, Scanner scanner) {
        System.out.print("Enter title keyword: ");
        String keyword = scanner.nextLine();
        List<Book> results = library.searchByTitle(keyword);
        if (results.isEmpty()) {
            System.out.println("No books matched.");
        } else {
            results.forEach(System.out::println);
        }
    }

    /**
     * This is where the CHECKED EXCEPTIONS from borrowBook() must be
     * handled - the compiler would refuse to build this code otherwise.
     * Using try/catch here lets us show a friendly message instead of
     * crashing the program.
     */
    private static void issueBook(Library library, Scanner scanner) {
        System.out.print("Enter member ID: ");
        String memberId = scanner.nextLine();
        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();

        Member member = findMemberById(library, memberId);
        Book book = library.searchByIsbn(isbn);

        if (member == null || book == null) {
            System.out.println("Member or book not found.");
            return;
        }

        try {
            library.issueBookToMember(member, book);
            System.out.println("Issued \"" + book.getTitle() + "\" to " + member.getName());
        } catch (MemberLimitExceededException | BookNotAvailableException e) {
            // Multi-catch: handle both custom exceptions the same way here,
            // since in this case we just want to show the message either way.
            System.out.println("Could not issue book: " + e.getMessage());
        }
    }

    private static void returnBook(Library library, Scanner scanner) {
        System.out.print("Enter member ID: ");
        String memberId = scanner.nextLine();
        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();

        Member member = findMemberById(library, memberId);
        Book book = library.searchByIsbn(isbn);

        if (member == null || book == null) {
            System.out.println("Member or book not found.");
            return;
        }

        library.returnBookFromMember(member, book);
        System.out.println(member.getName() + " returned \"" + book.getTitle() + "\"");
    }

    private static Member findMemberById(Library library, String id) {
        for (Member m : library.getAllMembers()) {
            if (m.getId().equalsIgnoreCase(id)) {
                return m;
            }
        }
        return null;
    }

    private static int readInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline
        return value;
    }
}
