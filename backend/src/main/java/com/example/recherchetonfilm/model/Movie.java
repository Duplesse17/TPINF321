package com.example.recherchetonfilm.model;

import java.util.List;

public class Movie {
    private boolean adult = false;
    private String imageUrl = "";
    private List<Integer> genreIds;
    private int id = 0;
    private String originalLanguage = "";
    private String originalTitle = "";
    private String overview = "";
    private String releaseDate = "";
    private String posterPath = "";
    private String title = "";
    private double voteAverage = 0.0;
    private String trailerUrl; // Nouveau champ pour la bande-annonce
    private double popularity;
    private int vote_count;
    public int getVote_count() {
        return vote_count;
    }
    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }
    public double getPopularity() {
        return popularity;
    }
    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }
    private int voteCount;

    // constructeur
    // Constructeur
    public Movie(){
        
    }
    public Movie(boolean adult, String backdropPath, List<Integer> genreIds, int id, String originalLanguage,
            String originalTitle,
            String overview, String releaseDate, String posterPath, String title, double voteAverage, int voteCount,
            String trailerUrl) {
        this.adult = adult;
        this.imageUrl = backdropPath;
        this.genreIds = genreIds;
        this.id = id;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
        this.title = title;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.trailerUrl = trailerUrl;
    }

    // Constructeurs, getters et setters
    public Movie(String title, String releaseDate, String overview, String imageUrl) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public boolean isAdult() {
        return adult;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public int getId() {
        return id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
// Getters et setters
    public String getTrailerUrl() {return trailerUrl;}
    public void setTrailerUrl(String trailerUrl) {this.trailerUrl = trailerUrl; }
}