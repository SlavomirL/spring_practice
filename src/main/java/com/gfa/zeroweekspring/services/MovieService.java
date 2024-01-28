package com.gfa.zeroweekspring.services;

import com.gfa.zeroweekspring.models.Genre;

import java.util.List;

public interface MovieService {

    void fetchAndStoreMovieGenres() throws Exception;

    List<Genre> showStoredGenres();
}