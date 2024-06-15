package example.database;

import example.model.Assessment;
import example.model.Comment;
import example.model.Location;
import example.model.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteBookingDatabase implements BookingDatabase{

    private final Connection connection;

    public SqliteBookingDatabase() throws SQLException {
        String url = "jdbc:sqlite:DataBase.db";
        connection = DriverManager.getConnection(url);
        initDatabase();
    }

    private static final String TABLAASSESSMENTS =
            "CREATE TABLE IF NOT EXISTS assessments(" +
                    "name TEXT, " +
                    "rating TEXT);";


    private static final String TABLALOCATIONS =
            "CREATE TABLE IF NOT EXISTS locations(" +
                    "name TEXT, " +
                    "location TEXT);";


    private static final String TABLACOMMENTS =
            "CREATE TABLE IF NOT EXISTS comments(" +
                    "name TEXT, " +
                    "country TEXT, " +
                    "punctuation1 TEXT," +
                    "review TEXT," +
                    "positive TEXT," +
                    "negative TEXT," +
                    "days TEXT);";


    private static final String TABLASERVICIES =
            "CREATE TABLE IF NOT EXISTS servicies(" +
                    "name TEXT, " +
                    "service TEXT);";
    private void initDatabase() throws SQLException{
        connection.createStatement().execute(TABLAASSESSMENTS);
        connection.createStatement().execute(TABLALOCATIONS);
        connection.createStatement().execute(TABLACOMMENTS);
        connection.createStatement().execute(TABLASERVICIES);
    }

    @Override
    public void add(Assessment assessments){
        try{
            connection.createStatement().execute(DMLTranslater.insertStatementOf(assessments));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void add(Location locations) {
        try {
            connection.createStatement().execute(DMLTranslater.insertStatementOf(locations));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void add(Comment comments) {
        try {
            connection.createStatement().execute(DMLTranslater.insertStatementOf(comments));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void add(Service servicies) {
        try {
            connection.createStatement().execute(DMLTranslater.insertStatementOf(servicies));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

