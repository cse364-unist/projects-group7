package com.example;

import lombok.Data;

@Data
public class Movie {
    private String title;
    private String genre;
	private String season;
	public String rating;
    Movie() {}

	public Movie(String season, String title, String genre, String rating) {

		this.season = season;
	    this.title = title;
        this.genre = genre;
		this.rating = rating;
	}

    public void setseason(String season) {
		this.season = season;
	}

    public void settitle(String title) {
		this.title = title;
	}

    public void setgenre(String genre) {
		this.genre = genre;
	}

	public void setrating(String rating) {
		this.rating = rating;
	}

	public String getGenre() {
		return this.genre;
	}

	public String getSeason() {
		return this.season;
	}

	public String getRating() {
		return this.rating;
	}
}

