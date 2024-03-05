package com.ad.markalive.batch.process;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ChangeStatusOfRemindFlagBatch {
    private JobLauncher jobLauncher;
    private Job job;
    public ChangeStatusOfRemindFlagBatch(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }
//   @Scheduled(fixedDelay = 5, timeUnit = TimeUnit.SECONDS)
   private void run() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
       jobLauncher.run(job, new JobParametersBuilder().addLocalDateTime("Date", LocalDateTime.now()).toJobParameters());
   }
}
