import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LibraryService {

    // Add a book to the database
    public static void addBook(String isbn, String title, String author, String genre) {
        String sql = "INSERT INTO Books (ISBN, Title, Author, Genre, AvailabilityStatus) VALUES (?, ?, ?, ?, 'Available')";
        
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, isbn);
            stmt.setString(2, title);
            stmt.setString(3, author);
            stmt.setString(4, genre);
            
            stmt.executeUpdate();
            System.out.println("Book added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
    }

    // List all books in the database
    public static void listBooks() {
        String sql = "SELECT * FROM Books";
        
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                System.out.println("Book ID: " + rs.getInt("BookID"));
                System.out.println("ISBN: " + rs.getString("ISBN"));
                System.out.println("Title: " + rs.getString("Title"));
                System.out.println("Author: " + rs.getString("Author"));
                System.out.println("Genre: " + rs.getString("Genre"));
                System.out.println("Status: " + rs.getString("AvailabilityStatus"));
                System.out.println("--------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error listing books: " + e.getMessage());
        }
    }

    // Borrow a book by its ID
    public static void borrowBook(int bookID) {
        String checkSql = "SELECT AvailabilityStatus FROM Books WHERE BookID = ?";
        String updateSql = "UPDATE Books SET AvailabilityStatus = 'Borrowed' WHERE BookID = ?";
        
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql);
             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
            
            checkStmt.setInt(1, bookID);
            ResultSet rs = checkStmt.executeQuery();
            
            if (rs.next() && rs.getString("AvailabilityStatus").equalsIgnoreCase("Available")) {
                updateStmt.setInt(1, bookID);
                updateStmt.executeUpdate();
                System.out.println("Book borrowed successfully!");
            } else {
                System.out.println("Book is not available for borrowing.");
            }
        } catch (SQLException e) {
            System.out.println("Error borrowing book: " + e.getMessage());
        }
    }

    // Return a book by its ID
    public static void returnBook(int bookID) {
        String sql = "UPDATE Books SET AvailabilityStatus = 'Available' WHERE BookID = ?";
        
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, bookID);
            int rowsUpdated = stmt.executeUpdate();
            
            if (rowsUpdated > 0) {
                System.out.println("Book returned successfully!");
            } else {
                System.out.println("Book ID not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error returning book: " + e.getMessage());
        }
    }
}
