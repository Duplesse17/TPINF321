package com.example.recherchetonfilm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.recherchetonfilm.model.Movie;
import com.example.recherchetonfilm.model.Serie;

import com.example.recherchetonfilm.service.TMDbService;

@Controller
@RequestMapping("/movies")
public class MovieController {


    @Autowired
    private TMDbService tmdbService;

    @GetMapping("/")
    public String showHomePage() {
        return "index"; // Page d'accueil pour effectuer une recherche
    }

    @PostMapping("/search")
    public String searchMovies(@RequestParam("query") String query, Model model) {
        try {
            List<Movie> movies = tmdbService.searchMoviesByTitles(query);

            // 3. Ajouter les URLs des bandes-annonces pour chaque film
            for (Movie movie : movies) {
                //System.err.println(movie.getId());
                String trailerUrl = tmdbService.getTrailerUrl(movie.getId(), "movie");
                movie.setTrailerUrl(trailerUrl); // Assure-toi que la classe `Movie` a un champ `trailerUrl`
                //System.out.println("Films avec bandes-annonces : " + movie.getTrailerUrl());

            }
            
            // 4. Ajouter les résultats au modèle
            model.addAttribute("movies", movies);
        } catch (Exception e) {
            System.err.println("Erreur lors de la recherche : " + e.getMessage());
            model.addAttribute("error", "Une erreur s'est produite lors de la recherche des films.");
        }

        return "searchResults"; // Vue contenant les résultats pour les films
    }

    @PostMapping("/series/search")
    public String searchSeries(@RequestParam("query") String query, Model model) {
        try {
            

            // 2. Rechercher des séries via TMDb pour chaque titre
            List<Serie> series = tmdbService.searchSeriesByTitles(query);

            // 3. Ajouter les URLs des bandes-annonces pour chaque série
            for (Serie serie : series) {
                String trailerUrl = tmdbService.getTrailerUrl(serie.getId(), "tv");
                serie.setTrailerUrl(trailerUrl); // Assure-toi que la classe `Serie` a un champ `trailerUrl`
            }
            System.out.println("Séries avec bandes-annonces : " + series);

            // 4. Ajouter les résultats au modèle
            model.addAttribute("series", series);
        } catch (Exception e) {
            System.err.println("Erreur lors de la recherche : " + e.getMessage());
            model.addAttribute("error", "Une erreur s'est produite lors de la recherche des séries.");
        }

        return "searchResultsSerie"; // Vue contenant les résultats pour les séries
    }

}
