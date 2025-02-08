package journal.program;

import java.sql.SQLException;

import journal.program.DatabaseConnection;

public class Journal{
    private final DatabaseConnection db;

    public Journal(DatabaseConnection db) {
        this.db = db;
    }   

    public void runProgram() throws SQLException {
        db.createConnection();
        db.createTable();
    }
}