package it.viki.ratinginfoservice.interfaces;

public class Rating {

    private String technoId;
    private String rating;

    public Rating(String technoId, String rating) {
        this.technoId = technoId;
        this.rating = rating;
    }

    public String getTechnoId() {
        return technoId;
    }

    public void setTechnoId(String technoId) {
        this.technoId = technoId;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
