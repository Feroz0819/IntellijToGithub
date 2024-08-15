package com.niit.FavouriteMovieService.service;

import com.niit.FavouriteMovieService.domain.FavouriteMovie;
import com.niit.FavouriteMovieService.domain.User;
import com.niit.FavouriteMovieService.exception.FavouriteMovieNotFoundException;
import com.niit.FavouriteMovieService.exception.UserAlreadyExistException;
import com.niit.FavouriteMovieService.exception.UserNotFoundException;

import java.util.List;

public interface IFavouriteMovieService {
    User registerUser(User user) throws UserAlreadyExistException;
    User updateUser(User user) throws UserNotFoundException;
    User saveFavouriteMovieToList(FavouriteMovie favouriteMovie, String userId) throws UserNotFoundException;
    List<FavouriteMovie> getAllFavouriteMoviesFromList(String userId) throws UserNotFoundException;

}
