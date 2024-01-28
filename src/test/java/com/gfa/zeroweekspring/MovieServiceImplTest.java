//package com.gfa.zeroweekspring;
//
//import com.gfa.zeroweekspring.dto.GenreDataDto;
//import com.gfa.zeroweekspring.dto.GenreDto;
//import com.gfa.zeroweekspring.integration.MovieApi;
//import com.gfa.zeroweekspring.models.Genre;
//import com.gfa.zeroweekspring.repositories.GenreRepository;
//import com.gfa.zeroweekspring.repositories.MovieRepository;
//import com.gfa.zeroweekspring.services.MovieServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Captor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.CountDownLatch;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
////@WebMvcTest(MovieServiceImpl.class)
////@RunWith(SpringRunner.class)
//@RunWith(MockitoJUnitRunner.class)
//public class MovieServiceImplTest {
//
//    @InjectMocks
//    private MovieServiceImpl movieService;
//
//    @Mock
//    private MovieRepository movieRepository;
//
//    @Mock
//    private GenreRepository genreRepository;
//
//    @Mock
//    private MovieApi movieApi;
//
//    @Captor
//    private ArgumentCaptor<Genre> genreCaptor;
//
//    @Test
//    public void testFetchAndStoreMovieGenres() throws InterruptedException {
//        // Create a CountDownLatch to wait for the async operation to complete
//        CountDownLatch latch = new CountDownLatch(1);
//
//        // Mock the API response
//        GenreDto genreDto = new GenreDto();
//        List<GenreDataDto> genreDataDtos = new ArrayList<>();
//        genreDataDtos.add(new GenreDataDto(1L, "Action"));
//        genreDto.setGenres(genreDataDtos);
//        Call<GenreDto> call = mock(Call.class);
//
//        // Define the behavior of the mocked API call
//        when(movieApi.getMoviesGenres()).thenReturn(call);
//        when(call.enqueue(any(Callback.class))).thenAnswer(invocation -> {
//            Callback<GenreDto> callback = invocation.getArgument(0);
//            callback.onResponse(call, Response.success(genreDto));
//            // Release the latch to signal that the async operation is complete
//            latch.countDown();
//            return null;
//        });
//
//        // Execute the method to be tested
//        movieService.fetchAndStoreMovieGenres();
//
//        // Wait for the latch, allowing the async operation to complete
//        latch.await();
//
//        // Verify that the save method was called with the expected Genre object
//        verify(genreRepository, times(1)).save(genreCaptor.capture());
//        Genre savedGenre = genreCaptor.getValue();
//        assertEquals(Long.valueOf(1L), savedGenre.getId());
//        assertEquals("Action", savedGenre.getName());
//    }
//}
