package org.example.static_nested_class.library;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private final List<Book> books = new ArrayList<>();

    // Static nested class for Book
    public static class Book {
        private String title;
        private String author;
        private String isbn;

        public Book(String title, String author, String isbn) {
            this.title = title;
            this.author = author;
            this.isbn = isbn;
        }

        // Getter methods for Book properties
        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public String getIsbn() {
            return isbn;
        }

        @Override
        public String toString() {
            return "Book [Title: " + title + ", Author: " + author + ", ISBN: " + isbn + "]";
        }
    }

    // Static nested class for BookComparator
    public static class BookComparator {
        public static int compareByTitle(Book book1, Book book2) {
            return book1.getTitle().compareTo(book2.getTitle());
        }

        public static int compareByAuthor(Book book1, Book book2) {
            return book1.getAuthor().compareTo(book2.getAuthor());
        }
    }

    public void addBook(String title, String author, String isbn) {
        Book book = new Book(title, author, isbn);
        books.add(book);
        System.out.println("Added: " + book);
    }

    // Method to get all books
    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }
}