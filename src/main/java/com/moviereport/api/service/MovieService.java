package com.moviereport.api.service;

import java.util.List;

import com.moviereport.api.dto.MovieDto;
import com.moviereport.api.entities.Movies;

public interface MovieService {
	Movies createMovie(Movies movie);
	List<MovieDto> getLongestDurationMovies();
	List<Movies> getTopRatedMovies();
	
	

}
