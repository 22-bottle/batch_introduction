//package com.example.SpringBatchTutorial.job.ConditionalStep;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobScope;
//import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.ExecutionContext;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//@RequiredArgsConstructor
//public class ConditionalStepJobConfig extends DefaultBatchConfiguration {
//
//    @Bean
//    public Job conditionalStepJob(JobRepository jobRepository,
//                                  Step conditionalStartStep,
//                                  Step conditionalAllStep,
//                                  Step conditionalFailStep,
//                                  Step conditionalCompletedStep) {
//        return new JobBuilder("conditionalStepJob", jobRepository)
//                .incrementer(new RunIdIncrementer())
//                .start(conditionalStartStep)
//                    .on("FAILED").to(conditionalFailStep) //FAILED일 때는 conditionalFailStep으로
//                .from(conditionalStartStep)
//                    .on("COMPLETED").to(conditionalCompletedStep) //COMPLETED일 때는 conditionalCompletedStep으로
//                .from(conditionalStartStep)
//                    .on("*").to(conditionalAllStep) //나머지는 conditionalAllStep으로 분기
//                .end()
//                .build();
//    }
//
//    @Bean
//    @JobScope
//    public Step conditionalStartStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
//        return new StepBuilder("conditionalStartStep", jobRepository)
//                .allowStartIfComplete(true) //a job instance already exists and is complete for parameters 방지
//                .tasklet(((contribution, chunkContext) -> {
//                    System.out.println("conditional start step");
//                    return RepeatStatus.FINISHED;
////                    throw new Exception("Failed!!!!!!!!!!"); //일부러 error 만들어봄
//                }), platformTransactionManager)
//                .build();
//    }
//
//    @Bean
//    @JobScope
//    public Step conditionalCompletedStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
//        return new StepBuilder("conditionalCompletedStep", jobRepository)
//                .allowStartIfComplete(true) //a job instance already exists and is complete for parameters 방지
//                .tasklet(((contribution, chunkContext) -> {
//                    System.out.println("conditional completed step");
//                    return RepeatStatus.FINISHED;
//                }), platformTransactionManager)
//                .build();
//    }
//
//    @Bean
//    @JobScope
//    public Step conditionalFailStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
//        return new StepBuilder("conditionalFailStep", jobRepository)
//                .allowStartIfComplete(true) //a job instance already exists and is complete for parameters 방지
//                .tasklet(((contribution, chunkContext) -> {
//                    System.out.println("conditional fail step");
//                    return RepeatStatus.FINISHED;
//                }), platformTransactionManager)
//                .build();
//    }
//
//    @Bean
//    @JobScope
//    public Step conditionalAllStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
//        return new StepBuilder("conditionalAllStep", jobRepository)
//                .allowStartIfComplete(true) //a job instance already exists and is complete for parameters 방지
//                .tasklet(((contribution, chunkContext) -> {
//                    System.out.println("conditional all step");
//                    return RepeatStatus.FINISHED;
//                }), platformTransactionManager)
//                .build();
//    }
//
//}