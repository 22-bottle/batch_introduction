//package com.example.SpringBatchTutorial.job.MultipleStep;
//
//import com.example.SpringBatchTutorial.job.FileDataReadWrite.PlayerFieldSetMapper;
//import com.example.SpringBatchTutorial.job.FileDataReadWrite.dto.Player;
//import com.example.SpringBatchTutorial.job.FileDataReadWrite.dto.PlayerYears;
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobScope;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.item.ExecutionContext;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.FlatFileItemWriter;
//import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
//import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
//import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
//import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
//import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//@RequiredArgsConstructor
//public class MultipleStepJobConfig extends DefaultBatchConfiguration {
//
//    @Bean
//    public Job multipleStepJob(JobRepository jobRepository, Step multipleStep1, Step multipleStep2, Step multipleStep3) {
//        return new JobBuilder("multipleStepJob", jobRepository)
//                .incrementer(new RunIdIncrementer())
//                .start(multipleStep1)
//                .next(multipleStep2)
//                .next(multipleStep3)
//                .build();
//    }
//
//    @Bean
//    @JobScope
//    public Step multipleStep1(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
//        return new StepBuilder("multipleStep1", jobRepository)
//                .allowStartIfComplete(true) //a job instance already exists and is complete for parameters 방지
//                .tasklet(((contribution, chunkContext) -> {
//                    System.out.println("multiple step 1");
//                    return RepeatStatus.FINISHED;
//                }), platformTransactionManager)
//                .build();
//    }
//
//    @Bean
//    @JobScope
//    public Step multipleStep2(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
//        return new StepBuilder("multipleStep2", jobRepository)
//                .allowStartIfComplete(true) //a job instance already exists and is complete for parameters 방지
//                .tasklet(((contribution, chunkContext) -> {
//                    System.out.println("multiple step 2");
//
//                    //data 담아서 다음 step으로
//                    ExecutionContext executionContext = chunkContext
//                            .getStepContext()
//                            .getStepExecution()
//                            .getJobExecution()
//                            .getExecutionContext();
//                    executionContext.put("someKey", "hello!");
//
//                    return RepeatStatus.FINISHED;
//                }), platformTransactionManager)
//                .build();
//    }
//
//    @Bean
//    @JobScope
//    public Step multipleStep3(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
//        return new StepBuilder("multipleStep3", jobRepository)
//                .allowStartIfComplete(true) //a job instance already exists and is complete for parameters 방지
//                .tasklet(((contribution, chunkContext) -> {
//                    System.out.println("multiple step 3");
//
//                    //이전 step에서 담아온 data를 꺼내 쓰기
//                    ExecutionContext executionContext = chunkContext
//                            .getStepContext()
//                            .getStepExecution()
//                            .getJobExecution()
//                            .getExecutionContext();
//                    System.out.println(executionContext.get("someKey"));
//
//                    return RepeatStatus.FINISHED;
//                }), platformTransactionManager)
//                .build();
//    }
//
//}