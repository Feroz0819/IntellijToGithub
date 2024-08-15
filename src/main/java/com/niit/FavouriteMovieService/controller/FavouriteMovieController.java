package com.niit.FavouriteMovieService.controller;

import com.niit.FavouriteMovieService.domain.FavouriteMovie;
import com.niit.FavouriteMovieService.domain.User;
import com.niit.FavouriteMovieService.exception.UserAlreadyExistException;
import com.niit.FavouriteMovieService.exception.UserNotFoundException;
import com.niit.FavouriteMovieService.service.IFavouriteMovieService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v2")
public class FavouriteMovieController {
    ResponseEntity responseEntity;
    IFavouriteMovieService iFavouriteMovieService;
@Autowired
    public FavouriteMovieController(IFavouriteMovieService iFavouriteMovieService) {
        this.iFavouriteMovieService = iFavouriteMovieService;
    }
    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody User user) throws UserAlreadyExistException{
    try {
        User registeredUser=iFavouriteMovieService.registerUser(user);
        responseEntity=new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    } catch (UserAlreadyExistException e) {
        throw new UserAlreadyExistException();
    } catch (Exception e){
        responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return responseEntity;
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody User user) throws UserNotFoundException{
    try{
        User updatedUser=iFavouriteMovieService.updateUser(user);
        responseEntity=new ResponseEntity<>(updatedUser,HttpStatus.OK);
    } catch (UserNotFoundException e) {
        throw new UserNotFoundException();
    } catch (Exception e){
        responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return responseEntity;
    }

    @PostMapping("/user/saveFavouriteMovie")
    public ResponseEntity<?> saveMovieToList(@RequestBody FavouriteMovie favouriteMovie, HttpServletRequest request) throws UserNotFoundException{
        // add a product to a specific user,
        // return 201 status if track is saved else 500 status
        try {
            System.out.println("header" + request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("userId from claims :: " + claims.getSubject());
            String userId = claims.getSubject();
            System.out.println("userId :: " + userId);
            responseEntity = new ResponseEntity<>(iFavouriteMovieService.saveFavouriteMovieToList(favouriteMovie, userId), HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }
        return responseEntity;    }

    @GetMapping("/user/getAllFavouriteMovies")
    public ResponseEntity<?> getAllMoviesFromList(HttpServletRequest request) throws UserNotFoundException {
        // list all products of a specific user,
        // return 200 status if track is saved else 500 status
        try {
            System.out.println("header" + request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("userId from claims :: " + claims.getSubject());
            String userId = claims.getSubject();
            System.out.println("userId :: " + userId);
            responseEntity = new ResponseEntity<>(iFavouriteMovieService.getAllFavouriteMoviesFromList(userId), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }
        return responseEntity;    }
}
