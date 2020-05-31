package dev.springsolver.springbatch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static Log logger = LogFactory.getLog(JobCompletionNotificationListener.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            logger.info("Job finished, verifying. Class: " + NasdaqTotalView.class );
            jdbcTemplate.query("SELECT soup_partition, soup_sequence, msg_type, symbol_locate, " +
                            "unique_timestamp, order_id, side, quantity, symbol, price, mpid" +
                            " FROM nasdaq_totalview",
                    (rs, row) -> new NasdaqTotalView(

                            rs.getInt("soup_partition"),
                            rs.getInt("soup_sequence"),
                            rs.getString("msg_type"),
                            rs.getInt("symbol_locate"),
                            rs.getLong("unique_timestamp"),
                            rs.getInt("order_id"),
                            rs.getString("side"),
                            rs.getInt("quantity"),
                            rs.getString("mpid"),
                            rs.getString("symbol"),
                            rs.getInt("price"))
            ).forEach(nasdaqTotalView -> logger.info("Found <" + nasdaqTotalView + "> in the database."));
        }
    }
}
