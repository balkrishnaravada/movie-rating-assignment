package com.moviereport.api.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.moviereport.api.entities.Movies;

public interface MovieRepo extends JpaRepository<Movies, String> {
	
	@Query(value = "select * from movies order by runtime_minutes DESC Limit 0,10", nativeQuery = true)
	List<Movies> findLongestDurationMovies();
	
	@Query(value="SELECT * FROM movies m INNER JOIN ratings r ON m.tconst=r.tconst WHERE r.average_rating >6 ORDER BY average_rating",nativeQuery = true)
	List<Movies> findTopRatedMovies();
	
	
}
