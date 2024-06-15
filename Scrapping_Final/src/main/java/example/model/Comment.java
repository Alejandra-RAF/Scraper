package example.model;

public class Comment {
    public String name;
    public String country;
    public String punctuation1;
    public String review;
    public String positive;
    public String negative;
    public String days;



    public Comment(String name, String country, String punctuation1, String review, String positive, String negative, String days) {
        this.name = name;
        this.country = country;
        this.punctuation1 = punctuation1;
        this.review = review;
        this.positive = positive;
        this.negative = negative;
        this.days = days;
    }
}
