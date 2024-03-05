package com.ad.markalive;

import com.ad.markalive.batch.processor.BookmarkTableRemindColProcessor;
import com.ad.markalive.model.Bookmark;
import com.ad.markalive.repository.mappers.BookmarkRowMapper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@SpringBootApplication
@EnableScheduling
public class MarkaliveApplication {
	private DataSource dataSource;
	private JobRepository jobRepository;
	private PlatformTransactionManager platformTransactionManager;

	public MarkaliveApplication(DataSource dataSource, JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
		this.dataSource = dataSource;
		this.jobRepository = jobRepository;
		this.platformTransactionManager = platformTransactionManager;
		this.javaMailSender = javaMailSender;
	}

	public static void main(String[] args) {
		SpringApplication.run(MarkaliveApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:3000");
			}
		};
	}

	@Bean
	public Job checkRemindJob(){
		return new JobBuilder("checkRemindJob", this.jobRepository)
				.start(processBookmarkRemind())
				.build();
	}

	@Bean
	public Step processBookmarkRemind(){
		return new StepBuilder("processRemindColumn", jobRepository)
				.<Bookmark, Bookmark>chunk(10,platformTransactionManager)
				.reader(bookmarkTableReader())
				.processor(new BookmarkTableRemindColProcessor())
				.writer(bookmarkTableWriter())
				.build();
	}

	@Bean
	public JdbcCursorItemReader<Bookmark> bookmarkTableReader(){
		return new JdbcCursorItemReaderBuilder<Bookmark>()
				.dataSource(this.dataSource)
				.name("bookmarkReader")
				.sql("SELECT * FROM BOOKMARK")
				.rowMapper(new BookmarkRowMapper())
				.build();
	}

	@Bean
	public JdbcBatchItemWriter<Bookmark> bookmarkTableWriter(){
		return new JdbcBatchItemWriterBuilder<Bookmark>()
				.dataSource(this.dataSource)
				.sql("UPDATE BOOKMARK SET remind = :remind WHERE id = :id")
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Bookmark>())
				.build();
	}

	@Bean
	public JobLauncher jobLauncherConfigured() throws Exception {
		TaskExecutorJobLauncher jobLauncher = new TaskExecutorJobLauncher();
		jobLauncher.setJobRepository(jobRepository);
		jobLauncher.afterPropertiesSet();
		return jobLauncher;
	}

	@Bean
	public MimeMessage defaultMimeMessage() throws MessagingException {
		return new MimeMessageHelper(javaMailSender.createMimeMessage(), false, "UTF-8").getMimeMessage();
	}
}
