package com.reactivespring.movies_info_services.controller;

import com.reactivespring.movies_info_services.domain.MovieInfo;
import com.reactivespring.movies_info_services.service.MoviesInfoServices;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("v1")
public class MoviesInfoController {

    private MoviesInfoServices movieInfoServices;

    public MoviesInfoController(MoviesInfoServices movieInfoServices) {
        this.movieInfoServices = movieInfoServices;
    }

    @PostMapping("/movieinfos")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<MovieInfo> addMovieInfo(@RequestBody MovieInfo movieInfo){
       return movieInfoServices.addMovieInfo(movieInfo);

    }

    @GetMapping("/movieinfos")
    public Flux<MovieInfo> getAllMovieInfo(){
        return movieInfoServices.getAllMovieInfos().log();
    }

    @GetMapping("/movieinfos/{id}")
    public Mono<MovieInfo> getMovieInfoById(@PathVariable String id){
        return movieInfoServices.getMovieInfoById(id).log();

    }

    @PutMapping("/movieinfos/{id}")
    public Mono<MovieInfo> updateMovieInfo(@RequestBody MovieInfo updateMovieInfo, @PathVariable String id){
        return movieInfoServices.updateMovieInfo(updateMovieInfo, id);
    }

    @DeleteMapping("/movieinfos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteMovieInfo(@PathVariable String id){
        return movieInfoServices.deleteMovieInfo(id);
    }
}
