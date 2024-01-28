//package com.gfa.zeroweekspring;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.gfa.zeroweekspring.controllers.APIMovieController;
//import com.gfa.zeroweekspring.models.Genre;
//import com.gfa.zeroweekspring.services.MovieService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(APIMovieController.class)
//public class APIMovieControllerTest {
//
//    @MockBean
//    MovieService movieServiceMock;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//
//    @Test
//    public void shouldDisplayMovieGenres() throws Exception {
//        Genre genre1 = new Genre();
//        genre1.setId(1L);
//        genre1.setName("first movie");
//        Genre genre2 = new Genre();
//        genre2.setId(2L);
//        genre2.setName("second movie");
//
//
//        List<Genre> genres = new ArrayList<>();
//        genres.add(genre1);
//        genres.add(genre2);
//
//        ObjectMapper om = new ObjectMapper();
//        String genresStr = om.writeValueAsString(genres);
//
//        when(movieServiceMock.showStoredGenres()).thenReturn(genres);
//
//        this.mockMvc.perform(get("/list-genres"))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print())  // this line of code is good to see details of the Http request and response
//                .andExpect(content().json(genresStr));
//    }
//
//
//}
