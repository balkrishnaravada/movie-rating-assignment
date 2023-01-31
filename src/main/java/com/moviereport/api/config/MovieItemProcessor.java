package com.moviereport.api.config;

import org.springframework.batch.item.ItemProcessor;

import com.moviereport.api.entities.Movies;

public class MovieItemProcessor implements ItemProcessor<Movies, Movies> {

	@Override
	public Movies process(Movies movies) throws Exception {
		// TODO Auto-generated method stub
		return movies;
	}

	

}
