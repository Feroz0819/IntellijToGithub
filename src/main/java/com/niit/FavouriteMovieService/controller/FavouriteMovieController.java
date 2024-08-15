package com.niit.FavouriteMovieService.controller;

import com.niit.FavouriteMovieService.domain.User;
import com.niit.FavouriteMovieService.exception.UserAlreadyExistException;
import com.niit.FavouriteMovieService.exception.UserNotFoundException;
import com.niit.FavouriteMovieService.service.IFavouriteMovieService;
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
}
