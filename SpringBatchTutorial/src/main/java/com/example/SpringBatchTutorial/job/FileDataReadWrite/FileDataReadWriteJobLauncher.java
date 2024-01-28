//package com.example.SpringBatchTutorial.job.FileDataReadWrite;
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
//public class FileDataReadWriteJobLauncher implements CommandLineRunner {
//
//    @Autowired
//    private JobLauncher jobLauncher;
//    @Autowired
//    @Qualifier("fileDataReadWriteJob")
//    private Job job;
//
//    @Override
//    public void run(String... args) throws Exception {
//        JobExecution execution = jobLauncher.run(job, new JobParameters());
//        System.out.println("Exit status : " + execution.getStatus());
//    }
//
//}
