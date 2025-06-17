package com.springboot.lms.dto;

import org.springframework.stereotype.Component;

@Component
public class ReviewDTO {
    private String comment;
    private String rating;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}