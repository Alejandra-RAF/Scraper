package example.database;

import example.model.Assessment;
import example.model.Comment;
import example.model.Location;
import example.model.Service;
public class DMLTranslater {
    private static final String INSERT_ASSESSMENT =
            "INSERT INTO assessments(name, rating) VALUES('%s', '%s')";
    public static String insertStatementOf(Assessment assessment){
        return String.format(INSERT_ASSESSMENT,
                assessment.name,
                assessment.rating);
    }



    private static final String INSERT_LOCATION =
            "INSERT INTO locations(name, location) VALUES('%s', '%s')";
    public static String insertStatementOf(Location location) {
        return String.format(INSERT_LOCATION,
                location.name,
                location.location);
    }



    private static final String INSERT_COMMENT =
            "INSERT INTO comments(name, country, punctuation1, review, positive, negative, days) VALUES('%s', '%s','%s', '%s', '%s', '%s', '%s')";
    public static String insertStatementOf(Comment comment) {
        return String.format(INSERT_COMMENT,
                comment.name,
                comment.country,
                comment.punctuation1,
                comment.review,
                comment.positive,
                comment.negative,
                comment.days);
    }


    private static final String INSERT_SERVICIE =
            "INSERT INTO servicies(name, service) VALUES('%s', '%s')";
    public static String insertStatementOf(Service servicie) {
        return String.format(INSERT_SERVICIE,
                servicie.name,
                servicie.service);
    }
}

