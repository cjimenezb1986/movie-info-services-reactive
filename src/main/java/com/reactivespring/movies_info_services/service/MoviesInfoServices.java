package com.reactivespring.movies_info_services.service;

import com.reactivespring.movies_info_services.domain.MovieInfo;
import com.reactivespring.movies_info_services.repository.MovieInfoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MoviesInfoServices {

    private MovieInfoRepository movieInfoRepository;

    public MoviesInfoServices(MovieInfoRepository movieInfoRepository) {
        this.movieInfoRepository = movieInfoRepository;
    }

    public Mono<MovieInfo> addMovieInfo(MovieInfo movieInfo) {

       return movieInfoRepository.save(movieInfo);

    }

    public Flux<MovieInfo> getAllMovieInfos() {
        return movieInfoRepository.findAll();
    }


    public Mono<MovieInfo> getMovieInfoById(String id) {
        return movieInfoRepository.findById(id);
    }

    public Mono<MovieInfo> updateMovieInfo(MovieInfo updateMovieInfo, String id) {

        return movieInfoRepository.findById(id)
                .flatMap( movieIfo->{
                    movieIfo.setCast(updateMovieInfo.getCast());
                    movieIfo.setName(updateMovieInfo.getName());
                    movieIfo.setRelease_date(updateMovieInfo.getRelease_date());
                    movieIfo.setYear(updateMovieInfo.getYear());
                    return movieInfoRepository.save(movieIfo);
                });
    }

    public Mono<Void> deleteMovieInfo(String id) {
        return movieInfoRepository.deleteById(id);
    }
}
