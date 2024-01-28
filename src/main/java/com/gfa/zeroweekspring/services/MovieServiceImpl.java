package com.gfa.zeroweekspring.services;


import com.gfa.zeroweekspring.dto.GenreDataDto;
import com.gfa.zeroweekspring.dto.GenreDto;
import com.gfa.zeroweekspring.integration.MovieApi;
import com.gfa.zeroweekspring.models.Genre;
import com.gfa.zeroweekspring.repositories.GenreRepository;
import com.gfa.zeroweekspring.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final MovieApi movieApi;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, GenreRepository genreRepository, MovieApi movieApi) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.movieApi = movieApi;
    }

    @Override
    public void fetchAndStoreMovieGenres() throws Exception {
        Call<GenreDto> call = movieApi.getMoviesGenres();

        Response<GenreDto> response = call.execute();

        if (response.isSuccessful()) {
            GenreDto genreDTO = response.body();
            List<GenreDataDto> genreDataDtoList = genreDTO.getGenres();

            for (GenreDataDto genreDataDto : genreDataDtoList) {
                Genre genre = new Genre();
                genre.setId(genreDataDto.getId());
                genre.setName(genreDataDto.getName());

                genreRepository.save(genre);
            }

        } else {
            int statusCode = response.code();
            String errorMessage = "API request was unsuccessful. Status code: " + statusCode;
            System.out.println(errorMessage);
            throw new Exception(errorMessage); // catch it in the controller

            // access object in onResponse / onFailure
        }
    }


//        call.enqueue(new Callback<>() {
//            @Override
//            public void onResponse(Call<GenreDto> call, Response<GenreDto> response) {
//                if (response.isSuccessful()) {
//                    GenreDto genreDTO = response.body();
//                    List<GenreDataDto> genreDataDtoList = genreDTO.getGenres();
//
//                    for (GenreDataDto genreDataDto : genreDataDtoList) {
//                        Genre genre = new Genre();
//                        genre.setId(genreDataDto.getId());
//                        genre.setName(genreDataDto.getName());
//
//                        genreRepository.save(genre);
//                    }
//
//                } else {
//                    int statusCode = response.code();
//                    String errorMessage = "API request was unsuccessful. Status code: " + statusCode;
//                    System.out.println(errorMessage);
//                    throw Exception(errorMessage); // catch it in the controller
//
//                    // access object in onResponse / onFailure
//                }
//            }
//
//
//            @Override
//            public void onFailure(Call<GenreDto> call, Throwable t) {
//                String errorMessage = "API request failed. Error: " + t.getMessage();
//                System.out.println(errorMessage);
//            }
//        });


    @Override
    public List<Genre> showStoredGenres() {
        return genreRepository.findAll();
    }
}


/*
 * QUESTIONS TO ASK:
 * - how to return errorMessage or successMesage into controller?
 * - why this code always select all from db when in fact it should read the content of the website and store it to db, nothing else
 * - is this the right approach?
 * - GET endpoints /list-genres and /genres returns 403 code even after receiving the token when using SCOPE_READ in hasAuthority- why?
 * - SCOPE_READ - where does this come from?
 * */