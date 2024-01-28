package com.gfa.zeroweekspring.integration;

import com.gfa.zeroweekspring.dto.GenreDto;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApi {
    @GET("genre/movie/list?language=en&api_key=c95cd8cc8155aa14dfb02f783aeb9d1f")
    Call<GenreDto> getMoviesGenres();
}