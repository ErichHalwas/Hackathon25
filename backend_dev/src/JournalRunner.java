import java.sql.*;
import journal.program.*;

public class JournalRunner {
    public static void main(String[] args) throws SQLException {
        Journal journal = new Journal();
        journal.createConnection();
        System.out.println("Connection created");
    }
}
