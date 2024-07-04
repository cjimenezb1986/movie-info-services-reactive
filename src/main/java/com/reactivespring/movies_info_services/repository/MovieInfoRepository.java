package com.reactivespring.movies_info_services.repository;

import com.reactivespring.movies_info_services.domain.MovieInfo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MovieInfoRepository extends ReactiveMongoRepository<MovieInfo, String>  {
}
