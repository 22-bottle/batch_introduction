//package com.example.SpringBatchTutorial.job.JobListener;
//
//import com.example.SpringBatchTutorial.job.ValidateParam.validator.FileParamValidator;
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.annotation.JobScope;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
//import org.springframework.batch.core.job.CompositeJobParametersValidator;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//@RequiredArgsConstructor
//public class JobListenerJobConfig extends DefaultBatchConfiguration {
//
//    @Bean
//    public Job jobListenerJob(JobRepository jobRepository, Step jobListenerStep) {
//        return new JobBuilder("jobListenerJob", jobRepository)
//                .incrementer(new RunIdIncrementer())
//                .listener(new JobLoggerListener()) //listener 등록
//                .start(jobListenerStep)
//                .build();
//    }
//
//    @Bean
//    @JobScope
//    public Step jobListenerStep(JobRepository jobRepository, Tasklet jobListenerTasklet, PlatformTransactionManager platformTransactionManager) {
//        return new StepBuilder("jobListenerStep", jobRepository)
//                .allowStartIfComplete(true) //a job instance already exists and is complete for parameters 방지
//                .tasklet(jobListenerTasklet, platformTransactionManager)
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public Tasklet jobListenerTasklet() {
//        return new Tasklet() {
//            @Override
//            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                System.out.println("job listener step start=========");
//                return RepeatStatus.FINISHED;
////                throw new Exception("Failed!!!!!!!!!!!!"); //일부러 에러 발생시켜봄
//            }
//        };
//    }
//
//}