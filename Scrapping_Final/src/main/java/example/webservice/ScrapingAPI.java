package example.webservice;

import example.model.Assessment;
import example.model.Comment;
import example.model.Location;
import example.model.Service;

import java.util.List;

import static spark.Spark.*;

//http://localhost:4567/
public class ScrapingAPI {
    public void startServer() {
    port(4567);
    staticFiles.location("/public");
    }

    private static Scraping scraping = new Scraping();

    public static void main(String[] args) {

        // Expone el método totalLocations como una petición GET
        get("/hotels/:name ", (request, response) -> {
            response.type("application/json");
            String url = "https://www.booking.com/hotel/es/acgrancanaria.es.html#tab-main";
            List<Location> locations = scraping.totalLocations(url);
            System.out.println(locations);
            return locations;
        }, new JsonTransformer());


        // Expone el método totalAssessments como una petición GET
        get("/hotels/:name/ratings", (request, response) -> {
            response.type("application/json");
            String url = "https://www.booking.com/hotel/es/acgrancanaria.es.html#tab-main";
            List<Assessment> assessments = scraping.totalAssessments(url);
            System.out.println(assessments);
            return assessments;
        }, new JsonTransformer());

        // Expone el método totalComments como una petición GET
        get("/hotels/:name/comments", (request, response) -> {
            response.type("application/json");
            String url = "https://www.booking.com/reviews/es/hotel/acgrancanaria.es.html#tab-main";
            List<Comment> comments = scraping.totalComments(url);
            System.out.println(comments);
            return comments;
        }, new JsonTransformer());

        // Expone el método totalServices como una petición GET
        get("/hotels/:name/services", (request, response) -> {
            response.type("application/json");
            String url = "https://www.booking.com/hotel/es/acgrancanaria.es.html#tab-main";
            List<Service> services = scraping.totalServices(url);
            System.out.println(services);
            return services;
        }, new JsonTransformer());
    }
}

