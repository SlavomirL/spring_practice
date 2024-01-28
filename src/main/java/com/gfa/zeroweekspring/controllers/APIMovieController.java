package com.gfa.zeroweekspring.controllers;

import com.gfa.zeroweekspring.models.Genre;
import com.gfa.zeroweekspring.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class APIMovieController {

    private final MovieService movieService;

    @Autowired
    public APIMovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/genres")
    public String getAndSaveMovieGenres(Authentication authentication) {

        try {
            movieService.fetchAndStoreMovieGenres();
        } catch (Exception e) {
            return e.getMessage();
        }
        return "endpoint genres is run";
    }

    @GetMapping("/list-genres")
    public ResponseEntity<?> showGenres(Authentication authentication) {
        List<Genre> movieGenres = movieService.showStoredGenres();

        if (movieGenres.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            System.out.println(authentication.getName());
            return ResponseEntity.ok(movieGenres);
        }
    }
}