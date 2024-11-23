import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String DB_URL = "jdbc:sqlserver://<your-server>:<port>;databaseName=LibraryManagement;";
    private static final String USER = "<your-username>";
    private static final String PASSWORD = "<your-password>";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}
