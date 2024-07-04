package com.reactivespring.movies_info_services.unit.controller;

import com.reactivespring.movies_info_services.controller.MoviesInfoController;
import com.reactivespring.movies_info_services.domain.MovieInfo;
import com.reactivespring.movies_info_services.service.MoviesInfoServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;

@WebFluxTest(controllers = MoviesInfoController.class)
@AutoConfigureWebTestClient
public class MoviesInfoControllerUnitTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private MoviesInfoServices moviesInfoServicesMock;

    static String MOVIES_INFO_URL ="/v1/movieinfos";

    @Test
    void getAllMovieInfo(){

        var movieinfos = List.of(new MovieInfo(null, "Batman Begins",
                        2005, List.of("Christian Bale1", "Michael Cane"), LocalDate.parse("2005-06-15")),
                new MovieInfo(null, "The Dark Knight",
                        2008, List.of("Christian Bale2", "HeathLedger"), LocalDate.parse("2008-07-18")),
                new MovieInfo("abc", "Dark Knight Rises5",
                        2012, List.of("Christian Bale3", "Tom Hardy"), LocalDate.parse("2012-07-20")));

        when(moviesInfoServicesMock.getAllMovieInfos()).thenReturn(Flux.fromIterable(movieinfos));

        webTestClient
                .get()
                .uri(MOVIES_INFO_URL)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(MovieInfo.class)
                .hasSize(3);
    }
}
