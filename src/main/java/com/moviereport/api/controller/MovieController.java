package com.moviereport.api.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviereport.api.config.ResponseMessage;
import com.moviereport.api.dto.MovieDto;
import com.moviereport.api.entities.Movies;
import com.moviereport.api.service.MovieService;

@RestController
@RequestMapping("/api/v1")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@GetMapping("/longest-duration-movies")
	public ResponseEntity<List<MovieDto>> top10Movies()
	{
		List<MovieDto> movies=this.movieService.getLongestDurationMovies();
		
		return new ResponseEntity<List<MovieDto>>(movies,HttpStatus.OK);
	}
	
	@PostMapping("/new-movie")
	public ResponseEntity<ResponseMessage> createMovie(@RequestBody Movies movie)
	{
		String message;
	try {
		this.movieService.createMovie(movie);
		message="Success!!";
		System.out.println(message);
	} 
		catch (Exception e) {
		message="Something went wrong!!";
		System.out.println(message);
	}
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));
	}
	
	@GetMapping("/top-rated-movies")
	public ResponseEntity<List<Movies>> getTopRatedMovies()
	{
		List<Movies> movies=this.movieService.getTopRatedMovies();
		
		return new ResponseEntity<List<Movies>>(movies,HttpStatus.OK);
	}
	

}
