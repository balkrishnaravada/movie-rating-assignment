package com.moviereport.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviereport.api.entities.Ratings;

public interface RatingRepo extends JpaRepository<Ratings, String>{

}
