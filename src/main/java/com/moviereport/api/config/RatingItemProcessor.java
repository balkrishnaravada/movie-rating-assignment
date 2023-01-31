package com.moviereport.api.config;

import org.springframework.batch.item.ItemProcessor;

import com.moviereport.api.entities.Ratings;

public class RatingItemProcessor implements ItemProcessor<Ratings, Ratings> {

	@Override
	public Ratings process(Ratings ratings) throws Exception {
		// TODO Auto-generated method stub
		return ratings;
	}

}
