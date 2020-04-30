package dev.springsolver.springbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

        @Override
        public void afterJob(JobExecution jobExecution) {
            if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
                log.info("Job finished, verifying. Class: " + NasdaqTotalView.class );

                jdbcTemplate.query("SELECT record_id, soup_partition, soup_sequence, msg_type, symbol_locate, " +
                                "unique_timestamp, order_id, side, quantity, mpid, symbol, price" +
                                " FROM nasdaq_totalview",
                        (rs, row) -> new NasdaqTotalView(
                                rs.getInt("record_id"),
                                rs.getInt("soup_partition"),
                                rs.getInt("soup_sequence"),
                                rs.getString("msg_type"),
                                rs.getInt("symbol_locate"),
                                rs.getDate("unique_timestamp"),
                                rs.getInt("order_id"),
                                rs.getString("side"),
                                rs.getInt("quantity"),
                                rs.getString("mpid"),
                                rs.getString("symbol"),
                                rs.getInt("price"))
                ).forEach(nasdaqTotalView -> log.info("Found <" + nasdaqTotalView + "> in the database."));
            }
        }
    }
