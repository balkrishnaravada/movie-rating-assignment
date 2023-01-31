package com.moviereport.api.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="movies")
public class Movies {
	
	@Id
	private String tconst;
	
	private String title_type;
	
	private String primary_title;

	private int runtime_minutes;
	
	private String genres;
	
//	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinTable(name="movie_rating", joinColumns = {@JoinColumn(name="movie_id",referencedColumnName = "tconst")},
//	inverseJoinColumns = {@JoinColumn(name="rating_id",referencedColumnName = "tconst")})
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Ratings ratings;
	
	

	public Movies(String tconst, String title_type, String primary_title, int runtime_minutes, String genres,
			Ratings ratings) {
		super();
		this.tconst = tconst;
		this.title_type = title_type;
		this.primary_title = primary_title;
		this.runtime_minutes = runtime_minutes;
		this.genres = genres;
		this.ratings = ratings;
		
	}

	public Movies() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTconst() {
		return tconst;
	}

	public void setTconst(String tconst) {
		this.tconst = tconst;
	}

	public String getTitle_type() {
		return title_type;
	}

	public void setTitle_type(String title_type) {
		this.title_type = title_type;
	}

	public String getPrimary_title() {
		return primary_title;
	}

	public void setPrimary_title(String primary_title) {
		this.primary_title = primary_title;
	}

	public int getRuntime_minutes() {
		return runtime_minutes;
	}

	public void setRuntime_minutes(int runtime_minutes) {
		this.runtime_minutes = runtime_minutes;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public Ratings getRatings() {
		return ratings;
	}

	public void setRatings(Ratings ratings) {
		this.ratings = ratings;
	}
	
	

}
