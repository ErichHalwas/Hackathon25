import java.sql.*;
import journal.program.*;

public class JournalRunner {
    public static void main(String[] args) throws SQLException {
        DatabaseConnection db = new DatabaseConnection();
        Journal journal = new Journal(db);
        journal.runProgram();
    }
}
