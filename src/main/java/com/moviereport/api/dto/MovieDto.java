package com.moviereport.api.dto;

public class MovieDto {
	
    private String tconst;
	
	private String primary_title;

	private int runtime_minutes;
	
	private String genres;

	public String getTconst() {
		return tconst;
	}

	public void setTconst(String tconst) {
		this.tconst = tconst;
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

	public MovieDto(String tconst, String primary_title, int runtime_minutes, String genres) {
		super();
		this.tconst = tconst;
		this.primary_title = primary_title;
		this.runtime_minutes = runtime_minutes;
		this.genres = genres;
	}

	public MovieDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
