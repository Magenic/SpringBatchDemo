package dev.springsolver.springbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@EnableRetry
public class SpringBatchConfiguration {
    private static final String INPUT_FILE_DIRECTORY = "src/main/resources/input/";
    private static final String OUTPUT_FILE_DIRECTORY = "build/output/";
    private static final Logger log = LoggerFactory.getLogger(SpringBatchConfiguration.class);

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;


    @Bean(destroyMethod="")
    @Retryable(value = {ItemStreamException.class}, maxAttempts=5)
    @Scope()
    public JsonItemReader<NasdaqTotalView> jsonItemReader() {
        return new JsonItemReaderBuilder<NasdaqTotalView>()
                .jsonObjectReader(new JacksonJsonObjectReader<>(NasdaqTotalView.class))
                .resource(new ClassPathResource("input/NDAQ-AOM.json"))
                .name("ndaqJsonItemReader")
                .build();
    }

    @Bean
    public JsonItemProcessor jsonItemProcessor() {
        return new JsonItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<NasdaqTotalView> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<NasdaqTotalView>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO nasdaq_totalview (soup_partition, soup_sequence, msg_type, symbol_locate, " +
                        "unique_timestamp, order_id, side, quantity, mpid, symbol, price) VALUES (:soupPartition, :soupSequence, :msgType, :symbolLocate, :uniqueTimestamp, :orderId, :side, :quantity, :mpid, :symbol, :price)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<NasdaqTotalView> writer) {
        return stepBuilderFactory.get("step1")
                .<NasdaqTotalView, NasdaqTotalView> chunk(10)
                .reader(jsonItemReader())
                .processor(jsonItemProcessor())
                .writer(writer)
                .build();
    }

}
