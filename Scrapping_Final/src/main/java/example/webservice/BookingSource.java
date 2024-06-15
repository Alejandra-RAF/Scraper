package example.webservice;

import example.model.Assessment;
import example.model.Comment;
import example.model.Location;
import example.model.Service;

import java.util.List;

public interface BookingSource {
        List<Assessment> totalAssessments(String url) throws Exception;
        List<Location> totalLocations(String url) throws Exception;
        List<Comment> totalComments(String url) throws Exception;
        List<Service> totalServices(String url) throws Exception;

}
