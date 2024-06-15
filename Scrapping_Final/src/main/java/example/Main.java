package example;

import example.database.BookingDatabase;
import example.database.SqliteBookingDatabase;
import example.model.Assessment;
import example.model.Comment;
import example.model.Location;
import example.model.Service;
import example.webservice.BookingSource;
import example.webservice.Scraping;
import example.webservice.ScrapingAPI;

import java.util.List;



public class Main {
    public static void main(String[] args) throws Exception {
        BookingSource source = new Scraping();
        BookingDatabase bookingDatabase = new SqliteBookingDatabase();

        String BookingURL = "https://www.booking.com/hotel/es/acgrancanaria.es.html#tab-main";
        String BookingURL2 = "https://www.booking.com/reviews/es/hotel/acgrancanaria.es.html#tab-main";
            updateAssessments(source, bookingDatabase, BookingURL);
            updateLocations(source, bookingDatabase, BookingURL);
            updateComments(source, bookingDatabase, BookingURL2);
            updateServicies(source, bookingDatabase, BookingURL);

        ScrapingAPI scrapingAPI = new ScrapingAPI();
        scrapingAPI.main(args);
    }


    private static void updateAssessments(BookingSource source, BookingDatabase bookingDatabase, String BookingURL) throws Exception {
        List<Assessment> assessments = source.totalAssessments(BookingURL);
        for (Assessment assessment : assessments) bookingDatabase.add(assessment);
    }

    private static void updateLocations(BookingSource source, BookingDatabase bookingDatabase, String BookingURL) throws Exception {
        List<Location> locations = source.totalLocations(BookingURL);
        for (Location location : locations) bookingDatabase.add(location);
    }

    private static void updateComments(BookingSource source, BookingDatabase bookingDatabase, String BookingURL2) throws Exception {
        List<Comment> comments = source.totalComments(BookingURL2);
        for (Comment comment : comments) bookingDatabase.add(comment);
    }


    private static void updateServicies(BookingSource source, BookingDatabase bookingDatabase, String BookingURL) throws Exception {
        List<Service> servicies = source.totalServices(BookingURL);
        for (Service servicie : servicies) bookingDatabase.add(servicie);
    }

}