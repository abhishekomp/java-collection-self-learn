package org.example.static_nested_class.library;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        // Adding books
        library.addBook("1984", "George Orwell", "123-456-789");
        library.addBook("Brave New World", "Aldous Huxley", "234-567-890");

        // Comparing books using the static nested BookComparator class
        Library.Book book1 = new Library.Book("1984", "George Orwell", "123-456-789");
        Library.Book book2 = new Library.Book("Animal Farm", "George Orwell", "345-678-901");

        int comparisonResult = Library.BookComparator.compareByTitle(book1, book2);
        System.out.println("Comparison by title: " + (comparisonResult < 0 ? "First" : "Second") + " book is earlier in alphabetical order.");

        comparisonResult = Library.BookComparator.compareByAuthor(book1, book2);
        System.out.println("Comparison by author: " + (comparisonResult < 0 ? "First" : "Second") + " book is earlier in alphabetical order.");
    }
}
