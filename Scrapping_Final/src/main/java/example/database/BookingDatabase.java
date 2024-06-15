package example.database;

import example.model.Assessment;
import example.model.Comment;
import example.model.Location;
import example.model.Service;

import java.sql.SQLException;

public interface BookingDatabase {
    void add(Assessment assessments) throws SQLException;
    void add(Location locations) throws SQLException;
    void add(Comment comments) throws SQLException;
    void add(Service servicies) throws SQLException;
}
