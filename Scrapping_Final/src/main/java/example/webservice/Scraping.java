package example.webservice;

import com.google.gson.JsonArray;
import example.model.Assessment;
import example.model.Comment;
import example.model.Location;
import example.model.Service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

import static spark.Spark.get;

public class Scraping implements BookingSource {

    public static Document getHtmlDocument(String url) {
        // Código para obtener el documento HTML de la página
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();
        } catch (IOException ex) {
            System.out.println("Excepción al obtener el HTML de la página" + ex.getMessage());
        }
        return doc;
    }

    @Override
    public List<Location> totalLocations(String url) {
        // Código para obtener las ubicaciones y nombres de los hoteles
        List<Location> locations = new ArrayList<>();
        Elements hotel = getHtmlDocument(url).select("div.wrap-hotelpage-top");
        for (Element elem : hotel) {
            String name = elem.getElementsByClass("d2fee87262 pp-header__title").text();
            String location = elem.getElementsByClass("hp_address_subtitle").text();

            locations.add(new Location(name, location));
        }
        return locations;
    }

    @Override
    public List<Assessment> totalAssessments(String url) {
        // Código para obtener las valoraciones
        List<Assessment> assessments = new ArrayList<>();
        Map<String, String> hm = new HashMap<String, String>();
        try {
            Elements ratings = getHtmlDocument(url).select("div.a1b3f50dcd.b2fe1a41c3.a1f3ecff04.e187349485.d19ba76520");
            for (Element elem : ratings) {
                String name = elem.getElementsByClass("d6d4671780").text();
                String rating = elem.getElementsByClass("ee746850b6 b8eef6afe1").text();
                hm.put(name, rating);
            }
            Set<String> keys = hm.keySet();
            for (String key : keys) {
                String value = hm.get(key);
                assessments.add(new Assessment(key, value));
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
        return assessments;
    }
    
    @Override//COMENTARIOS
    public List<Comment>totalComments(String url) {
        List<Comment> comments = new ArrayList<>();

        Elements commentations = getHtmlDocument(url).select("li.review_item.clearfix");
        for (Element elem: commentations) {

            JsonArray name = new JsonArray();
            JsonArray country = new JsonArray();
            Elements users = elem.select("div.review_item_reviewer");

            for (Element user : users) {
                String n = user.getElementsByClass("reviewer_name").text();
                String c = user.getElementsByClass("reviewer_country").text();
                name.add(n);
                country.add(c);
            }
            JsonArray punctuation1 = new JsonArray();
            JsonArray review = new JsonArray();

            Elements punctuations = elem.select("div.review_item_review_container.lang_ltr");
            for (Element punctuation : punctuations) {
                String p1 = punctuation.getElementsByClass("review-score-badge").text();
                String p2 = punctuation.getElementsByClass("review_item_header_content").text();
                punctuation1.add(p1);
                review.add(p2);
            }
            JsonArray positive = new JsonArray();
            JsonArray negative = new JsonArray();
            JsonArray days = new JsonArray();

            Elements annotations = elem.select("div.review_item_review_content");
            for (Element annotation : annotations) {
                String p = annotation.getElementsByClass("review_pos").text();
                String n = annotation.getElementsByClass("review_neg").text();
                String d = annotation.getElementsByClass("review_staydate").text();
                positive.add(p);
                negative.add(n);
                days.add(d);
            }
            System.out.println(name + "\n" +  country+ "\n" + punctuation1+ "\n" + review + "\n" + positive + "\n" + negative + "\n" + days);
            comments.add(new Comment(name.toString().replaceAll("[\"\\[\\]]",""),
                    country.toString().replaceAll("[\"\\[\\]]",""),
                    punctuation1.toString().replaceAll("[\"\\[\\]]",""),
                    review.toString().replaceAll("[\"\\u201C\\u201D\\[\\]]",""),
                    positive.toString().replaceAll("[\"\\[\\]]",""),
                    negative.toString().replaceAll("[\"\\[\\]]",""),
                    days.toString().replaceAll("[\"\\[\\]]","")));
        }
        return comments;
    }



    @Override//Servicios
    public List<Service>totalServices(String url) {
        List<Service> servicies = new ArrayList<>();
        Elements services = getHtmlDocument(url).select("div.hotel-facilities-group");
        for (Element elem : services) {
            String name = elem.getElementsByClass("bui-title bui-title--strong_1 bui-spacer--medium hotel-facilities-group__title").text();
            String service = elem.getElementsByClass("bui-list__description").text();

            servicies.add(new Service(name, service));

            System.out.println(name + ": " + service +"\n");
            System.out.println("----------------------------");
        }
        return servicies;
    }
}