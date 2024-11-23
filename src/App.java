import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Library Management System");
            System.out.println("1. Add Book");
            System.out.println("2. List Books");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Genre: ");
                    String genre = scanner.nextLine();
                    
                    LibraryService.addBook(isbn, title, author, genre);
                    break;
                case 2:
                    LibraryService.listBooks();
                    break;
                case 3:
                    System.out.print("Enter Book ID to borrow: ");
                    int borrowBookID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    
                    LibraryService.borrowBook(borrowBookID);
                    break;
                case 4:
                    System.out.print("Enter Book ID to return: ");
                    int returnBookID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    
                    LibraryService.returnBook(returnBookID);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
