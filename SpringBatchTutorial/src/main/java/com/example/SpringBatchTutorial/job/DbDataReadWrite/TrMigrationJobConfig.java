//package com.example.SpringBatchTutorial.job.DbDataReadWrite;
//
//import com.example.SpringBatchTutorial.job.DbDataReadWrite.core.domain.account.Accounts;
//import com.example.SpringBatchTutorial.job.DbDataReadWrite.core.domain.account.AccountsRepository;
//import com.example.SpringBatchTutorial.job.DbDataReadWrite.core.domain.order.Orders;
//import com.example.SpringBatchTutorial.job.DbDataReadWrite.core.domain.order.OrdersRepository;
//import com.example.SpringBatchTutorial.job.JobListener.JobLoggerListener;
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.annotation.JobScope;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.item.Chunk;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.data.RepositoryItemReader;
//import org.springframework.batch.item.data.RepositoryItemWriter;
//import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
//import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.domain.Sort;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import java.util.Arrays;
//import java.util.Collections;
//
//@Configuration
//@RequiredArgsConstructor
//public class TrMigrationJobConfig extends DefaultBatchConfiguration {
//
//    @Autowired
//    private OrdersRepository ordersRepository;
//    @Autowired
//    private AccountsRepository accountRepository;
//
//    @Bean
//    public Job trMigrationJob(JobRepository jobRepository, Step trMigrationStep) {
//        return new JobBuilder("trMigrationJob", jobRepository)
//                .incrementer(new RunIdIncrementer())
//                .start(trMigrationStep)
//                .build();
//    }
//
//    @Bean
//    @JobScope
//    public Step trMigrationStep(JobRepository jobRepository,
//                                ItemReader<Orders> trOrdersReader,
//                                ItemProcessor<Orders, Accounts> trOrderProcessor,
//                                ItemWriter<Accounts> trOrdersWriter,
//                                PlatformTransactionManager platformTransactionManager) {
//        return new StepBuilder("trMigrationStep", jobRepository)
//                .allowStartIfComplete(true) //a job instance already exists and is complete for parameters 방지
//                .<Orders, Accounts>chunk(5, platformTransactionManager) //5개의 데이터 단위로 Orders 타입의 객체를 읽어서 Orders 타입의 객체로 쓸 것
//                .reader(trOrdersReader) //reader 등록
//                .processor(trOrderProcessor) //processor 등록
//                .writer(trOrdersWriter) //writer 등록
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public RepositoryItemReader<Orders> trOrdersReader() {
//        return new RepositoryItemReaderBuilder<Orders>()
//                .name("trOrdersReader")
//                .repository(ordersRepository)
//                .methodName("findAll")
//                .pageSize(5) //보통 chunk size와 같게 설정
//                .arguments(Arrays.asList()) //파라미터가 있는 경우 사용
//                .sorts(Collections.singletonMap("id", Sort.Direction.ASC))
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public ItemProcessor<Orders, Accounts> trOrderProcessor() {
//        return new ItemProcessor<Orders, Accounts>() {
//            @Override
//            public Accounts process(Orders order) throws Exception {
//                return new Accounts(order);
//            }
//        };
//    }
//
//    @Bean
//    @StepScope
//    public RepositoryItemWriter<Accounts> trOrdersWriter() { //ItemWriter를 사용하는 방법도 있음
//        return new RepositoryItemWriterBuilder<Accounts>()
//                .repository(accountRepository)
//                .methodName("save")
//                .build();
//    }
//
//}