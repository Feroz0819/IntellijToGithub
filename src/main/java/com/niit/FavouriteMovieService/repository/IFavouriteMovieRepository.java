package com.niit.FavouriteMovieService.repository;

import com.niit.FavouriteMovieService.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IFavouriteMovieRepository extends MongoRepository<User,String> {

    User findByUserId(String userId);
}
