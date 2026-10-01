/**
 * Librarian IS-A Person too - the second subclass of the abstract Person
 * class. This is what makes displayRole() genuinely polymorphic: two
 * different classes, two different implementations of the same method
 * signature.
 */
public class Librarian extends Person {

    private String employeeId;

    public Librarian(String name, String id, String employeeId) {
        super(name, id);
        this.employeeId = employeeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    @Override
    public String displayRole() {
        return "Librarian (Employee ID: " + employeeId + ")";
    }
}
