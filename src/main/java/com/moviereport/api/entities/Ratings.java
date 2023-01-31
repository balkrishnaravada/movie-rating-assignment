package com.moviereport.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ratings")
public class Ratings {
	@Id
	private String tconst;
	
	private float average_rating;
	
	private int num_votes;
	
	@OneToOne(mappedBy = "ratings")
	private Movies movies;
	
	public Ratings(String tconst, float average_rating, int num_votes, Movies movies) {
		super();
		this.tconst = tconst;
		this.average_rating = average_rating;
		this.num_votes = num_votes;
		this.movies = movies;
	}
	
	
	public String getTconst() {
		return tconst;
	}


	public void setTconst(String tconst) {
		this.tconst = tconst;
	}


	public float getAverage_rating() {
		return average_rating;
	}


	public void setAverage_rating(float average_rating) {
		this.average_rating = average_rating;
	}


	public int getNum_votes() {
		return num_votes;
	}


	public void setNum_votes(int num_votes) {
		this.num_votes = num_votes;
	}


	public Movies getMovies() {
		return movies;
	}


	public void setMovies(Movies movies) {
		this.movies = movies;
	}


	public Ratings() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
