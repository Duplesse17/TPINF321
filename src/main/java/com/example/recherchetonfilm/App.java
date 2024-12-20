package com.example.recherchetonfilm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class App {
    public static void main(String[] args) {
        // Initialisation du service TMDb
        //TMDbService tmdbService = new TMDbService();
        //MovieController moviecontroller = new MovieController();
        //try {
        //    // Recherche de films avec un terme et des mots-clés
        //    String query = "vatican";  // Terme de recherche
        //    List<String> keywords = List.of("Spider-man","Tony");
//
        //    try {
        //        // Recherche des films avec la méthode searchMovies
        //        List<String> movieTitles = tmdbService.searchMovies(query, keywords);
        //        System.out.println("Films trouvés : " + movieTitles);
        //    } catch (Exception e) {
        //        // En cas d'erreur lors de la recherche des films
        //        System.out.println("Erreur lors de la recherche des films : " + e.getMessage());
        //    }
//
        //} catch (Exception e) {
        //    // En cas d'erreur lors de l'initialisation ou autre
        //    System.err.println("Une erreur s'est produite dans le programme : " + e.getMessage());
        //}

        SpringApplication.run(App.class, args);  // Démarre l'application Spring Boot

    }
}
