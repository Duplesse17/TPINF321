package com.example.recherchetonfilm.service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.recherchetonfilm.model.Movie;
import com.example.recherchetonfilm.model.Serie;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Service
public class TMDbService {

    private static final String API_KEY = "bf101a968cd9978f99076f4b4116311d"; // Remplace par ta propre clé API
    private static final String BASE_URL = "https://api.themoviedb.org/3/search/movie";
    private static final String BASE_URL_TV = "https://api.themoviedb.org/3";

    private final RestTemplate restTemplate;

    /**
     * Recherche des films sur TMDb en fonction des titres fournis.
     * 
     * @param titles Liste des titres de films à rechercher.
     * @return Une liste de films avec leurs informations.
     */

    @Autowired
    public TMDbService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Movie> searchMoviesByTitles(String title) {
        List<Movie> movies = new ArrayList<>();

        try {
            title = URLEncoder.encode(title, StandardCharsets.UTF_8.toString());

            // Construction de l'URL avec le titre du film
            String url = BASE_URL + "?api_key=" + API_KEY + "&query=" + title;

            // Envoi de la requête HTTP
            String response = restTemplate.getForObject(url, String.class);
            //System.out.println(response);
            // Traitement de la réponse JSON
            JsonObject responseObject = new Gson().fromJson(response, JsonObject.class);
            JsonArray results = responseObject.getAsJsonArray("results");

            if (results.size() > 0) {
                //System.err.println("@@@@@@@");

                // Parcourir les 5 premiers résultats, ou moins si la liste a moins de 5
                // éléments
                for (int i = 0; i < Math.min(20, results.size()); i++) {
                    JsonObject movieJson = results.get(i).getAsJsonObject();

                    // Extraction des nouveaux éléments avec vérification
                    String movieTitle = movieJson.has("title") && !movieJson.get("title").isJsonNull()
                            ? movieJson.get("title").getAsString()
                            : "Titre inconnu";

                    String releaseDate = movieJson.has("release_date") && !movieJson.get("release_date").isJsonNull()
                            ? movieJson.get("release_date").getAsString()
                            : "Date inconnue";

                    String overview = movieJson.has("overview") && !movieJson.get("overview").isJsonNull()
                            ? movieJson.get("overview").getAsString()
                            : "Résumé non disponible";
                    String posterPath = movieJson.has("poster_path") && !movieJson.get("poster_path").isJsonNull()
                            ? movieJson.get("poster_path").getAsString()
                            : "";
                    String imageUrl = "https://image.tmdb.org/t/p/w500" + posterPath;

                    boolean adult = movieJson.has("adult") ? movieJson.get("adult").getAsBoolean() : false;
                    List<Integer> genreIds = new ArrayList<>();
                    if (movieJson.has("genre_ids")) {
                        JsonArray genres = movieJson.getAsJsonArray("genre_ids");
                        for (int j = 0; j < genres.size(); j++) {
                            genreIds.add(genres.get(j).getAsInt());
                        }
                    }
                    int id = movieJson.has("id") && !movieJson.get("id").isJsonNull()
                            ? movieJson.get("id").getAsInt()
                            : -1; // Valeur par défaut pour l'ID

                    String originalLanguage = movieJson.has("original_language")
                            && !movieJson.get("original_language").isJsonNull()
                                    ? movieJson.get("original_language").getAsString()
                                    : "Langue inconnue";

                    String originalTitle = movieJson.has("original_title")
                            && !movieJson.get("original_title").isJsonNull()
                                    ? movieJson.get("original_title").getAsString()
                                    : "Titre original inconnu";

                    double voteAverage = movieJson.has("vote_average") && !movieJson.get("vote_average").isJsonNull()
                            ? movieJson.get("vote_average").getAsDouble()
                            : 0.0; // Valeur par défaut pour la moyenne des votes

                    double popularity = movieJson.has("popularity") && !movieJson.get("popularity").isJsonNull()
                            ? movieJson.get("popularity").getAsDouble()
                            : 0.0; // Valeur par défaut pour la popularité

                    int voteCount = movieJson.has("vote_count") && !movieJson.get("vote_count").isJsonNull()
                            ? movieJson.get("vote_count").getAsInt()
                            : 0; // Valeur par défaut pour le nombre de votes

                    // Créer un objet Movie avec les nouveaux paramètres
                    Movie movie = new Movie();
                    movie.setPopularity(popularity);
                    movie.setVote_count(voteCount);
                    movie.setTitle(movieTitle);
                    movie.setReleaseDate(releaseDate);
                    movie.setOverview(overview);
                    movie.setPosterPath(posterPath);
                    movie.setImageUrl(imageUrl);
                    movie.setAdult(adult);
                    movie.setGenreIds(genreIds);
                    movie.setId(id);
                    movie.setOriginalLanguage(originalLanguage);
                    movie.setOriginalTitle(originalTitle);
                    movie.setVoteAverage(voteAverage);

                    // Ajouter à la liste des films
                    movies.add(movie);
                }
            }

        } catch (Exception e) {
            System.err.println("erreur");
            e.printStackTrace(); // Gestion des erreurs si nécessaire
        }

        return movies;
    }

    public List<Serie> searchSeriesByTitles(String title) {
        List<Serie> seriesList = new ArrayList<>();

        try {
            title = URLEncoder.encode(title, StandardCharsets.UTF_8.toString());
            System.err.println("Recherche de la série : " + title);

            // Construction de l'URL avec le titre de la série
            String url = BASE_URL_TV + "/search/tv?api_key=" + API_KEY + "&query=" + title;

            // Envoi de la requête HTTP
            String response = restTemplate.getForObject(url, String.class);

            // Traitement de la réponse JSON
            JsonObject responseObject = new Gson().fromJson(response, JsonObject.class);
            JsonArray results = responseObject.getAsJsonArray("results");

            if (results != null && results.size() > 0) {
                System.err.println("Séries trouvées pour le titre : " + title);

                // Parcourir les 5 premiers résultats ou moins si la liste est plus courte
                for (int i = 0; i < Math.min(5, results.size()); i++) {
                    JsonObject seriesJson = results.get(i).getAsJsonObject();

                    // Extraction des informations nécessaires
                    String backdropPath = seriesJson.has("backdrop_path")
                            ? "https://image.tmdb.org/t/p/w500" + seriesJson.get("backdrop_path").getAsString()
                            : null;
                    String firstAirDate = seriesJson.has("first_air_date")
                            ? seriesJson.get("first_air_date").getAsString()
                            : "Date inconnue";
                    List<Integer> genreIds = new ArrayList<>();
                    if (seriesJson.has("genre_ids")) {
                        JsonArray genreIdsArray = seriesJson.getAsJsonArray("genre_ids");
                        for (int j = 0; j < genreIdsArray.size(); j++) {
                            genreIds.add(genreIdsArray.get(j).getAsInt());
                        }
                    }
                    int id = seriesJson.has("id") ? seriesJson.get("id").getAsInt() : 0;
                    String name = seriesJson.has("name") ? seriesJson.get("name").getAsString() : "Non spécifié";
                    List<String> originCountry = new ArrayList<>();
                    if (seriesJson.has("origin_country")) {
                        JsonArray originCountryArray = seriesJson.getAsJsonArray("origin_country");
                        for (int j = 0; j < originCountryArray.size(); j++) {
                            originCountry.add(originCountryArray.get(j).getAsString());
                        }
                    }
                    String originalLanguage = seriesJson.has("original_language")
                            ? seriesJson.get("original_language").getAsString()
                            : "Langue inconnue";
                    String originalName = seriesJson.has("original_name")
                            ? seriesJson.get("original_name").getAsString()
                            : "Non spécifié";
                    String overview = seriesJson.has("overview")
                            ? seriesJson.get("overview").getAsString()
                            : "Pas de résumé disponible";
                    double popularity = seriesJson.has("popularity")
                            ? seriesJson.get("popularity").getAsDouble()
                            : 0.0;
                    String posterPath = seriesJson.has("poster_path")
                            ? "https://image.tmdb.org/t/p/w500" + seriesJson.get("poster_path").getAsString()
                            : null;
                    double voteAverage = seriesJson.has("vote_average")
                            ? seriesJson.get("vote_average").getAsDouble()
                            : 0.0;
                    int voteCount = seriesJson.has("vote_count")
                            ? seriesJson.get("vote_count").getAsInt()
                            : 0;

                    // Création d'un objet Series
                    Serie series = new Serie(backdropPath, firstAirDate, genreIds, id, name,
                            originCountry, originalLanguage, originalName,
                            overview, popularity, posterPath, voteAverage, voteCount);
                    seriesList.add(series);
                }
            } else {
                System.err.println("Aucune série trouvée pour le titre : " + title);
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la recherche de la série : " + title);
            e.printStackTrace();
        }

        return seriesList;
    }

    // fonction pour recuperer les bande d'anonce sur tmbd
    @SuppressWarnings("unchecked")
    public String getTrailerUrl(int id, String type) {
        // Construire l'URL en fonction du type (film ou série)
        String url = "https://api.themoviedb.org/3/" + type + "/" + id + "/videos?api_key=" + API_KEY;

        // Appel API avec RestTemplate
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        List<Map<String, String>> results = (List<Map<String, String>>) response.get("results");
        //System.err.println(response);
        // Parcourir les résultats pour trouver un clip, un teaser, ou un trailer
        for (Map<String, String> video : results) {
            String videoType = video.get("type");
            String videoSite = video.get("site");
            String videoKey = video.get("key");

            // Vérifier si la vidéo est sur YouTube
            if ("YouTube".equals(videoSite)) {
                // Priorité : Clip, puis Teaser, puis Trailer
                if ("Clip".equals(videoType)) {
                    return "https://www.youtube.com/watch?v=" + video.get("key");
                } else if ("Teaser".equals(videoType)) {
                    return "https://www.youtube.com/watch?v=" + video.get("key");
                } else if ("Trailer".equals(videoType)) {
                    return "https://www.youtube.com/watch?v=" + video.get("key");
                }
            } else if ("Vimeo".equals(videoSite)) {
                // Priorité : Clip, puis Teaser, puis Trailer
                if ("Clip".equals(videoType)) {
                    return "https://www.youtube.com/watch?v=" + video.get("key");
                } else if ("Teaser".equals(videoType)) {
                    return "https://www.youtube.com/watch?v=" + video.get("key");
                } else if ("Trailer".equals(videoType)) {
                    return "https://www.youtube.com/watch?v=" + video.get("key");
                }
            }

            // Si la vidéo n'est pas sur YouTube, on peut vérifier d'autres sites
            // Ici, tu pourrais traiter les autres cas, ou renvoyer un lien de base si
            // souhaité.
            else {
                // Si tu veux un comportement spécifique pour les vidéos non-YouTube (par
                // exemple, renvoyer un autre site)
                // Tu peux adapter cette section. Par exemple :
                // Priorité : Clip > Teaser > Trailer
                if ("Clip".equals(videoType)) {
                    return "https://www.youtube.com/watch?v=" + videoKey;
                } else if ("Teaser".equals(videoType)) {
                    return "https://www.youtube.com/watch?v=" + videoKey;
                } else if ("Trailer".equals(videoType)) {
                    return "https://www.youtube.com/watch?v=" + videoKey;
                }
                return "https://www." + videoSite + ".com/watch?v=" + video.get("key");
            }
        }

        // Si aucune vidéo (clip, teaser, ou trailer) n'est trouvée
        return "https://www.youtube.com/watch?v=tgkXpowex-g";
    }

}
