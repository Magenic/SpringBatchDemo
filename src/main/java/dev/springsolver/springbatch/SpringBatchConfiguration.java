package dev.springsolver.springbatch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import javax.sql.DataSource;


@Configuration
@EnableBatchProcessing
@EnableRetry
public class SpringBatchConfiguration {
    private static final Log logger = LogFactory.getLog(SpringBatchConfiguration.class);

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean(destroyMethod="")
    @Retryable(value = {ItemStreamException.class}, maxAttempts=5)
    @StepScope
    public JsonItemReader<NasdaqTotalView> jsonItemReader() throws Exception {
        return new JsonItemReaderBuilder<NasdaqTotalView>()
                .jsonObjectReader(new JacksonJsonObjectReader<>(NasdaqTotalView.class))
                .resource(new ClassPathResource("input/NDAQ-AOM.json"))
                .name("ndaqJsonItemReader")
                .build();
    }

    @Bean
    public NasdaqItemProcessor nasdaqItemProcessor() {

        return new NasdaqItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<NasdaqTotalView> unprocessedItemWriter(DataSource dataSource) {
        logger.info("Give me some feedback on the dataSource " + dataSource);
                return new JdbcBatchItemWriterBuilder<NasdaqTotalView>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<NasdaqTotalView>())
                .sql("INSERT INTO nasdaq_totalview (soup_partition, soup_sequence, msg_type, symbol_locate, " +
                        "unique_timestamp, order_id, side, quantity, symbol, price, mpid) VALUES (:soupPartition, " +
                        ":soupSequence, :msgType, :symbolLocate, :uniqueTimestamp, :orderId, :side, :quantity, :symbol, :price, :mpid)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<NasdaqItemProcessor> processedItemWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<NasdaqItemProcessor>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO nasdaq_totalview (soup_partition, soup_sequence, msg_type, symbol_locate, " +
                        "unique_timestamp, order_id, side, quantity, symbol, price, mpid) VALUES " +
                        "(:soupPartition, :soupSequence, :msgType, :symbolLocate, :uniqueTimestamp, :orderId, :side, " +
                        ":quantity, :symbol, :price, :mpid)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener jobCompletionNotificationListener, Step step2) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .flow(step2)
                .end()
                .listener(jobCompletionNotificationListener)
                .build();
    }

    @Bean
    public Step step1(ItemReader<NasdaqTotalView> jsonItemReader, ItemWriter<NasdaqTotalView> unprocessedItemWriter,
     ItemListener itemListener) throws Exception {
        return stepBuilderFactory.get("step1")
                .listener(itemListener)
                .<NasdaqTotalView, NasdaqTotalView> chunk(1)
                .reader(jsonItemReader)
                .writer(unprocessedItemWriter)
                .build();
    }

    @Bean
    public Step step2(ItemReader<NasdaqTotalView> jsonItemReader,
      ItemProcessor<NasdaqTotalView, NasdaqTotalView> nasdaqItemProcessor,
      ItemWriter<NasdaqTotalView> processedItemWriter,
      ItemListener itemListener) throws Exception {
            return stepBuilderFactory.get("step2")
                .listener(itemListener)
                .<NasdaqTotalView, NasdaqTotalView> chunk(1)
                .reader(jsonItemReader)
                .processor(nasdaqItemProcessor)
                .writer(processedItemWriter)
                .build();
    }
}
