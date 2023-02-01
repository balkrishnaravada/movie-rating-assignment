package com.moviereport.api.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;

import org.springframework.batch.core.launch.support.RunIdIncrementer;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.moviereport.api.entities.Movies;
import com.moviereport.api.entities.Ratings;


@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public FlatFileItemReader<Movies> reader()
	{
		FlatFileItemReader<Movies> reader=new FlatFileItemReader<>();
		reader.setResource(new ClassPathResource("/movies.csv"));
		reader.setLineMapper(getLineMapper());
		reader.setLinesToSkip(1);
		return reader;
	}
	
	@Bean
	public FlatFileItemReader<Ratings> reader2()
	{
		FlatFileItemReader<Ratings> reader2=new FlatFileItemReader<>();
		reader2.setResource(new ClassPathResource("/ratings.csv"));
		reader2.setLineMapper(getLineMapper2());
		reader2.setLinesToSkip(1);
		return reader2;
	}

	private LineMapper<Movies> getLineMapper() {
		 DefaultLineMapper<Movies> lineMapper =new  DefaultLineMapper<>();
		 
		 DelimitedLineTokenizer lineTokenizer=new DelimitedLineTokenizer();
		 lineTokenizer.setNames(new String[] {"tconst","title_type","primary_title","runtime_minutes","genres"});
		 lineTokenizer.setIncludedFields(new int[] {0,1,2,3,4});
		 
		 BeanWrapperFieldSetMapper<Movies> fieldSetMapper= new BeanWrapperFieldSetMapper<>();
		 fieldSetMapper.setTargetType(Movies.class);
		 
		 lineMapper.setLineTokenizer(lineTokenizer);
		 lineMapper.setFieldSetMapper(fieldSetMapper);
		return lineMapper;
	}
	
	private LineMapper<Ratings> getLineMapper2() {
		 DefaultLineMapper<Ratings> lineMapper2 =new  DefaultLineMapper<>();
		 
		 DelimitedLineTokenizer lineTokenizer2=new DelimitedLineTokenizer();
		 lineTokenizer2.setNames(new String[] {"tconst","average_rating","num_votes"});
		 lineTokenizer2.setIncludedFields(new int[] {0,1,2});
		 
		 BeanWrapperFieldSetMapper<Ratings> fieldSetMapper2= new BeanWrapperFieldSetMapper<>();
		 fieldSetMapper2.setTargetType(Ratings.class);
		 
		 lineMapper2.setLineTokenizer(lineTokenizer2);
		 lineMapper2.setFieldSetMapper(fieldSetMapper2);
		return lineMapper2;
	}
	
	
	@Bean
	public MovieItemProcessor processor()
	{
		return new MovieItemProcessor();
	}
	
	@Bean
	public RatingItemProcessor processor2()
	{
		return new RatingItemProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<Movies> writer(){
		JdbcBatchItemWriter<Movies> writer=new JdbcBatchItemWriter<>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Movies>());
		writer.setSql("insert into movies(tconst,title_type,primary_title,runtime_minutes,genres) values (:tconst,:title_type,:primary_title,:runtime_minutes,:genres)");
		writer.setDataSource(dataSource);
		return writer;
	}
	
	@Bean
	public JdbcBatchItemWriter<Ratings> writer2(){
		JdbcBatchItemWriter<Ratings> writer2=new JdbcBatchItemWriter<>();
		writer2.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Ratings>());
		writer2.setSql("insert into ratings(tconst,average_rating,num_votes) values (:tconst,:average_rating,:num_votes)");
		writer2.setDataSource(dataSource);
		return writer2;
	}
	
	@Bean
	public Job importMoviesJob() {
		return this.jobBuilderFactory.get("MOVIES-IMPORT-JOB").incrementer(new RunIdIncrementer()).flow(step1()).end().build();
		
	}
	
	@Bean
	public Job importRatingsJob() {
		return this.jobBuilderFactory.get("RATING-IMPORT-JOB").incrementer(new RunIdIncrementer()).flow(step2()).end().build();
		
	}

	@Bean
	public Step step1() {
		return this.stepBuilderFactory.get("step1").<Movies,Movies>chunk(10)
		.reader(reader())
		.processor(processor())
		.writer(writer())
		.build();
		
	}
	@Bean
	public Step step2() {
		return this.stepBuilderFactory.get("step2").<Ratings,Ratings>chunk(10)
		.reader(reader2())
		.processor(processor2())
		.writer(writer2())
		.build();
		
	}
}
