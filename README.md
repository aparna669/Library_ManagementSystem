# Library Management System (Java, Console)

A console-based Library Management System built to demonstrate core
Object-Oriented Programming concepts in Java: encapsulation, inheritance,
abstraction, polymorphism, interfaces, collections, and custom exception
handling.

## How to run

```bash
javac *.java
java Main
```

You'll see a menu-driven console app. Sample flow to try:
1. Choose `1` to list all seeded books.
2. Choose `3` to issue "Clean Code" (ISBN001) to member M001.
3. Choose `3` again with ISBN002 (Effective Java, only 1 copy) twice in a
   row for the same or different members to see the custom
   `BookNotAvailableException` message fire.
4. Choose `4` to return a book and see availability update.

## Class overview

| Class | Role |
|---|---|
| `Person` | Abstract base class — shared fields (name, id), abstract `displayRole()` |
| `Member` | Extends `Person` — borrows/returns books, enforces a borrow limit |
| `Librarian` | Extends `Person` — represents staff |
| `Book` | Encapsulated entity — tracks total/available copies |
| `Searchable` | Interface — contract for search-by-ISBN / search-by-title |
| `Library` | Core logic, implements `Searchable`, composes Books + Members |
| `BookNotAvailableException` | Custom checked exception |
| `MemberLimitExceededException` | Custom checked exception |
| `Main` | Console menu, demonstrates polymorphism explicitly |

See the accompanying **Interview Prep Guide** for a full explanation of
every OOP concept used and likely interview questions.
