package org.example.static_nested_class.library;

import java.util.List;

import static java.util.Collections.reverseOrder;

public class Main_Comparator {
    public static void main(String[] args) {
        Library library = getLibrary();

        // Sorting the books in alphabetical NATURAL order by title using the created comparator
        List<Library.Book> allBooks = library.getBooks();
        allBooks.sort(Library.BookComparator::compareByTitle);
        System.out.println("Books sorted in alphabetical order by title:");
        for (Library.Book book : allBooks) {
            System.out.println(book);
        }

        // Sorting the books in alphabetical REVERSE order by title using the created comparator
        List<Library.Book> bookList = library.getBooks();
        bookList.sort(reverseOrder(Library.BookComparator::compareByTitle));
        System.out.println("Books sorted in alphabetical REVERSE order by title:");
        for (Library.Book book : bookList) {
            System.out.println(book);
        }

        // Sorting the books in reverse order by title using lambda expression
        List<Library.Book> libraryBooks = library.getBooks();
        libraryBooks.sort((b1, b2) -> b2.getTitle().compareTo(b1.getTitle()));
        System.out.println("Books sorted in reverse order by title using lambda expression:");
        for (Library.Book book : libraryBooks) {
            System.out.println(book);
        }
    }


    private static Library getLibrary() {
        Library library = new Library();

        // Adding books
        library.addBook("1984", "George Orwell", "123-456-789");
        library.addBook("Brave New World", "Aldous Huxley", "234-567-890");
        library.addBook("Ulysses", "James Joyce", "678-901-234");
        library.addBook("Great Expectations", "Charles Dickens", "456-789-012");
        library.addBook("Animal Farm", "George Orwell", "345-678-901");
        library.addBook("Pride and Prejudice", "Jane Austen", "567-890-123");
        library.addBook("Pinocchio", "Carlo Collodi", "567-890-127");
        library.addBook("A Passage to India", "E.M. Forster", "678-901-239");
        return library;
    }
}
