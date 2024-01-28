//package com.example.SpringBatchTutorial.job.ValidateParam;
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
//import java.util.Arrays;
//
//@Configuration
//@RequiredArgsConstructor
//public class ValidateParamJobConfig extends DefaultBatchConfiguration {
//
//    @Bean
//    public Job validateParamJob(JobRepository jobRepository, Step validateParamStep) {
//        return new JobBuilder("validateParamJob", jobRepository)
//                .incrementer(new RunIdIncrementer())
////                .validator(new FileParamValidator()) //하나의 validator를 등록할 때
//                .validator(multipleValidator()) //여러 개의 validator를 등록할 때
//                .start(validateParamStep)
//                .build();
//    }
//
//    private CompositeJobParametersValidator multipleValidator() {
//        CompositeJobParametersValidator validator = new CompositeJobParametersValidator();
//        validator.setValidators(Arrays.asList(new FileParamValidator())); //등록할 validator들을 입력
//        return validator;
//    }
//
//    @Bean
//    @JobScope
//    public Step validateParamStep(JobRepository jobRepository, Tasklet validateParamTasklet, PlatformTransactionManager platformTransactionManager) {
//        return new StepBuilder("validateParamStep", jobRepository)
//                .allowStartIfComplete(true) //a job instance already exists and is complete for parameters 방지
//                .tasklet(validateParamTasklet, platformTransactionManager)
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public Tasklet validateParamTasklet(@Value("#{jobParameters['fileName']}") String fileName) {
//        return new Tasklet() {
//            @Override
//            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                System.out.println("validate param step start=========");
//                System.out.println("fileName : " + fileName);
//                return RepeatStatus.FINISHED;
//            }
//        };
//    }
//
//}