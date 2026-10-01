/**
 * Person is an ABSTRACT CLASS - it represents a common "template" for
 * anyone in the library system (a Member or a Librarian), but you can
 * never create a plain "Person" object directly (new Person(...) is illegal).
 *
 * WHY ABSTRACT? Member and Librarian share fields (name, id) and some
 * behavior, but each has its OWN way of describing its role. That's a
 * perfect case for an abstract class: share the common code, force
 * subclasses to implement the parts that differ.
 */
public abstract class Person {

    // ENCAPSULATION: fields are private. Nothing outside this class can
    // touch them directly - they can only be read/changed through the
    // public getter/setter methods below. This protects the data from
    // being set to invalid values from outside the class.
    private String name;
    private String id;

    // Constructor - runs whenever a subclass (Member/Librarian) is created
    public Person(String name, String id) {
        this.name = name;
        this.id = id;
    }

    // Getters - controlled read access to private fields
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    // Setter - controlled write access. We could add validation here later
    // (e.g. reject an empty name) without changing any code that calls it.
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ABSTRACT METHOD: no body here. Every subclass of Person MUST provide
     * its own implementation of this method, or it won't compile.
     * This is what enables POLYMORPHISM later: we can hold a Person
     * reference and call displayRole() without knowing (or caring) at
     * compile time whether it's actually a Member or a Librarian - the
     * correct version runs automatically at runtime.
     */
    public abstract String displayRole();

    // A normal (non-abstract) method - shared by all subclasses as-is,
    // unless a subclass chooses to override it.
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name;
    }
}
