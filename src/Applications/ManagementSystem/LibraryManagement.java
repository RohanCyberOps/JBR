import java.util.*;

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
    }

    public void displayInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Status: " + (isAvailable ? "Available" : "Borrowed"));
    }

    // Getters and Setters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
}

public class LibraryManagement {
    private static List<Book> books = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Add some sample books
        books.add(new Book("Java Programming", "John Doe", "1234567890"));
        books.add(new Book("Data Structures", "Jane Smith", "0987654321"));

        while (true) {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: addBook(); break;
                case 2: viewAllBooks(); break;
                case 3: searchBook(); break;
                case 4: borrowBook(); break;
                case 5: returnBook(); break;
                case 6: System.out.println("Goodbye!"); return;
                default: System.out.println("Invalid option!");
            }
        }
    }

    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        books.add(new Book(title, author, isbn));
        System.out.println("Book added successfully!");
    }

    private static void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available!");
            return;
        }

        System.out.println("\n=== All Books ===");
        for (int i = 0; i < books.size(); i++) {
            System.out.println("\nBook " + (i + 1) + ":");
            books.get(i).displayInfo();
        }
    }

    private static void searchBook() {
        System.out.print("Enter title to search: ");
        String searchTitle = scanner.nextLine().toLowerCase();

        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(searchTitle)) {
                book.displayInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found with that title!");
        }
    }

    private static void borrowBook() {
        System.out.print("Enter ISBN of book to borrow: ");
        String isbn = scanner.nextLine();

        Book book = findBook(isbn);
        if (book == null) {
            System.out.println("Book not found!");
        } else if (!book.isAvailable()) {
            System.out.println("Book is already borrowed!");
        } else {
            book.setAvailable(false);
            System.out.println("Book borrowed successfully!");
        }
    }

    private static void returnBook() {
        System.out.print("Enter ISBN of book to return: ");
        String isbn = scanner.nextLine();

        Book book = findBook(isbn);
        if (book == null) {
            System.out.println("Book not found!");
        } else if (book.isAvailable()) {
            System.out.println("Book is already available!");
        } else {
            book.setAvailable(true);
            System.out.println("Book returned successfully!");
        }
    }

    private static Book findBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }
}