package library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class LibraryImplTest {
    private LibraryImpl sut;
    Book dummyBook;
    String dummyStudent1 = "Student1";
    String dummyStudent2 = "Student2";

    @BeforeEach
    public void initDummies() {
        sut = new LibraryImpl();
        dummyBook = new Book(new Random().nextInt(100), "Dummy book");
    }

    @Test
    public void shouldGetErrorWhenAddedNullBook() {
        assertThrows(IllegalArgumentException.class, () -> sut.addNewBook(null));
    }

    @Test
    public void shouldGetEmptyListWhenCreated() {
        assertEquals(0, sut.findAvailableBooks().size());
    }

    @Test
    public void shouldBeAvailableBookWhenAdded() {
        sut.addNewBook(dummyBook);
        assertEquals(1, sut.findAvailableBooks().size());
    }

    @Test
    public void shouldGetErrorWhenNullStudentBorrows() {
        assertThrows(IllegalArgumentException.class, () -> sut.borrowBook(dummyBook, null));
    }

    @Test
    public void shouldGetErrorWhenNullStudentReturns() {
        assertThrows(IllegalArgumentException.class, () -> sut.returnBook(dummyBook, null));
    }

    @Test
    public void shouldGetErrorWhenStudentBorrowsNullBook() {
        assertThrows(IllegalArgumentException.class, () -> sut.borrowBook(null, dummyStudent1));
    }

    @Test
    public void shouldGetErrorWhenStudentReturnsNullBook() {
        assertThrows(IllegalArgumentException.class, () -> sut.returnBook(null, dummyStudent1));
    }

    @Test
    public void shouldGetErrorWhenStudentBorrowsMissingBook() {
        assertThrows(IllegalArgumentException.class, () -> sut.borrowBook(dummyBook, dummyStudent1));
    }

    @Test
    public void shouldGetErrorWhenStudentReturnsMissingBook() {
        assertThrows(IllegalArgumentException.class, () -> sut.borrowBook(dummyBook, dummyStudent1));
    }

    @Test
    public void shouldBeNoBooksAvailableWhenEachIsBorrowed() {
        sut.addNewBook(dummyBook);
        sut.borrowBook(dummyBook, dummyStudent1);
        assertEquals(0, sut.findAvailableBooks().size());
    }

    @Test
    public void shouldBeAvailableBooksWhenStudetReturns() {
        sut.addNewBook(dummyBook);
        sut.borrowBook(dummyBook, dummyStudent1);
        sut.returnBook(dummyBook, dummyStudent1);
        assertEquals(1, sut.findAvailableBooks().size());
    }

    @Test
    public void shouldGetErrorWhenOtherStudetReturns() {
        sut.addNewBook(dummyBook);
        sut.borrowBook(dummyBook, dummyStudent1);
        assertThrows(IllegalArgumentException.class, () -> sut.returnBook(dummyBook, dummyStudent2));
    }
}