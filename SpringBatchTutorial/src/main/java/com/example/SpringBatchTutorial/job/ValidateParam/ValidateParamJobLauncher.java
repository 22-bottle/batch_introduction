import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

import java.util.HashMap;
import java.util.Map;//package com.example.SpringBatchTutorial.job.ValidateParam;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobExecution;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ValidateParamJobLauncher implements CommandLineRunner {
//
//    @Autowired
//    private JobLauncher jobLauncher;
//    @Autowired
//    @Qualifier("validateParamJob")
//    private Job job;
//
//    @Override
//    public void run(String... args) throws Exception {
//        Map<String, JobParameter<?>> parameters = new HashMap<>();
//        parameters.put("fileName", new JobParameter<>("test.csv", String.class));
//        JobExecution execution = jobLauncher.run(job, new JobParametersBuilder(new JobParameters(parameters)).toJobParameters());
//        System.out.println("Exit status : " + execution.getStatus());
//    }
//
//}
