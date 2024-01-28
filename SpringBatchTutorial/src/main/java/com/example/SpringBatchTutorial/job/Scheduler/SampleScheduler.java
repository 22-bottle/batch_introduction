package com.example.SpringBatchTutorial.job.Scheduler;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class SampleScheduler {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    @Qualifier("helloWorldJob")
    private Job helloWorldJob;

    @Scheduled(cron = "0 */1 * * * *") //1분에 한번
    public void run() throws Exception {
        JobParameters jobParameters = new JobParameters(
                Collections.singletonMap("requestTime", new JobParameter<>(System.currentTimeMillis(), Long.class))
        );
        JobExecution execution = jobLauncher.run(helloWorldJob, jobParameters);
    }

}
