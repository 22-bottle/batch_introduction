package com.test.companyBatch.job;

import com.test.companyBatch.job.dto.CatchResponseDto;
import com.test.companyBatch.job.dto.CompanyResponseDto;
import com.test.companyBatch.job.entity.Company;
import com.test.companyBatch.job.repostiory.CompanyRepository;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
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
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.reactive.function.client.WebClient;
import java.io.StringReader;

@Configuration
@RequiredArgsConstructor
public class CompanyBatchJobConfig extends DefaultBatchConfiguration {

    @Autowired
    private CompanyRepository companyRepository;

    private final String URL = "https://www.catch.co.kr/apiGuide/guide/openAPIGuide/apiCompList?Service=1&SortCode=3&";
    private final String APIKEY = "APIKey=OKi0USF3nvPj8a7RqbTErJqAeUNEt0YnkpKixpoEB2QcQ";

    @Bean
    public Job companyBatchJob(JobRepository jobRepository, Step companyBatchStep) {
        return new JobBuilder("companyBatchJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(companyBatchStep)
                .build();
    }

    @Bean
    @JobScope
    public Step companyBatchStep(JobRepository jobRepository,
                                 ItemReader<CompanyResponseDto> companyBatchReader,
                                 ItemProcessor<CompanyResponseDto, Company> companyBatchProcessor,
                                 ItemWriter<Company> companyBatchWriter,
                                 PlatformTransactionManager platformTransactionManager) {
        return new StepBuilder("companyReadStep", jobRepository)
                .allowStartIfComplete(true)
                .<CompanyResponseDto, Company>chunk(10, platformTransactionManager)
                .reader(companyBatchReader)
                .processor(companyBatchProcessor)
                .writer(companyBatchWriter)
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<CompanyResponseDto> companyBatchReader() throws JAXBException {
        WebClient webClient = WebClientUtil.getBaseUrl(URL + APIKEY);
        String strCatchResponse = webClient.get()
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();
        JAXBContext jaxbContext = JAXBContext.newInstance(CatchResponseDto.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        CatchResponseDto catchResponseDto = (CatchResponseDto) unmarshaller.unmarshal(new StringReader(strCatchResponse));
        System.out.println("catch 조회 결과 : " + catchResponseDto.getResultMsg());
        System.out.println("조회 결과 개수 : " + catchResponseDto.getRowCount());
        System.out.println("company list : " + catchResponseDto.getCompanys().getCompanyList().size());
        return new ListItemReader<>(catchResponseDto.getCompanys().getCompanyList());
    }

    @Bean
    @StepScope
    public ItemProcessor<CompanyResponseDto, Company> companyBatchProcessor() {
        return new ItemProcessor<CompanyResponseDto, Company>() {
            @Override
            public Company process(CompanyResponseDto dto) throws Exception {
                return Company.toEntity(dto);
            }
        };
    }

    @Bean
    @StepScope
    public ItemWriter<Company> companyBatchWriter() {
        return new RepositoryItemWriterBuilder<Company>()
                .repository(companyRepository)
                .methodName("save")
                .build();
    }

//    @Bean
//    @StepScope
//    public Tasklet companyReadTasklet() {
//        return new Tasklet() {
//            @Override
//            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                WebClient webClient = WebClientUtil.getBaseUrl(URL);
//                String strCatchResponse = webClient.get()
//                        .retrieve()
//                        .bodyToMono(String.class)
//                        .block();
//                JAXBContext jaxbContext = JAXBContext.newInstance(CatchResponseDto.class);
//                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//                CatchResponseDto catchResponseDto = (CatchResponseDto) unmarshaller.unmarshal(new StringReader(strCatchResponse));
//                System.out.println("Catch 조회 결과 : " + catchResponseDto.getResultMsg());
//                return RepeatStatus.FINISHED;
//            }
//        };
//    }

}
