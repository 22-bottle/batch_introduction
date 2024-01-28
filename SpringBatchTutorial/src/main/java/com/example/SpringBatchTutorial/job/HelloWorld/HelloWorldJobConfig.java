package com.example.SpringBatchTutorial.job.HelloWorld;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class HelloWorldJobConfig extends DefaultBatchConfiguration {

//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job helloWorldJob() {
//        return jobBuilderFactory.get("helloWorldJob")
//                .incrementer(new RunIdIncrementer())
//                .start(helloWorldStep())
//                .build();
//    }
//
//    @Bean
//    @JobScope
//    public Step helloWorldStep() {
//        return stepBuilderFactory.get("helloWorldStep")
//                .tasklet(helloWorldTasklet())
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public Tasklet helloWorldTasklet() {
//        return new Tasklet() {
//            @Override
//            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                System.out.println("Hello World Spring Batch");
//                return RepeatStatus.FINISHED;
//            }
//        };
//    }

    @Bean
    public Job helloWorldJob(JobRepository jobRepository, Step helloWorldStep) {
        return new JobBuilder("helloWorldJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(helloWorldStep)
                .build();
    }

    @Bean
    public Step helloWorldStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new StepBuilder("helloWorldStep", jobRepository)
                .allowStartIfComplete(true)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("hello world step start=========");
                        return RepeatStatus.FINISHED;
                    }
                }, platformTransactionManager)
                .build();
    }

}
