package library;

import java.util.List;

public interface Library {

    void addNewBook(Book book);

    void borrowBook(Book book, String student);

    void returnBook(Book book, String student);

    List<Book> findAvailableBooks();
}
