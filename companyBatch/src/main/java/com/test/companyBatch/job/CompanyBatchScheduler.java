package com.test.companyBatch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CompanyBatchScheduler {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    @Qualifier("companyBatchJob")
    private Job companyBatchJob;

    @Scheduled(cron = "0/10 * * * * *")
    public void run() throws Exception {
        JobParameters jobParameters = new JobParameters(
                Collections.singletonMap("reqeustTime", new JobParameter<>(System.currentTimeMillis(), Long.class))
        );
        JobExecution execution = jobLauncher.run(companyBatchJob, jobParameters);
    }

}
