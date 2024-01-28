//package com.example.SpringBatchTutorial.job.FileDataReadWrite;
//
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
//import org.springframework.batch.item.Chunk;
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
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//@RequiredArgsConstructor
//public class FileDataReadWriteJobConfig extends DefaultBatchConfiguration {
//
//    @Bean
//    public Job fileDataReadWriteJob(JobRepository jobRepository, Step fileDataReadWriteStep) {
//        return new JobBuilder("fileDataReadWriteJob", jobRepository)
//                .incrementer(new RunIdIncrementer())
//                .start(fileDataReadWriteStep)
//                .build();
//    }
//
//    @Bean
//    @JobScope
//    public Step fileDataReadWriteStep(JobRepository jobRepository,
//                                ItemReader<Player> playerItemReader,
//                                ItemProcessor<Player, PlayerYears> playerItemProcessor,
//                                ItemWriter<PlayerYears> playerItemWriter,
//                                PlatformTransactionManager platformTransactionManager) {
//        return new StepBuilder("fileDataReadWriteStep", jobRepository)
//                .allowStartIfComplete(true) //a job instance already exists and is complete for parameters 방지
//                .<Player, PlayerYears>chunk(5, platformTransactionManager) //5개의 데이터 단위로 Orders 타입의 객체를 읽어서 Orders 타입의 객체로 쓸 것
//                .reader(playerItemReader)
////                .writer(new ItemWriter<Player>() {
////                    @Override
////                    public void write(Chunk<? extends Player> chunk) throws Exception {
////                        chunk.forEach(System.out::println);
////                    }
////                })
//                .processor(playerItemProcessor)
//                .writer(playerItemWriter)
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public FlatFileItemReader<Player> playerItemReader() {
//        return new FlatFileItemReaderBuilder<Player>()
//                .name("playerItemReader")
//                .resource(new FileSystemResource("players.csv"))
//                .lineTokenizer(new DelimitedLineTokenizer()) //어떻게 구분할 것인지
//                .fieldSetMapper(new PlayerFieldSetMapper()) //읽은 데이터를 객체로 바꾸기 위한 mapper
//                .linesToSkip(1) //첫째줄은 스킵
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public ItemProcessor<Player, PlayerYears> playerItemProcessor() {
//        return new ItemProcessor<Player, PlayerYears>() {
//            @Override
//            public PlayerYears process(Player item) throws Exception {
//                return new PlayerYears(item);
//            }
//        };
//    }
//
//    @Bean
//    @StepScope
//    public FlatFileItemWriter<PlayerYears> playerItemWriter() {
//        BeanWrapperFieldExtractor<PlayerYears> fieldExtractor = new BeanWrapperFieldExtractor<>(); //field를 추출하기 위한 객체
//        fieldExtractor.setNames(new String[] {"ID", "lastName", "position", "yearsExperience"}); //추출할 field
//        fieldExtractor.afterPropertiesSet();
//
//        DelimitedLineAggregator<PlayerYears> lineAggregator = new DelimitedLineAggregator<>();
//        lineAggregator.setDelimiter(","); //콤마로 구분할 것
//        lineAggregator.setFieldExtractor(fieldExtractor); //위에서 명시한 field를 추출
//
//        FileSystemResource outputResource = new FileSystemResource("players_output.txt");
//
//        return new FlatFileItemWriterBuilder<PlayerYears>()
//                .name("playerItemWriter")
//                .resource(outputResource)
//                .lineAggregator(lineAggregator)
//                .build();
//    }
//
//}