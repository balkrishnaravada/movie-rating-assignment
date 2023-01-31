package com.moviereport.api.service.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviereport.api.dto.MovieDto;

import com.moviereport.api.entities.Movies;
import com.moviereport.api.repo.MovieRepo;
import com.moviereport.api.service.MovieService;


@Service
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	private MovieRepo movieRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Movies createMovie(Movies movie) {
		Movies newMovie=this.movieRepo.save(movie);
		return newMovie;
	}



	@Override
	public List<MovieDto> getLongestDurationMovies() {
		List<Movies> movies=this.movieRepo.findLongestDurationMovies();
		List<MovieDto> moviesDtos=movies.stream().map(movie-> this.modelMapper.map(movie, MovieDto.class)).collect(Collectors.toList());
		return moviesDtos;
	}

	@Override
	public List<Movies> getTopRatedMovies() {
		List<Movies> movies=this.movieRepo.findTopRatedMovies();
		
		return movies;
	}
	
	

}
