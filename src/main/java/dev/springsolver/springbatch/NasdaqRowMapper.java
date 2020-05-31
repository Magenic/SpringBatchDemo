package dev.springsolver.springbatch;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class NasdaqRowMapper implements RowMapper<NasdaqTotalView> {
    @Override
    public NasdaqTotalView mapRow(ResultSet rs, int rowNum) throws SQLException  {
        return new NasdaqTotalView(
                    rs.getInt("soup_partition"),
                    rs.getInt("soup_sequence"),
                    rs.getString("msg_type"),
                    rs.getInt("symbol_locate"),
                    rs.getLong("unique_timestamp"),
                    rs.getInt("order_id"),
                    rs.getString("side"),
                    rs.getInt("quantity"),
                    rs.getString("symbol"),
                    rs.getInt("price"),
                    rs.getString("mpid"));
        }
}

