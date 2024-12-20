package com.example.recherchetonfilm.model;

public class Actor {
    private int id;            // ID unique de l'acteur (provenant de TMDB)
    private String name;       // Nom de l'acteur
    private String biography;  // Biographie de l'acteur
    private String profilePath; // URL de la photo de profil
    private String birthDate;  // Date de naissance
    private String placeOfBirth; // Lieu de naissance
    private boolean isAlive;   // Indique si l'acteur est encore en vie

    // Constructeur par défaut
    public Actor() {}

    // Constructeur avec tous les champs
    public Actor(int id, String name, String biography, String profilePath, String birthDate, String placeOfBirth, boolean isAlive) {
        this.id = id;
        this.name = name;
        this.biography = biography;
        this.profilePath = profilePath;
        this.birthDate = birthDate;
        this.placeOfBirth = placeOfBirth;
        this.isAlive = isAlive;
    }

    // Getters et Setters
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

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    // Méthode pour obtenir une URL complète pour la photo de profil
    public String getFullProfileUrl() {
        return "https://image.tmdb.org/t/p/w500" + this.profilePath;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", biography='" + biography + '\'' +
                ", profilePath='" + profilePath + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", isAlive=" + isAlive +
                '}';
    }
}
