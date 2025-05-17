package org.example.static_nested_class.library;

public class Main {
    public static void main(String[] args) {
        Library library = getLibrary();

        // Comparing books using the static nested BookComparator class
        Library.Book book1 = new Library.Book("1984", "George Orwell", "123-456-789");
        Library.Book book2 = new Library.Book("Animal Farm", "George Orwell", "345-678-901");

        int comparisonResult = Library.BookComparator.compareByTitle(book1, book2);
        System.out.println("Comparison by title: " + (comparisonResult < 0 ? "First" : "Second") + " book is earlier in alphabetical order.");

        comparisonResult = Library.BookComparator.compareByAuthor(book1, book2);
        System.out.println("Comparison by author: " + (comparisonResult < 0 ? "First" : "Second") + " book is earlier in alphabetical order.");

        // Displaying all books in the library
        System.out.println("Books in the library:");
        for (Library.Book book : library.getBooks()) {
            System.out.println(book);
        }
    }

    private static Library getLibrary() {
        Library library = new Library();

        // Adding books
        library.addBook("1984", "George Orwell", "123-456-789");
        library.addBook("Brave New World", "Aldous Huxley", "234-567-890");
        library.addBook("Ulysses", "James Joyce", "678-901-234");
        library.addBook("Animal Farm", "George Orwell", "345-678-901");
        library.addBook("Pride and Prejudice", "Jane Austen", "567-890-123");
        library.addBook("Pinocchio", "Carlo Collodi", "567-890-127");
        library.addBook("A Passage to India", "E.M. Forster", "678-901-239");
        return library;
    }
}

        //library.addBook("The Lord of the Rings", "J.R.R.Tolkien", "678-901-234");
        //library.addBook("The Hobbit", "JRR Tolkien", "890-123-456");
        //library.addBook("To Kill a Mockingbird", "Harper Lee", "456-789-012");
        //library.addBook("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", "901-234-567");
        //library.addBook("The Adventures of Huckleberry Finn", "Mark Twain", "012-345-678");
        //library.addBook("The Little Prince", "Antoine de Saint-Exupery", "789-012-345");
        //library.addBook("Fahrenheit 451", "Ray Bradbury", "345-678-901");
