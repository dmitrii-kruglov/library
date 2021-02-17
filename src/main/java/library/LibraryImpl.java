package library;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LibraryImpl implements Library {
    private Map<Book, String> books = new HashMap<Book, String>();

    public void addNewBook(Book book) {
        if (book == null) throw new IllegalArgumentException("Book is missing");
        books.put(book, null);
    }

    public void borrowBook(Book book, String student) {
        if (book == null) throw new IllegalArgumentException("Book is missing");
        if (student == null) throw new IllegalArgumentException("Student is missing");
        if (!books.containsKey(book)) throw new IllegalArgumentException("There is no " + book + " book in the library");
        books.replace(book, student);
    }

    public void returnBook(Book book, String student) {
        if (book == null) throw new IllegalArgumentException("Book is missing");
        if (student == null) throw new IllegalArgumentException("Student is missing");
        if (!books.containsKey(book)) throw new IllegalArgumentException("There is no " + book + " book in the library");
        if (!books.get(book).equals(student)) throw new IllegalArgumentException("Student " + student + " did not take book " + book);
        books.replace(book, null);
    }

    public List<Book> findAvailableBooks() {
        return books
                .entrySet()
                .stream()
                .filter(record -> record.getValue() == null)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
