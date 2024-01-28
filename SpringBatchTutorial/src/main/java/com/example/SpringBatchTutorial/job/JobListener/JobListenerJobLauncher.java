/*
package com.example.SpringBatchTutorial.job.JobListener;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JobListenerJobLauncher implements CommandLineRunner {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    @Qualifier("jobListenerJob")
    private Job job;

    @Override
    public void run(String... args) throws Exception {
        JobExecution execution = jobLauncher.run(job, new JobParameters());
        System.out.println("Exit status : " + execution.getStatus());
    }

}
*/
