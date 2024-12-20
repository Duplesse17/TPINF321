package com.example.recherchetonfilm.model;

import java.util.List;

public class Serie {
    private String backdropPath;
    private String firstAirDate;
    private List<Integer> genreIds;
    private int id;
    private String name;
    private List<String> originCountry;
    private String originalLanguage;
    private String originalName;
    private String overview;
    private double popularity;
    private String imageUrl;
    private double voteAverage;
    private int voteCount;
    private String trailerUrl; // Nouveau champ pour la bande-annonce

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public Serie(String backdropPath, String firstAirDate, List<Integer> genreIds, int id, String name,
            List<String> originCountry, String originalLanguage, String originalName, String overview,
            double popularity, String imageUrl, double voteAverage, int voteCount) {
        this.backdropPath = backdropPath;
        this.firstAirDate = firstAirDate;
        this.genreIds = genreIds;
        this.id = id;
        this.name = name;
        this.originCountry = originCountry;
        this.originalLanguage = originalLanguage;
        this.originalName = originalName;
        this.overview = overview;
        this.popularity = popularity;
        this.imageUrl = imageUrl;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    // Constructeur
    public Serie(String backdropPath, String firstAirDate, List<Integer> genreIds, int id, String name,
                List<String> originCountry, String originalLanguage, String originalName, String overview,
                double popularity, String posterPath, double voteAverage, int voteCount, String trailerUrl) {
        this.backdropPath = backdropPath;
        this.firstAirDate = firstAirDate;
        this.genreIds = genreIds;
        this.id = id;
        this.name = name;
        this.originCountry = originCountry;
        this.originalLanguage = originalLanguage;
        this.originalName = originalName;
        this.overview = overview;
        this.popularity = popularity;
        this.imageUrl = posterPath;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.trailerUrl = trailerUrl;
    }

    // Getters et setters
    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public Serie(String backdropPath, String firstAirDate, List<Integer> genreIds, int id, String name,
            List<String> originCountry, String originalLanguage, String originalName, String overview,
            double popularity, double voteAverage) {
        this.backdropPath = backdropPath;
        this.firstAirDate = firstAirDate;
        this.genreIds = genreIds;
        this.id = id;
        this.name = name;
        this.originCountry = originCountry;
        this.originalLanguage = originalLanguage;
        this.originalName = originalName;
        this.overview = overview;
        this.popularity = popularity;
        this.voteAverage = voteAverage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(List<String> originCountry) {
        this.originCountry = originCountry;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getimageUrl() {
        return imageUrl;
    }

    public void setimageUrl(String posterPath) {
        this.imageUrl = posterPath;
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

    @Override
    public String toString() {
        return "Serie{" +
                "name='" + name + '\'' +
                ", firstAirDate='" + firstAirDate + '\'' +
                ", overview='" + overview + '\'' +
                ", voteAverage=" + voteAverage +
                '}';
    }
}

