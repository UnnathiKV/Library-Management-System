import java.util.ArrayList;
import java.util.Scanner;

// Book class
class Book {
    int id;
    String title;
    boolean isAvailable;

    public Book(int id, String title) {
        this.id = id;
        this.title = title;
        this.isAvailable = true;
    }

    public String toString() {
        return "Book ID: " + id + ", Title: " + title + ", Available: " + isAvailable;
    }
}

// User class
class User {
    int userId;
    String name;
    ArrayList<Book> borrowedBooks;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
        borrowedBooks = new ArrayList<>();
    }

    public String toString() {
        return "User ID: " + userId + ", Name: " + name + ", Borrowed Books: " + borrowedBooks.size();
    }
}

// Library class
class Library {
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully.");
    }

    public void addUser(User user) {
        users.add(user);
        System.out.println("User registered successfully.");
    }

    public void showBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            for (Book b : books) {
                System.out.println(b);
            }
        }
    }

    public void showUsers() {
        if (users.isEmpty()) {
            System.out.println("No users registered.");
        } else {
            for (User u : users) {
                System.out.println(u);
            }
        }
    }

    public void issueBook(int bookId, int userId) {
        Book book = null;
        User user = null;

        for (Book b : books) {
            if (b.id == bookId && b.isAvailable) {
                book = b;
                break;
            }
        }

        for (User u : users) {
            if (u.userId == userId) {
                user = u;
                break;
            }
        }

        if (book != null && user != null) {
            book.isAvailable = false;
            user.borrowedBooks.add(book);
            System.out.println("Book issued to user.");
        } else {
            System.out.println("Book not available or user not found.");
        }
    }

    public void returnBook(int bookId, int userId) {
        for (User u : users) {
            if (u.userId == userId) {
                for (Book b : u.borrowedBooks) {
                    if (b.id == bookId) {
                        b.isAvailable = true;
                        u.borrowedBooks.remove(b);
                        System.out.println("Book returned successfully.");
                        return;
                    }
                }
            }
        }
        System.out.println("Book or user not found.");
    }
}

// Main class
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Library Management Menu ===");
            System.out.println("1. Add Book");
            System.out.println("2. Register User");
            System.out.println("3. Show All Books");
            System.out.println("4. Show All Users");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int bid = sc.nextInt();
                    sc.nextLine(); // clear buffer
                    System.out.print("Enter Book Title: ");
                    String title = sc.nextLine();
                    library.addBook(new Book(bid, title));
                    break;

                case 2:
                    System.out.print("Enter User ID: ");
                    int uid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter User Name: ");
                    String name = sc.nextLine();
                    library.addUser(new User(uid, name));
                    break;

                case 3:
                    library.showBooks();
                    break;

                case 4:
                    library.showUsers();
                    break;

                case 5:
                    System.out.print("Enter Book ID to Issue: ");
                    int issueBookId = sc.nextInt();
                    System.out.print("Enter User ID: ");
                    int issueUserId = sc.nextInt();
                    library.issueBook(issueBookId, issueUserId);
                    break;

                case 6:
                    System.out.print("Enter Book ID to Return: ");
                    int returnBookId = sc.nextInt();
                    System.out.print("Enter User ID: ");
                    int returnUserId = sc.nextInt();
                    library.returnBook(returnBookId, returnUserId);
                    break;

                case 7:
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);

        sc.close();
    }
}
